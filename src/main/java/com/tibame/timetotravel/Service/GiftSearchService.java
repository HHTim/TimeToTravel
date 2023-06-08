package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.GiftItem;
import com.tibame.timetotravel.view.ViewGift;

import java.util.List;

public interface GiftSearchService {

    public List<ViewGift> getAll();

    public Object getOne(Integer userId, Integer giftId);

    public List<ViewGift> getByName(String giftName);

    public List<ViewGift> sortGift(Integer giftSort);

    public List<ViewGift> getByType(String giftTypeId);

}
