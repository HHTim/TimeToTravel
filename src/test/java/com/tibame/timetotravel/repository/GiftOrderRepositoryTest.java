package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.dto.GiftOrderList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GiftOrderRepositoryTest {

    @Autowired
    GiftOrderRepository giftOrderRepository;

    @Test
    void findAllGiftOrdersTest() {
        List<GiftOrderList> allGiftOrders = giftOrderRepository.findAllGiftOrders();
        for (GiftOrderList allGiftOrder : allGiftOrders) {
            System.out.println(allGiftOrder);
        }
    }
}