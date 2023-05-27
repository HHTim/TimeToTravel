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
    public void findAllByRoomIdTest() {
        List<OrderDetail> orders = orderDetailRepository.findAllByRoomId(7);
        for (OrderDetail order : orders) {
            System.out.println(order.getOrderComment());
        }
    }

    @Test
    public void findAllCOmmentByRoomIdTest() {
        List<String> comments = orderDetailRepository.findCommentByRoomId(7);
        System.out.println(comments);
    }

    @Test
    public void findRoomRankTest() {
        List<Integer> ranks = orderDetailRepository.findRoomRank(7);
        System.out.println(ranks);
    }
}