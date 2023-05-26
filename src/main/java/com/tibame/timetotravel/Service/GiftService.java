package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.Gift;

import java.util.List;

public interface GiftService {

    void insert(Gift gift);
    void deleteById(Integer giftId);

    void update(Integer giftId, Gift gift);
    Gift findById(Integer giftId);

    List<Gift> findAll();


}
