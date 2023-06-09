package com.tibame.timetotravel.repository;

import com.tibame.timetotravel.dto.GiftOrderList;
import com.tibame.timetotravel.entity.GiftOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("giftOrderRepository")
public interface GiftOrderRepository extends JpaRepository<GiftOrder, Integer> {
    @Query(value = "select new com.tibame.timetotravel.dto.GiftOrderList(go.giftOrderId, u.userName, g.giftName, g.giftPrice, god.boughtCount, god.unitPrice, go.giftOrderDatetime, go.giftOrderStatus, go.giftOrderAmount) from GiftOrder go left join User u on go.userId = u.userId left join GiftOrderDetails god on go.giftOrderId = god.giftOrderId left join Gift g on god.giftId = g.giftId")
    List<GiftOrderList> findAllGiftOrders();

    @Query(value = "SELECT * FROM gift_order WHERE USER_ID = ?1 ORDER BY GIFT_ORDER_ID DESC ", nativeQuery = true)
    List<GiftOrder> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM gift_order WHERE USER_ID = ?1 AND GIFT_ORDER_ID = ?2", nativeQuery = true)
    GiftOrder getByOrderId(Integer userId, Integer giftOrderId);

    @Query(value = "SELECT * FROM gift_order WHERE USER_ID = ?1 AND GIFT_ORDER_STATUS = ?2", nativeQuery = true)
    List<GiftOrder> getByStatus(Integer userId, Boolean giftOrderStatus);
}
