package com.tibame.timetotravel.service;

import com.tibame.timetotravel.view.ViewGift;

import java.util.List;

public interface GiftSearchService {

    public List<ViewGift> getAll();

    public List<ViewGift> getByName(String giftName);

    public List<ViewGift> sortGift(Integer giftSort);

    public List<ViewGift> getByType(String giftTypeId);

}
