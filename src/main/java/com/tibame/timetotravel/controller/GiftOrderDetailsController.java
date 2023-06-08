package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.GiftOrderListDto;
import com.tibame.timetotravel.service.GiftOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftOrderDetailsController")
public class GiftOrderDetailsController {

    @Autowired
    @Qualifier("giftOrderDetailsService")
    private GiftOrderDetailsService giftOrderDetailsService;

    @GetMapping("/giftOrderDetails/{giftOrderId}")
    public List<GiftOrderListDto> findById(@PathVariable Integer giftOrderId) {
        List<GiftOrderListDto> giftOrderLists = giftOrderDetailsService.findById(giftOrderId);

        return giftOrderLists;
    }

}
