package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.GiftOrderListDto;

import java.util.List;

public interface GiftOrderDetailsService {

    List<GiftOrderListDto> findById(Integer giftOrderId);

//    List<GiftOrderDetails> findAll();


}
