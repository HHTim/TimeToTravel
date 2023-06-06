package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.BookingRoomDto;
import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.entity.Room;
import com.tibame.timetotravel.repository.*;
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
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public BookingRoomDto bookingRoom(Integer comId, Integer roomId, String start, String end) throws InvocationTargetException, IllegalAccessException {
        // 建立DTO
        BookingRoomDto bookingRoomDto = new BookingRoomDto();

        // 查詢商家名稱跟地址
        ViewCompanyRoom company = viewCompanyRoomRepository.findByComIdAndRoomId(comId, roomId);
        BeanUtils.copyProperties(bookingRoomDto, company);

        // 將目前的房間的評價分數放入
        List<Integer> roomRank = viewUserOrderDetailRepository.findRankByRoomId(roomId);
        bookingRoomDto.setOrderRanks(roomRank);

        // 查詢comId對應的所有房間
        List<Room> rooms = roomRepository.findAllByComId(comId);


        // 查詢comId對應的所有私房景點
        List<PrivateScene> scenes = privateSceneRepository.findAllByComId(comId);
        bookingRoomDto.setPrivateScenes(scenes);

        // DTO最終需要所有房間的評論、星星及消費者頭像，一間飯店會有多個評價也可能沒有評價所以用雙重陣列表示
        // 先宣告最後要塞給DTO的所有雙重陣列
        List<String> allComments = new ArrayList<>();
        List<Integer> allRanks = new ArrayList<>();
        List<byte[]> allAvatars = new ArrayList<>();
        List<String> allNames = new ArrayList<>();

        // 將查回來的房間迴圈取出他們的roomId，並且調查每個房間對應的使用者評價
        for (Room room : rooms) {
            Integer currRoomId = room.getRoomId();

            System.out.println("期間訂單數: " + orderDetailRepository.findOrderByDate(currRoomId, start, end));
            System.out.println("該房間的庫存數: " + room.getRoomStock());
            System.out.println("相減過後剩餘的庫存數: " + (room.getRoomStock() - orderDetailRepository.findOrderByDate(currRoomId, start, end)));
            room.setRoomStock(room.getRoomStock() - orderDetailRepository.findOrderByDate(currRoomId, start, end));
            System.out.println("最後的結果: " + room.getRoomStock());


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

        bookingRoomDto.setRooms(rooms);


        // 將最後的二維陣列塞給DTO
        bookingRoomDto.setAllComments(allComments);
        bookingRoomDto.setAllOrderRanks(allRanks);
        bookingRoomDto.setAllUserAvatars(allAvatars);
        bookingRoomDto.setAllUserNames(allNames);
        return bookingRoomDto;
    }
}
