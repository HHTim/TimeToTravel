package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.entity.Gift;
import com.tibame.timetotravel.repository.GiftRepository;
import com.tibame.timetotravel.service.GiftService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("giftService")
public class  GiftServiceImpl implements GiftService {

    @Autowired
    @Qualifier("giftRepository")
    private GiftRepository giftRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void insert(Gift gift) {
        giftRepository.save(gift);
    }

    @Override
    @Transactional
    public void deleteById(Integer giftId) {
        giftRepository.deleteById(giftId);
    }

    @Override
    @Transactional
    public void updateStatusById(Integer giftId, Gift gift) {
//        // 把舊的giftId拖出來
         Gift newGift = giftRepository.findById(giftId).orElse(null);
         newGift.setGiftStatus(gift.getGiftStatus());
         giftRepository.save(newGift);
    }


    public Gift findById(Integer giftId) {
        return giftRepository.findById(giftId).orElse(null);
    }

    @Override
    public List<Gift> findAll() {
        return giftRepository.findAll();
    }

    @Override
    public List<Gift> findByKeyword(String keyword) {
        if (keyword != null && !("".equals(keyword))) {
            System.out.println(keyword + "hahaha");
            return giftRepository.findByKeyword(keyword);
        } else {
            return null;
        }
    }

    @Override
    public List<Gift> findByGiftType(String giftTypeValue) {
        return giftRepository.findByGiftType(giftTypeValue);
    }

    @Override
    public void updateById(Integer giftId, Gift gift) {
        Gift newGift = giftRepository.findById(giftId).orElse(null);
        newGift.setGiftIntro(gift.getGiftIntro());
        newGift.setGiftName(gift.getGiftName());
        newGift.setGiftPhoto(gift.getGiftPhoto());
        newGift.setGiftPrice(gift.getGiftPrice());
        newGift.setGiftStock(gift.getGiftStock());
        newGift.setGiftTypeId(gift.getGiftTypeId());
        giftRepository.save(newGift);
    }
}
