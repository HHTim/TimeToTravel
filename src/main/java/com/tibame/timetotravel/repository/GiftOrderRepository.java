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

}
