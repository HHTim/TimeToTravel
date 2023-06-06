package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.UserGiftCart;

import java.util.List;

public interface GiftCartService {

    List<UserGiftCart> addToCart(Integer userId, Integer giftId, Integer giftCount);
    void removeFromCart(Integer userId, Integer giftId);
    void clearCart(Integer userId);
    UserGiftCart updateCart(Integer userId, Integer giftId, Integer giftCount);
    List<UserGiftCart> getCart(Integer userId);
}
