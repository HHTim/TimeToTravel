package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.UserGiftCart;
import com.tibame.timetotravel.entity.GiftOrder;
import com.tibame.timetotravel.entity.GiftOrderDetails;
import com.tibame.timetotravel.repository.GiftOrderDetailsRepository;
import com.tibame.timetotravel.repository.GiftOrderRepository;
import com.tibame.timetotravel.service.GiftCartService;
import com.tibame.timetotravel.service.GiftOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service("giftOrderService")
public class GiftOrderServiceImpl implements GiftOrderService {

    @Autowired
    @Qualifier("giftOrderRepository")
    private GiftOrderRepository giftOrderRepository;

    @Autowired
    @Qualifier("giftOrderDetailsRepository")
    private GiftOrderDetailsRepository giftOrderDetailsRepository;

    @Autowired
    @Qualifier("giftCartService")
    private GiftCartService giftCartService;

    @Override
    @Transactional
    public void insert(Integer userId) {

        // 建立一筆訂單的許多訂單明細
        // 先依照userId取得購物車內容列表
        List<UserGiftCart> userGiftCartList = giftCartService.getCart(userId);
        if (userGiftCartList != null) {

            // 下訂時間
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            // 先創建一筆土產訂單
            GiftOrder giftOrder = new GiftOrder();
            giftOrder.setUserId(userId);
            giftOrder.setGiftOrderDatetime(currentTime);
            giftOrder.setGiftOrderStatus(false);
            giftOrderRepository.save(giftOrder);
            // 取得這筆訂單的編號
            Integer giftOrderId = giftOrder.getGiftOrderId();
            // 宣告總金額
            Integer amount = 0;
            // 取出每項購物車內容記錄到訂單明細
            for (UserGiftCart item : userGiftCartList) {
                GiftOrderDetails giftOrderDetails = new GiftOrderDetails();
                giftOrderDetails.setGiftOrderId(giftOrderId);
                giftOrderDetails.setGiftId(item.getGiftId());
                giftOrderDetails.setBoughtCount(item.getGiftCount());
                giftOrderDetails.setUnitPrice(item.getUnitPrice());
                giftOrderDetailsRepository.save(giftOrderDetails);
                amount += item.getUnitPrice();
            }
            // 總金額儲存回去這筆土產訂單
            giftOrder.setGiftOrderAmount(amount);
            giftOrderRepository.save(giftOrder);
            // 最後清空購物車
            giftCartService.clearCart(userId);
        }
    }

    @Override
    public List<GiftOrder> findByUserId(Integer userId) {
        return giftOrderRepository.findByUserId(userId);
    }

    @Override
    public GiftOrder getByOrderId(Integer userId, Integer giftOrderId) {
        return giftOrderRepository.getByOrderId(userId, giftOrderId);
    }

}


//    @Override
//    public PageBean<GiftOrder> findByUserId(Integer userId, Integer page) {
//        PageBean<GiftOrder> objectPageBean = new PageBean<>();
//
//        List<GiftOrder> giftOrderList = giftOrderRepository.findByUserId(userId).stream().skip((page - 1) * 5).collect(Collectors.toList());
//        objectPageBean.setPageSize(giftOrderList.size());
//        objectPageBean.setRows(giftOrderList);
//        return objectPageBean;
//    }
