package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.service.Room2Service;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Room2ServiceImplTest {

    @Autowired
    Room2Service room2Service;

    @Test
    public void findAvailableCompanyTest() {
        List<ViewCompanyRoom> companies = room2Service.findAvailableCompany("新北市", 2, "2023-05-22", "2023-05-28");
        System.out.println("共有: " + companies.size() + " 間符合條件");

        for (ViewCompanyRoom company : companies) {
            System.out.println(company);
        }
    }
}