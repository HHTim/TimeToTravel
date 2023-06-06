package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.BookingRoomDto;
import com.tibame.timetotravel.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class BookingServiceImplTest {

    @Autowired
    BookingService bookingService;

    @Test
    public void bookingRoomTest() throws InvocationTargetException, IllegalAccessException {
        BookingRoomDto bookingRoomDto = bookingService.bookingRoom(2, 5, "2023-06-01", "2023-06-04");
//        System.out.println(bookingRoomDto.getComName());
//        System.out.println(bookingRoomDto.getComAddress());
//        System.out.println(bookingRoomDto.getRoomDesc());
//
//        for (Room room : bookingRoomDto.getRooms()) {
//            System.out.println(room);
//        }
//
//        for (PrivateScene privateScene : bookingRoomDto.getPrivateScenes()) {
//            System.out.println(privateScene);
//        }
//
//
//        System.out.println(bookingRoomDto.getOrderRanks());
//        System.out.println(bookingRoomDto.getAllUserNames());
//        System.out.println(bookingRoomDto.getAllComments());
//        System.out.println(bookingRoomDto.getAllOrderRanks());
//        System.out.println(bookingRoomDto.getAllUserAvatars());
    }
}