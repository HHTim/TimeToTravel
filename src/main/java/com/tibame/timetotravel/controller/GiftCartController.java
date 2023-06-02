package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.GiftCart;
import com.tibame.timetotravel.service.GiftCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/giftCartController")
public class GiftCartController {

    @Autowired
    GiftCartService giftCartService;

    @GetMapping("/{userId}")
    public GiftCart getCart(@PathVariable Integer userId) {
        return giftCartService.getCart(userId);
    }

    @PostMapping("/{userId}")
    public void addToCart(@RequestBody Map<String, Object> map) {
        Integer userId = (Integer) map.get("userId");
        Integer giftId = (Integer) map.get("giftId");
        Integer giftCount = (Integer) map.get("giftCount");
        giftCartService.addToCart(userId, giftId, giftCount);
    }

    @DeleteMapping("/{userId}/remove/{giftId}")
    public void removeFromCart(@PathVariable Integer userId,
                               @PathVariable Integer giftId) {
        giftCartService.removeFromCart(userId, giftId);
    }

}
