package com.tibame.timetotravel.controller;

import com.sun.tools.jconsole.JConsoleContext;
import com.tibame.timetotravel.entity.Gift;
import com.tibame.timetotravel.service.GiftOrderManageService;
import com.tibame.timetotravel.view.ViewGiftOrderManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


//    @GetMapping("/giftOrderManage/{giftOrderId}/{userId}")
//    public List<ViewGiftOrderManage> findByGiftOrder(@PathVariable Integer giftOrderId, @PathVariable Integer userId){
//        System.out.println("訂單關鍵字搜尋");
//        return giftOrderManageService.findByGiftOrder(giftOrderId, userId);
//    }


    @GetMapping("/giftOrderManage/{giftOrderId}/{userId}")
    public List<ViewGiftOrderManage> findByGiftOrder(@PathVariable Integer giftOrderId, @PathVariable Integer userId) {
        System.out.println("訂單關鍵字搜尋");
        if (giftOrderId != null) {  // 只根據 giftOrderId 查詢
            System.out.println("AAAAA");
            return giftOrderManageService.findByGiftOrderId(giftOrderId);
        } else if (userId != null) { // 只根據 userId 查詢
            System.out.println("VBBBBBB");
            return giftOrderManageService.findByUserId(userId);
        } else {
            // 如果沒有提供任何參數，你可以返回所有訂單或者做其他處理
            return giftOrderManageService.findAll();
        }
    }



}