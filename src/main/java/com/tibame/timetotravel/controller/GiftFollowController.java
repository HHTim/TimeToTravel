package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.entity.GiftFollow;
import com.tibame.timetotravel.service.GiftFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftFollowController")
public class GiftFollowController {

    @Autowired
    @Qualifier("giftFollowService")
    private GiftFollowService giftFollowService;

    @PostMapping("/giftFollow")
    public String insert(@RequestBody GiftFollow giftFollow) {
        giftFollowService.insert(giftFollow);
        return "新增成功";
    }

    @DeleteMapping("/giftFollow/{giftFollowId}")
    public String deleteById(@PathVariable Integer giftFollowId) {
        giftFollowService.deleteById(giftFollowId);
        return "刪除成功";
    }

    @PutMapping("/giftFollow/{giftFollowId}")
    public GiftFollow updateById(@PathVariable Integer giftFollowId,
                                 @RequestBody GiftFollow giftFollow) {

        return giftFollowService.updateById(giftFollowId, giftFollow);
    }

    @GetMapping("/giftFollow/{giftFollowId}")
    public GiftFollow findById(@PathVariable Integer giftFollowId) {
        return giftFollowService.findById(giftFollowId);
    }


    @GetMapping("/giftOrder")
    public List<GiftFollow> findAll() {
        return giftFollowService.findAll();
    }

}
