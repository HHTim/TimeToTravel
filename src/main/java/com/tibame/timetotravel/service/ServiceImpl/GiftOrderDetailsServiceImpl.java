package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.GiftOrderDetails;
import com.tibame.timetotravel.repository.GiftOrderDetailsRepository;
import com.tibame.timetotravel.service.GiftOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("giftOrderDetailsService")
public class GiftOrderDetailsServiceImpl implements GiftOrderDetailsService {

    @Autowired
    @Qualifier("giftOrderDetailsRepository")
    private GiftOrderDetailsRepository giftOrderDetailsRepository;

    @Override
    @Transactional
    public void insert(GiftOrderDetails giftOrderDetails) {
        giftOrderDetailsRepository.save(giftOrderDetails);
    }

    @Override
    @Transactional
    public void deleteById(Integer giftOrderDetailsId) {
        giftOrderDetailsRepository.deleteById(giftOrderDetailsId);
    }

    @Override
    @Transactional
    public GiftOrderDetails updateById(Integer giftOrderDetailsId, GiftOrderDetails giftOrderDetails) {

        GiftOrderDetails god = giftOrderDetailsRepository.findById(giftOrderDetailsId).orElse(null);

        if (god != null) {
            god.setGiftOrderId(giftOrderDetails.getGiftOrderId());
            god.setGiftId(giftOrderDetails.getGiftId());
            god.setBoughtCount(giftOrderDetails.getBoughtCount());
            god.setUnitPrice(giftOrderDetails.getUnitPrice());
            giftOrderDetailsRepository.save(god);
            return god;
        } else {
            return null;
        }

    }

    @Override
    public GiftOrderDetails findById(Integer giftOrderDetailsId) {
        return giftOrderDetailsRepository.findById(giftOrderDetailsId).orElse(null);
    }

    @Override
    public List<GiftOrderDetails> findAll() {
        return giftOrderDetailsRepository.findAll();
    }
}
