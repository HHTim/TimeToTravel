package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.GiftOrder;

import java.util.List;

public interface GiftOrderService {

    void insert(GiftOrder giftOrder);

    void deleteById(Integer giftOrderId);

    GiftOrder updateById(Integer giftOrderId, GiftOrder giftOrder);

    GiftOrder findById(Integer giftOrderId);

    List<GiftOrder> findAll();

}
