package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.Gift;

import java.util.List;

public interface GiftService {

//    basic 功能
    void insert(Gift gift);
    void deleteById(Integer giftId);
    void updateStatusById(Integer giftId, Gift gift);
    Gift findById(Integer giftId);

//    額外新增
    List<Gift> findAll();
    List<Gift> findByKeyword(String keyword);
    List<Gift> findByGiftType(String giftTypeValue);

    void updateById(Integer giftId, Gift gift);
}
