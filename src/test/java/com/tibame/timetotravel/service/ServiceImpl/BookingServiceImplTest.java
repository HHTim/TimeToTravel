package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.BookingRoom;
import com.tibame.timetotravel.entity.PrivateScene;
import com.tibame.timetotravel.entity.Room;
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
        BookingRoom bookingRoom = bookingService.bookingRoom(2, 5);
        System.out.println(bookingRoom.getComName());
        System.out.println(bookingRoom.getComAddress());
        System.out.println(bookingRoom.getRoomDesc());

        for (Room room : bookingRoom.getRooms()) {
            System.out.println(room);
        }

        for (PrivateScene privateScene : bookingRoom.getPrivateScenes()) {
            System.out.println(privateScene);
        }

        for (String comment : bookingRoom.getOrderComments()) {
            System.out.println(comment);
        }

        System.out.println(bookingRoom.getOrderRanks());
        System.out.println(bookingRoom.getAllOrderRanks());
    }
}