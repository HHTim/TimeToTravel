package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.entity.GiftOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("giftOrderRepository")
public interface GiftOrderRepository extends JpaRepository<GiftOrder, Integer> {

    List<GiftOrder> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM gift_order WHERE USER_ID = ?1 AND GIFT_ORDER_ID = ?2", nativeQuery = true)
    GiftOrder getByOrderId(Integer userId, Integer giftOrderId);

    @Query(value = "SELECT * FROM gift_order WHERE USER_ID = ?1 AND GIFT_ORDER_STATUS = ?2", nativeQuery = true)
    List<GiftOrder> getByStatus(Integer userId, Boolean giftOrderStatus);
}
