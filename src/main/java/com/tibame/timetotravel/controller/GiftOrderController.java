package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.entity.GiftOrder;
import com.tibame.timetotravel.service.GiftOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftOrderController")
public class GiftOrderController {

    @Autowired
    @Qualifier("giftOrderService")
    private GiftOrderService giftOrderService;

    @PostMapping("/giftOrder/{userId}")
    public ResponseEntity<String> insert(@PathVariable Integer userId) {
        giftOrderService.insert(userId);
        return ResponseEntity.ok("新增一筆訂單囉！");
    }

    @GetMapping("/giftOrder/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable Integer userId) {
        List<GiftOrder> giftOrderList = giftOrderService.findByUserId(userId);

        if (giftOrderList.isEmpty()) {
            String empty = "沒有訂單紀錄 :)";
            return ResponseEntity.ok(empty);
        }

        return ResponseEntity.ok(giftOrderList);
    }

    @GetMapping("/giftOrder/{userId}/{giftOrderId}")
    public ResponseEntity<?> getByOrderId(@PathVariable Integer userId,
                                  @PathVariable Integer giftOrderId) {
        GiftOrder giftOrder = giftOrderService.getByOrderId(userId, giftOrderId);

        if (giftOrder == null) {
            String none = "查無資料";
            return ResponseEntity.ok(none);
        }

        return ResponseEntity.ok(giftOrder);
    }

}
