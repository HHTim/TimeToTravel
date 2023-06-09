package com.tibame.timetotravel.controller;

import com.sun.tools.jconsole.JConsoleContext;
import com.tibame.timetotravel.entity.Gift;
import com.tibame.timetotravel.service.GiftOrderManageService;
import com.tibame.timetotravel.view.ViewGiftOrderManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

//import java.util.Date;
import java.util.List;
import java.sql.Date;

@RestController
@RequestMapping("/giftOrderManageController")
public class GiftOrderManageController {

    @Autowired
    @Qualifier("giftOrderManageService")
    private GiftOrderManageService giftOrderManageService;


    @GetMapping("/giftOrderManage")
    public List<ViewGiftOrderManage> findAll(){
        System.out.println("找全部訂單明細");
        return giftOrderManageService.findAll();
    }


//    @GetMapping("/giftOrderManage/userAccount/{userAccount}")
//    public List<ViewGiftOrderManage> findByUserAccount(@PathVariable Integer userAccount){
//        System.out.println("userAccount 關鍵字搜尋");
//        return giftOrderManageService.findByUserAccount(userAccount);
//    };

//    @GetMapping("/giftOrderManage/giftOrderId/{giftOrderId}")
//    public List<ViewGiftOrderManage> findByGiftOrderId(@PathVariable Integer giftOrderId){
//        System.out.println("giftOrderId 關鍵字搜尋");
//        return giftOrderManageService.findByGiftOrderId(giftOrderId);
//    };

    @GetMapping("/giftOrderManage/{startDate}/{endDate}")
    public List<ViewGiftOrderManage> findByDateRange(@PathVariable String startDate, @PathVariable String endDate){
        System.out.println("日期搜尋");
        return giftOrderManageService.findByDateRange(startDate, endDate);
    }

    @GetMapping("/giftOrderManage/searchByKeyword/{keywordId}/{keywordAccount}")
    public List<ViewGiftOrderManage> findByKeyword(@PathVariable String keywordId,
                                                                    @PathVariable String keywordAccount) {
        System.out.println("訂單關鍵字搜尋");
        return giftOrderManageService.findByKeyword(keywordId, keywordAccount);
    }



}
