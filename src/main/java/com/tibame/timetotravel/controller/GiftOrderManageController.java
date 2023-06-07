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
@RequestMapping("/giftOrderMangeController")
public class GiftOrderManageController {

    @Autowired
    @Qualifier("giftOrderManageService")
    private GiftOrderManageService giftOrderManageService;


    @GetMapping("/giftOrderManage")
    public List<ViewGiftOrderManage> findAll(){
        System.out.println("找全部訂單明細");
        return giftOrderManageService.findAll();
    }


    @GetMapping("/giftOrderManage/userAccount/{userAccount}")
    public List<ViewGiftOrderManage> findByUserAccount(@PathVariable Integer userAccount){
        System.out.println("userAccount 關鍵字搜尋");
        return giftOrderManageService.findByUserAccount(userAccount);
    };



    @GetMapping("/giftOrderManage/giftOrderId/{giftOrderId}")
    public List<ViewGiftOrderManage> findByGiftOrderId(@PathVariable Integer giftOrderId){
        System.out.println("giftOrderId 關鍵字搜尋");
        return giftOrderManageService.findByGiftOrderId(giftOrderId);
    };

    @GetMapping("/giftOrderManage/{startDate}/{endDate}")
    public List<ViewGiftOrderManage> findByDateRange(@PathVariable String startDate, @PathVariable String endDate){
        System.out.println("日期搜尋");
        return giftOrderManageService.findByDateRange(startDate, endDate);
    }

//    @GetMapping("/giftOrderManage/{giftOrderId}/{userId}")
//    public List<ViewGiftOrderManage> findByGiftOrder(@PathVariable Integer giftOrderId, @PathVariable Integer userId) {
//        System.out.println("訂單關鍵字搜尋");
//        if (giftOrderId != null) {  // 只根據 giftOrderId 查詢
//            System.out.println("AAAAA");
//            return giftOrderManageService.findByGiftOrderId(giftOrderId);
//        } else if (userId != null) { // 只根據 userId 查詢
//            System.out.println("VBBBBBB");
//            return giftOrderManageService.findByUserId(userId);
//        } else {
//            // 如果沒有提供任何參數，你可以返回所有訂單或者做其他處理
//            return giftOrderManageService.findAll();
//        }
//    }



}
