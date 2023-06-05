package com.tibame.timetotravel.service;

import com.tibame.timetotravel.entity.GiftFollow;

import java.util.List;

public interface GiftFollowService {

    void insert(GiftFollow giftFollow);

    void deleteById(Integer giftFollowId);

    GiftFollow findById(Integer giftFollowId);

    List <GiftFollow> findAll();

    GiftFollow updateById(Integer giftFollowId, GiftFollow giftFollow);
}
