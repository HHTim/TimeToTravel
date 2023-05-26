package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.SearchRoom;
import com.tibame.timetotravel.service.Room2Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@SpringBootTest
class Room2ServiceImplTest {

    @Autowired
    Room2Service room2Service;

    @Test
    public void findAvailableCompanyTest() throws InvocationTargetException, IllegalAccessException {
        List<SearchRoom> companies = room2Service.findAvailableCompany("新北市", 4, "2023-05-25", "2023-05-30");
        System.out.println("共有: " + companies.size() + " 間符合條件");

        for (SearchRoom company : companies) {
            System.out.println(company.getComName());
        }
    }
}