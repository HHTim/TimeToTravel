package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.Gift;
import com.tibame.timetotravel.view.ViewGiftOrderManage;

import java.util.List;

public interface GiftOrderManageService {

    //    basic 功能
//    ViewGiftOrderManage findById(Integer giftId);

    List<ViewGiftOrderManage> findAll();

    //搜尋
//    List<ViewGiftOrderManage> findByGiftOrder(Integer giftOrderId, Integer userId);
    List<ViewGiftOrderManage> findByGiftOrderId(Integer giftOrderId);
    List<ViewGiftOrderManage> findByUserAccount(Integer userAccount);
}
