package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.view.ViewUserOrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ViewUserOrderDetailRepositoryTest {

    @Autowired
    ViewUserOrderDetailRepository viewUserOrderDetailRepository;

    @Test
    public void findAllByRoomIdTest() {
        List<ViewUserOrderDetail> orders = viewUserOrderDetailRepository.findAllByRoomId(7);
        for (ViewUserOrderDetail order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void findAllCOmmentByRoomIdTest() {
        List<String> comments = viewUserOrderDetailRepository.findCommentByRoomId(7);
        System.out.println(comments);
    }

    @Test
    public void findRoomRankTest() {
        List<Integer> ranks = viewUserOrderDetailRepository.findRankByRoomId(7);
        System.out.println(ranks);
    }
}