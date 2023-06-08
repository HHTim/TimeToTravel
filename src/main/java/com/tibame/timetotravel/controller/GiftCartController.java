package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.UserGiftCart;
import com.tibame.timetotravel.service.GiftCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/giftCartController")
public class GiftCartController {

    @Autowired
    @Qualifier("giftCartService")
    GiftCartService giftCartService;

    @PostMapping("/giftCart/{userId}")
    public ResponseEntity<List<UserGiftCart>> addToCart(@PathVariable Integer userId,
                                                        @RequestBody Map<String, Object> map) {
        Integer giftId = (Integer) map.get("giftId");
        Integer giftCount = (Integer) map.get("giftCount");
        return ResponseEntity.ok(giftCartService.addToCart(userId, giftId, giftCount));
    }

    @DeleteMapping("/giftCart/{userId}/{giftId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Integer userId,
                                                 @PathVariable Integer giftId) {
        giftCartService.removeFromCart(userId, giftId);
        return ResponseEntity.ok("刪除成功囉！");
    }

    @DeleteMapping("/giftCart/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Integer userId) {
        giftCartService.clearCart(userId);
        return ResponseEntity.ok("全部清空喔耶～");
    }

    @PutMapping("/giftCart/{userId}")
    public ResponseEntity<UserGiftCart> updateCart(@PathVariable Integer userId,
                                                   @RequestBody Map<String, Object> map) {
        Integer giftId = (Integer) map.get("giftId");
        Integer giftCount = (Integer) map.get("giftCount");
        return ResponseEntity.ok(giftCartService.updateCart(userId, giftId, giftCount));
    }

    @GetMapping("/giftCart/{userId}")
    public ResponseEntity<?> getCart(@PathVariable Integer userId) {
        List<UserGiftCart> userGiftCartList = giftCartService.getCart(userId);

        if (userGiftCartList == null) {
            String empty = "沒有商品 :)";
            return ResponseEntity.ok(empty);
        }

        return ResponseEntity.ok(userGiftCartList);
    }

    @GetMapping("/redirect_cart/{userId}")
    public RedirectView redirect(@PathVariable Integer userId) {
        List<UserGiftCart> userGiftCartList = giftCartService.getCart(userId);

        if (userGiftCartList == null) {
            return new RedirectView("/gift_search");
        }
        return new RedirectView("/gift_cart");
    }

}
