package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.GiftCart;
import com.tibame.timetotravel.dto.GiftCartItem;
import com.tibame.timetotravel.dto.UserGiftCart;
import com.tibame.timetotravel.entity.Gift;
import com.tibame.timetotravel.repository.GiftRepository;
import com.tibame.timetotravel.service.GiftCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("giftCartService")
public class GiftCartServiceImpl implements GiftCartService {

    // 購物車存活時間(天)
    Integer expireTimeInDays = 3;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    GiftRepository giftRepository;

    @Override
    @Transactional
    public List<UserGiftCart> addToCart(Integer userId, Integer giftId, Integer giftCount) {
        String key = getCartKey(userId);
        System.out.println(key);
        GiftCart giftCart = (GiftCart) redisTemplate.opsForValue().get(key);
        // 如果沒有購物車紀錄，new一個新的
        if (giftCart == null) {
            GiftCart giftCart1 = new GiftCart(userId, new ArrayList<>());
            GiftCartItem giftCartItem = new GiftCartItem(giftId, giftCount);
            giftCart1.getItemList().add(giftCartItem);
            return save(userId, key, giftCart1);
        }
        // 有紀錄，取出購物車
        List<GiftCartItem> itemList = giftCart.getItemList();
        // 檢查每個商品
        for (GiftCartItem oldItem : itemList) {
            // 如果已經有此商品，數量增加
            if (giftId.equals(oldItem.getGiftId())) {
                int totalCount = oldItem.getGiftCount() + giftCount;
                if (totalCount > 99) {
                    totalCount = 99;
                }
                oldItem.setGiftCount(totalCount);
                return save(userId, key, giftCart); // 回傳列表
            }
        }
        // 如果無此商品，新增進去
        GiftCartItem item = new GiftCartItem(giftId, giftCount);
        itemList.add(item);
        return save(userId, key, giftCart);
    }

    @Override
    @Transactional
    public void removeFromCart(Integer userId, Integer giftId) {
        String key = getCartKey(userId);
        System.out.println(key);
        GiftCart giftCart = (GiftCart) redisTemplate.opsForValue().get(key);
        if (giftCart != null) {
            List<GiftCartItem> itemList = giftCart.getItemList();
            // 將指定giftId的商品刪除
            itemList.removeIf(item -> giftId.equals(item.getGiftId()));
            save(userId, key, giftCart);
        }
    }

    @Override
    @Transactional
    public void clearCart(Integer userId) {
        String key = getCartKey(userId);
        System.out.println(key);
        redisTemplate.delete(key);

//        GiftCart giftCart = (GiftCart) redisTemplate.opsForValue().get(key);
//        if (giftCart != null) {
//            List<GiftCartItem> itemList = giftCart.getItemList();
//            // 購物車列表清空
//            itemList.clear();
//            save(userId, key, giftCart);
//        }
    }

    @Override
    @Transactional
    public UserGiftCart updateCart(Integer userId, Integer giftId, Integer giftCount) {
        String key = getCartKey(userId);
        System.out.println(key);
        GiftCart giftCart = (GiftCart) redisTemplate.opsForValue().get(key);
        if (giftCart != null) {
            List<GiftCartItem> itemList = giftCart.getItemList();
            for (GiftCartItem oldItem : itemList) {
                // 找到指定giftId的商品
                if (giftId.equals(oldItem.getGiftId())) {
                    oldItem.setGiftCount(giftCount); // 更新數量
                    redisTemplate.opsForValue().set(key, giftCart);
                    redisTemplate.expire(key, expireTimeInDays, TimeUnit.DAYS);

                    Gift gift = giftRepository.findById(giftId).orElse(null);
                    Integer unitPrice = gift.getGiftPrice() * giftCount;
                    UserGiftCart userGiftCart = new UserGiftCart();
                    BeanUtils.copyProperties(gift, userGiftCart); // 複製商品資訊到購物車項目
                    userGiftCart.setUserId(userId);
                    userGiftCart.setGiftCount(giftCount);
                    userGiftCart.setUnitPrice(unitPrice);
                    return userGiftCart;
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public List<UserGiftCart> getCart(Integer userId) {
        // 要回傳使用者的專屬購物車列表
        List<UserGiftCart> resultList = new ArrayList<>();
        String key = getCartKey(userId);
        System.out.println(key);
        // 先從Redis確認有無加入購物車的紀錄
        GiftCart giftCart = (GiftCart) redisTemplate.opsForValue().get(key);

        // 如果有購物車紀錄
        if (giftCart != null) {
            // 取得購物車，但有可能是null
            List<GiftCartItem> itemList = giftCart.getItemList();
            // 如果購物車裡面有東西
            if (itemList.size() > 0) {
                // 拿出裡面每一項商品
                for (GiftCartItem item : itemList) {
                    UserGiftCart userGiftCart = new UserGiftCart();
                    // 以giftId去查詢商品完整資訊
                    Gift gift = giftRepository.findById(item.getGiftId()).orElse(null);
                    // 小計 = 商品價格 * 購買數量
                    Integer unitPrice = gift.getGiftPrice() * item.getGiftCount();

                    BeanUtils.copyProperties(gift, userGiftCart); // 複製商品資訊到購物車項目
                    userGiftCart.setUserId(userId);
                    userGiftCart.setGiftCount(item.getGiftCount());
                    userGiftCart.setUnitPrice(unitPrice);
                    resultList.add(userGiftCart);
                }
                return resultList;
            } else {
                return null;
            }
        }
        return null;
    }

    private String getCartKey(Integer userId) {
        // 給個前綴，變字串
        return "cart: " + userId;
    }

    private List<UserGiftCart> save(Integer userId, String key, GiftCart giftCart) {
        // 固定的存入Redis動作
        redisTemplate.opsForValue().set(key, giftCart);
        redisTemplate.expire(key, expireTimeInDays, TimeUnit.DAYS);
        return getCart(userId);
    }

}
