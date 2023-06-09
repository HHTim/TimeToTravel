package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.GiftItem;
import com.tibame.timetotravel.entity.GiftFollow;
import com.tibame.timetotravel.repository.GiftFollowRepository;
import com.tibame.timetotravel.repository.ViewGiftRepository;
import com.tibame.timetotravel.service.GiftSearchService;
import com.tibame.timetotravel.view.ViewGift;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftSearchServiceImpl implements GiftSearchService {

    @Autowired
    private ViewGiftRepository viewGiftRepository;

    @Autowired
    @Qualifier("giftFollowRepository")
    private GiftFollowRepository giftFollowRepository;

    @Override
    public List<ViewGift> getAll() {
        return viewGiftRepository.findAll();
    }

    @Override
    public Object getOne(Integer userId, Integer giftId) {

        ViewGift noLogin = viewGiftRepository.findById(giftId).orElse(null);
        // 如果user沒登入，userId = null，回傳普通的
        if (userId == null) {
            return noLogin;
        }
        // 如果user有登入，用userId, giftId 去查詢有無收藏
        GiftFollow hasFollow = giftFollowRepository.findByUserIdAndGiftId(userId, giftId);
        // 如果沒有收藏，直接回傳普通的
        if (hasFollow == null) {
            GiftItem loginNoFollow = new GiftItem();
            BeanUtils.copyProperties(noLogin, loginNoFollow);
            return loginNoFollow;
        }
        // 如果有收藏，加上userId, giftFollowId 回傳
        GiftItem loginFollow = new GiftItem();
        BeanUtils.copyProperties(noLogin, loginFollow);
        loginFollow.setUserId(userId);
        loginFollow.setGiftFollowId(hasFollow.getGiftFollowId());
        return loginFollow;

    }

    @Override
    public List<ViewGift> getByName(String giftName) {
        return viewGiftRepository.getByName(giftName);
    }

    @Override
    public List<ViewGift> sortGift(Integer giftSort){

        if (giftSort == 1) {
            return viewGiftRepository.descendGift();
        } else if (giftSort == 2) {
            return viewGiftRepository.ascendGift();
        }
        return viewGiftRepository.findAll();
    }

    @Override
    public List<ViewGift> getByType(String giftTypeId) {
        return viewGiftRepository.getByType(giftTypeId);
    }
}
