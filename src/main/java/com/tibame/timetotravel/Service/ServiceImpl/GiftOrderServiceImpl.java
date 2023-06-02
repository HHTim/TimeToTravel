package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.GiftOrder;
import com.tibame.timetotravel.repository.GiftOrderRepository;
import com.tibame.timetotravel.service.GiftOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("giftOrderService")
public class GiftOrderServiceImpl implements GiftOrderService {

    @Autowired
    @Qualifier("giftOrderRepository")
    private GiftOrderRepository giftOrderRepository;

    @Override
    @Transactional
    public void insert(GiftOrder giftOrder) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        giftOrder.setGiftOrderDatetime(timestamp);
        giftOrderRepository.save(giftOrder);
    }

    @Override
    @Transactional
    public void deleteById(Integer giftOrderId) {
        giftOrderRepository.deleteById(giftOrderId);
    }

    @Override
    @Transactional
    public GiftOrder updateById(Integer giftOrderId, GiftOrder giftOrder) {

        GiftOrder go = giftOrderRepository.findById(giftOrderId).orElse(null);

        if (go != null) {
            go.setGiftOrderStatus(giftOrder.getGiftOrderStatus());
            giftOrderRepository.save(go);
            return go;
        } else {
            return null;
        }
    }

    @Override
    public GiftOrder findById(Integer giftOrderId) {
        return giftOrderRepository.findById(giftOrderId).orElse(null);
    }

    @Override
    public List<GiftOrder> findAll() {
        return giftOrderRepository.findAll();
    }
}
