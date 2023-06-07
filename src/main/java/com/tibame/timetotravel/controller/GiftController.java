package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.entity.Gift;
import com.tibame.timetotravel.entity.Room;
import com.tibame.timetotravel.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftController")
public class GiftController {

    @Autowired
    @Qualifier("giftService")
    private GiftService giftService;

    @PostMapping("/gift")
    public String insert(@RequestBody Gift gift){
        giftService.insert(gift);
        return "新增成功";
    }

    @DeleteMapping("/gift/{giftId}")
    public String deleteById(@PathVariable Integer giftId){
       giftService.deleteById(giftId);
        return "刪除成功";
    }

    @PutMapping("/gift/{giftId}")
    public String updateStatusById(@PathVariable Integer giftId,
                             @RequestBody Gift gift){
        giftService.updateStatusById(giftId, gift);
        return "修改成功";
    }
    @PutMapping("/gift/updateGift/{giftId}")
    public String updateById(@PathVariable Integer giftId,
                                @RequestBody Gift gift){
        giftService.updateById(giftId, gift);
        return "修改成功!";
    }


    @GetMapping("/gift/findByGiftId/{giftId}")
    public Gift findById(@PathVariable Integer giftId) {
        return giftService.findById(giftId);
    }


    @GetMapping("/gift")
    public List<Gift> findAll(){
        System.out.println("找全部土產");
        return giftService.findAll();
    }

    @GetMapping("/gift/{keyword}")
    public List<Gift> findByKeyword(@PathVariable String keyword){
        System.out.println("關鍵字搜尋");
        return giftService.findByKeyword(keyword);
    }
    @GetMapping("/gift/giftType/{giftTypeValue}")
    public List<Gift> findByGiftType(@PathVariable String giftTypeValue){
        System.out.println(giftTypeValue);
        System.out.println("分類查詢");
        return giftService.findByGiftType(giftTypeValue);
    }






}
