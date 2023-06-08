package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.GiftOrderList;
import com.tibame.timetotravel.entity.GiftOrderDetails;

import java.util.List;

public interface GiftOrderDetailsService {

    List<GiftOrderList> findById(Integer giftOrderId);

}
