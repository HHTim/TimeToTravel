package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.entity.GiftOrder;
import com.tibame.timetotravel.service.GiftOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftOrderController")
public class GiftOrderController {

    @Autowired
    @Qualifier("giftOrderService")
    private GiftOrderService giftOrderService;

    @PostMapping("/giftOrder")
    public String insert(@RequestBody GiftOrder giftOrder) {
        giftOrderService.insert(giftOrder);
        return "新增成功";
    }

    @DeleteMapping("/giftOrder/{giftOrderId}")
    public String deleteById(@PathVariable Integer giftOrderId) {
        giftOrderService.deleteById(giftOrderId);
        return "刪除成功";
    }


    @PutMapping("/giftOrder/{giftOrderId}")
    public GiftOrder updateById(@PathVariable Integer giftOrderId,
                                @RequestBody GiftOrder giftOrder) {

        return giftOrderService.updateById(giftOrderId, giftOrder);
    }

    @GetMapping("/giftOrder/{giftOrderId}")
    public GiftOrder findById(@PathVariable Integer giftOrderId) {
        return giftOrderService.findById(giftOrderId);
    }

    @GetMapping("/giftOrder")
    public List<GiftOrder> findAll() {
        return giftOrderService.findAll();
    }

}
