package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.GiftOrderList;
import com.tibame.timetotravel.entity.GiftOrder;
import com.tibame.timetotravel.entity.GiftOrderDetails;
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
    public List<GiftOrderList> findById(@PathVariable Integer giftOrderId) {
        List<GiftOrderList> giftOrderLists = giftOrderDetailsService.findById(giftOrderId);

        return giftOrderLists;
    }

}
