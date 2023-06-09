package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.Gift;
import com.tibame.timetotravel.view.ViewGiftOrderManage;
import java.util.List;


public interface GiftOrderManageService {

    //    basic 功能
//    ViewGiftOrderManage findById(Integer giftId);

    List<ViewGiftOrderManage> findAll();

    //關鍵字搜尋
    List<ViewGiftOrderManage> findByKeyword(String keywordId, String keywordAccount);
//    List<ViewGiftOrderManage> findByGiftOrderId(Integer giftOrderId);
//    List<ViewGiftOrderManage> findByUserAccount(String userAccount);
    //日期搜尋
    List<ViewGiftOrderManage> findByDateRange(String startDate, String endDate);
////

}
