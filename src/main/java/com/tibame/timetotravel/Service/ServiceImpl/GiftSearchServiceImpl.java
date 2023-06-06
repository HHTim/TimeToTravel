package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.repository.ViewGiftRepository;
import com.tibame.timetotravel.service.GiftSearchService;
import com.tibame.timetotravel.view.ViewGift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftSearchServiceImpl implements GiftSearchService {

    @Autowired
    private ViewGiftRepository viewGiftRepository;

    @Override
    public List<ViewGift> getAll() {
        return viewGiftRepository.findAll();
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
