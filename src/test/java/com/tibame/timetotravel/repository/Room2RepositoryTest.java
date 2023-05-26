package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Room2RepositoryTest {

    @Autowired
    Room2Repository room2Repository;

    @Test
    public void findAvailableRoomTest() {
        List<ViewCompanyRoom> companies = room2Repository.findCompany("新北市", 4);

        for (ViewCompanyRoom company : companies) {
            System.out.println(company);
        }
    }

    @Test
    public void findRoomStockTest() {
        String start = "2023-05-10";
        String end = "2023-05-15";

        Integer roomStock = room2Repository.findRoomStock(5, start, end);
        System.out.println(roomStock);
    }

    @Test
    public void findRoomRankTest() {
        List<Integer> roomRanks = room2Repository.findRoomRank(7);
        System.out.println(roomRanks);
    }

}