package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.GiftOrderDetails;

import java.util.List;

public interface GiftOrderDetailsService {

    void insert(GiftOrderDetails giftOrderDetails);

    void deleteById(Integer giftOrderDetailsId);

    GiftOrderDetails updateById(Integer giftOrderDetailsId, GiftOrderDetails giftOrderDetails);

    GiftOrderDetails findById(Integer giftOrderDetailsId);

    List<GiftOrderDetails> findAll();


}
