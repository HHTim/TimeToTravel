package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.dto.RoomWithCompanyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ViewCompanyRoomRepositoryTest {
    @Autowired
    ViewCompanyRoomRepository viewCompanyRoomRepository;

    @Test
    public void findJoinTest() {
        RoomWithCompanyDto dto = viewCompanyRoomRepository.findJoin(1);
        System.out.println(dto);
    }
}