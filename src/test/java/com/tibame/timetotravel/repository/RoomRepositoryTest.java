package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RoomRepositoryTest {
    @Autowired
    RoomRepository roomRepository;

    @Test
    public void findAllByComIdTest() {
        List<Room> rooms = roomRepository.findAllByComId(2);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }
}