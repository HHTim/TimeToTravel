package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.GiftType;

import java.util.List;

public interface GiftTypeService {

    void insert(GiftType giftType);

    void deleteById(Integer giftTypeId);

    GiftType updateById(Integer giftTypeId, GiftType giftType);

    GiftType findById(Integer giftTypeId);

    List<GiftType> findAll();
}
