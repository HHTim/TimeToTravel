package com.tibame.timetotravel.controller;

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

    @PostMapping("/giftOrderDetails")
    public String insert(@RequestBody GiftOrderDetails giftOrderDetails) {
        giftOrderDetailsService.insert(giftOrderDetails);
        return "新增成功";
    }

    @DeleteMapping("/giftOrderDetails/{giftOrderDetailsId}")
    public String deleteById(@PathVariable Integer giftOrderDetailsId) {
        giftOrderDetailsService.deleteById(giftOrderDetailsId);
        return "刪除成功";
    }

    @PutMapping("/giftOrderDetails/{giftOrderDetailsId}")
    public GiftOrderDetails updateById(@PathVariable Integer giftOrderDetailsId,
                                       @RequestBody GiftOrderDetails giftOrderDetails) {

        return giftOrderDetailsService.updateById(giftOrderDetailsId, giftOrderDetails);
    }

    @GetMapping("/giftOrderDetails/{giftOrderDetailsId}")
    public GiftOrderDetails findById(@PathVariable Integer giftOrderDetailsId) {
        return giftOrderDetailsService.findById(giftOrderDetailsId);
    }

    @GetMapping("/giftOrderDetails")
    public List<GiftOrderDetails> findAll() {
        return giftOrderDetailsService.findAll();
    }


}
