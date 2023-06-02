package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.GiftCart;

public interface GiftCartService {

    GiftCart getCart(Integer userId);
    void addToCart(Integer userId, Integer giftId, Integer giftCount);
    void removeFromCart(Integer userId, Integer giftId);
}
