package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void findByUserIdTest() {
        List<OrderDetail> orders = orderDetailRepository.findByUserId(3);
        for (OrderDetail order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void findByComIdTest() {
        List<OrderDetail> orders = orderDetailRepository.findByComId(2);
        for (OrderDetail order : orders) {
            System.out.println(order);
        }
    }
}