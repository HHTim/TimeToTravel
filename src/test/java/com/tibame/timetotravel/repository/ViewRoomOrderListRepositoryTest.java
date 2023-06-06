package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewRoomOrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ViewRoomOrderListRepositoryTest {
    @Autowired
    ViewRoomOrderListRepository viewRoomOrderListRepository;

    @Test
    public void findByUserIdTest() {
        List<ViewRoomOrderDetail> orders = viewRoomOrderListRepository.findByUserId(3);
        for (ViewRoomOrderDetail order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void findByUserIdAndOrderIdTest() {
        List<ViewRoomOrderDetail> order = viewRoomOrderListRepository.findByUserIdAndOrderId(3, 39);
        System.out.println(order);
    }

}