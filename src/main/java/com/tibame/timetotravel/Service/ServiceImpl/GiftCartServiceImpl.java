package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.GiftCart;
import com.tibame.timetotravel.dto.GiftCartItem;
import com.tibame.timetotravel.service.GiftCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GiftCartServiceImpl implements GiftCartService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public GiftCart getCart(Integer userId) {
        String key = getCartKey(userId);
        return (GiftCart) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void addToCart(Integer userId, Integer giftId, Integer giftCount) {
        String key = getCartKey(userId);
        GiftCart giftCart = (GiftCart) redisTemplate.opsForValue().get(key);
        if (giftCart == null) {
            giftCart = new GiftCart(userId, new ArrayList<>());
        }
        GiftCartItem giftCartItem = new GiftCartItem(giftId, giftCount);
        giftCart.addItem(giftCartItem);
        redisTemplate.opsForValue().set(key, giftCart);
    }

    @Override
    public void removeFromCart(Integer userId, Integer giftId) {
        String key = getCartKey(userId);
        GiftCart giftCart = (GiftCart) redisTemplate.opsForValue().get(key);
        if (giftCart != null) {
            giftCart.removeItem(giftId);
            redisTemplate.opsForValue().set(key, giftCart);
        }
    }

    private String getCartKey(Integer userId) {
        return "cart: " + userId;
    }


}
