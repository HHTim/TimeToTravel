package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.BookingRoomDto;
import com.tibame.timetotravel.dto.OrderWithUser;
import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.entity.Room;
import com.tibame.timetotravel.repository.*;
import com.tibame.timetotravel.service.BookingService;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        // 拿回來的房間跑迴圈，者出每一間的時間區間的剩餘庫存數
        for (Room room : rooms) {
            Integer currRoomId = room.getRoomId();
//            System.out.println("期間訂單數: " + orderDetailRepository.findOrderByDate(currRoomId, start, end));
//            System.out.println("該房間的庫存數: " + room.getRoomStock());
//            System.out.println("相減過後剩餘的庫存數: " + (room.getRoomStock() - orderDetailRepository.findOrderByDate(currRoomId, start, end)));
            // 將room的庫存數更新為剩餘庫存數
            room.setRoomStock(room.getRoomStock() - orderDetailRepository.findOrderByDate(currRoomId, start, end));
//            System.out.println("最後的結果: " + room.getRoomStock());
        }
        bookingRoomDto.setRooms(rooms);
        // 將所有評論過濾沒有內容的放到DTO中
        List<OrderWithUser> orderWithUserByComId = orderDetailRepository.findOrderWithUserByComId(comId).stream()
                .filter(e -> !Objects.isNull(e.getOrderComment()))
                .map(e -> {
                    e.setConvertAvatar(new String(e.getUserAvatar()));
                    return e;
                })
                .collect(Collectors.toList());
        bookingRoomDto.setOrderWithUsers(orderWithUserByComId);
        return bookingRoomDto;
    }
}
