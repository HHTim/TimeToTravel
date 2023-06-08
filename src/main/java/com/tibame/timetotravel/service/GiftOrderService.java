package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.GiftOrder;

import java.util.List;

public interface GiftOrderService {

    void insert(Integer userId);

    List<GiftOrder> findByUserId(Integer userId);

//    PageBean<GiftOrder> findByUserId(Integer userId, Integer page);

    GiftOrder getByOrderId(Integer userId, Integer giftOrderId);

}
