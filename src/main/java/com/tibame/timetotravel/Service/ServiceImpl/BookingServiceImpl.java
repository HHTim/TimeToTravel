package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.BookingRoom;
import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.entity.Room;
import com.tibame.timetotravel.repository.PrivateSceneRepository;
import com.tibame.timetotravel.repository.RoomRepository;
import com.tibame.timetotravel.repository.ViewCompanyRoomRepository;
import com.tibame.timetotravel.repository.ViewUserOrderDetailRepository;
import com.tibame.timetotravel.service.BookingService;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import com.tibame.timetotravel.view.ViewUserOrderDetail;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    ViewCompanyRoomRepository viewCompanyRoomRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    PrivateSceneRepository privateSceneRepository;
    @Autowired
    ViewUserOrderDetailRepository viewUserOrderDetailRepository;

    @Override
    public BookingRoom bookingRoom(Integer comId, Integer roomId) throws InvocationTargetException, IllegalAccessException {
        // 建立DTO
        BookingRoom bookingRoom = new BookingRoom();

        // 查詢商家名稱跟地址
        ViewCompanyRoom company = viewCompanyRoomRepository.findByComIdAndRoomId(comId, roomId);
        BeanUtils.copyProperties(bookingRoom, company);

        // 將目前的房間的評價分數放入
        List<Integer> roomRank = viewUserOrderDetailRepository.findRankByRoomId(roomId);
        bookingRoom.setOrderRanks(roomRank);

        // 查詢comId對應的所有房間
        List<Room> rooms = roomRepository.findAllByComId(comId);
        bookingRoom.setRooms(rooms);

        // 查詢comId對應的所有私房景點
        List<PrivateScene> scenes = privateSceneRepository.findAllByComId(comId);
        bookingRoom.setPrivateScenes(scenes);

        // DTO最終需要所有房間的評論、星星及消費者頭像，一間飯店會有多個評價也可能沒有評價所以用雙重陣列表示
        // 先宣告最後要塞給DTO的所有雙重陣列
        List<String> allComments = new ArrayList<>();
        List<Integer> allRanks = new ArrayList<>();
        List<byte[]> allAvatars = new ArrayList<>();
        List<String> allNames = new ArrayList<>();

        // 將查回來的房間迴圈取出他們的roomId，並且調查每個房間對應的使用者評價
        for (Room room : rooms) {
            Integer currRoomId = room.getRoomId();
//            List<String> comments = viewUserOrderDetailRepository.findCommentByRoomId(currRoomId);
//            List<Integer> ranks = viewUserOrderDetailRepository.findRankByRoomId(currRoomId);
            // 每個房間查回來的評論也會是一個一維陣列，先宣告到時候要放入外面的雙重陣列
            List<String> comments = new ArrayList<>();
            List<Integer> ranks = new ArrayList<>();
            List<byte[]> avatars = new ArrayList<>();
            List<String> names = new ArrayList<>();

            // 查詢該房間的所有使用者評論
            List<ViewUserOrderDetail> orders = viewUserOrderDetailRepository.findAllByRoomId(currRoomId);

            // 將目前這間房間查回來的評論再跑迴圈，將評論、分數、頭像放到一維陣列中
            for (ViewUserOrderDetail order : orders) {
                // 略過沒有評價分數與評論的訂單
                if (Objects.isNull(order.getOrderComment())) continue;

                comments.add(order.getOrderComment());
                ranks.add(order.getOrderRank());
                avatars.add(order.getUserAvatar());
                names.add(order.getUserName());
            }

            // 將剛剛的一維陣列放到二維陣列中
            allComments.addAll(comments);
            allRanks.addAll(ranks);
            allAvatars.addAll(avatars);
            allNames.addAll(names);
        }

        // 將最後的二維陣列塞給DTO
        bookingRoom.setAllComments(allComments);
        bookingRoom.setAllOrderRanks(allRanks);
        bookingRoom.setAllUserAvatars(allAvatars);
        bookingRoom.setAllUserNames(allNames);
        return bookingRoom;
    }
}
