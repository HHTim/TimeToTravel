package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.BookingRoom;
import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.entity.Room;
import com.tibame.timetotravel.repository.OrderDetailRepository;
import com.tibame.timetotravel.repository.PrivateSceneRepository;
import com.tibame.timetotravel.repository.RoomRepository;
import com.tibame.timetotravel.repository.SearchRepository;
import com.tibame.timetotravel.service.BookingService;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    SearchRepository searchRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    PrivateSceneRepository privateSceneRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public BookingRoom bookingRoom(Integer comId, Integer roomId) throws InvocationTargetException, IllegalAccessException {
        // 建立DTO
        BookingRoom bookingRoom = new BookingRoom();

        // 查詢商家名稱跟地址
        ViewCompanyRoom company = searchRepository.findByComIdAndRoomId(comId, roomId);
        BeanUtils.copyProperties(bookingRoom, company);

        // 將目前的房間的評價分數放入
        List<Integer> roomRank = orderDetailRepository.findRoomRank(roomId);
        bookingRoom.setOrderRanks(roomRank);


        // 查詢comId對應的所有房間
        List<Room> rooms = roomRepository.findAllByComId(comId);
        bookingRoom.setRooms(rooms);

        // 查詢comId對應的所有私房景點
        List<PrivateScene> scenes = privateSceneRepository.findAllByComId(comId);
        bookingRoom.setPrivateScenes(scenes);

        // 將查回來的所有房間跑回圈，並且查詢所有的評論，最後放入DTO
        List<String> allComments = new ArrayList<>();
        List<List<Integer>> allRanks = new ArrayList<>();
        for (Room room : rooms) {
            Integer currRoomId = room.getRoomId();
            List<String> comments = orderDetailRepository.findCommentByRoomId(currRoomId);
            List<Integer> ranks = orderDetailRepository.findRoomRank(currRoomId);

            allComments.addAll(comments);
            allRanks.add(ranks);
        }
        bookingRoom.setOrderComments(allComments);
        bookingRoom.setAllOrderRanks(allRanks);
        return bookingRoom;
    }
}
