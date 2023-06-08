package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.GiftItem;
import com.tibame.timetotravel.service.GiftSearchService;
import com.tibame.timetotravel.view.ViewGift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/giftSearchController")
public class GiftSearchController {

    @Autowired
    GiftSearchService giftSearchService;

    @GetMapping("/giftSearch")
    public List<ViewGift> getAll() {
        return giftSearchService.getAll();
    }

    @GetMapping("/giftSearch/{userId}/{giftId}")
    public ResponseEntity<?> getOne(@PathVariable Integer userId,
                                 @PathVariable Integer giftId) {
        Object ifLoginFollow = giftSearchService.getOne(userId, giftId);

        return ResponseEntity.ok(ifLoginFollow);
    }

    @GetMapping("/giftSearch/giftName/{giftName}")
    public List<ViewGift> getByName(@PathVariable String giftName) {
        return giftSearchService.getByName(giftName);
    }

    @GetMapping("/giftSearch/giftSort/{giftSort}")
    public List<ViewGift> sortGift(@PathVariable Integer giftSort) {
        return giftSearchService.sortGift(giftSort);
    }

    @GetMapping("/giftSearch/giftType/{giftTypeId}")
    public List<ViewGift> getByType(@PathVariable String giftTypeId) {
        return giftSearchService.getByType(giftTypeId);
    }

}
