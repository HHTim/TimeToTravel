package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewRoomOrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderDetailRepositoryTest {

    @Autowired
    ViewRoomOrderListRepository viewRoomOrderListRepository;

    @Test
    public void findByUserIdTest() {
        List<ViewRoomOrderDetail> orders = viewRoomOrderListRepository.findByUserId(2);
        for (ViewRoomOrderDetail order : orders) {
            System.out.println(order.getOrderId());
        }
    }

    @Test
    public void findByComIdTest() {
        List<ViewRoomOrderDetail> orders = viewRoomOrderListRepository.findByComId(2);
        for (ViewRoomOrderDetail order : orders) {
            System.out.println(order);
        }
    }
}