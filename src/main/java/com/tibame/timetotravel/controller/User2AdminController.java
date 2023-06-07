package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.entity.U2AMessage;
import com.tibame.timetotravel.service.U2AMessageService;
import com.tibame.timetotravel.view.U2AMsgView;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User2AdminController")
public class User2AdminController {

    @Autowired
    @Qualifier("U2AMessageService")
    U2AMessageService u2AMessageService;

    @PostMapping("/message")
    public String insert(@RequestBody U2AMessage u2AMessage, HttpSession session){
        UserSessionDto user = (UserSessionDto) session.getAttribute("user");
        u2AMessage.setU2aSenderId(user.getUser().getUserId());
        u2AMessageService.insert(u2AMessage);
        return "執行資料庫的 Insert 操作";
    }

    @GetMapping("/message/page/{currPage}/{rows}")
    public PageBean<U2AMessage> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return u2AMessageService.findMsgByPageRowData(currPage,rows);
    }

    @GetMapping("/message/u2a/view/page/{currPage}/{rows}")
    public PageBean<U2AMsgView> readViewByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("View分頁搜尋");
        return u2AMessageService.findViewByPageRowData(currPage, rows);
    }

    @GetMapping("/message/u2a/view/page/{currPage}/{rows}/keyword/{keyword}")
    public PageBean<U2AMsgView> readViewByKeyWords(@PathVariable Integer currPage, @PathVariable Integer rows, @PathVariable String keyword){
        System.out.println("關鍵字搜尋");
        return u2AMessageService.findBeanPageViewByKeyWords(keyword, currPage, rows);
    }

    @GetMapping("/message/u2a/view/page/dateRange/{currPage}/{rows}/{startDate}/{endDate}")
    public PageBean<U2AMsgView> readViewByDateRange(@PathVariable Integer currPage, @PathVariable Integer rows , @PathVariable String startDate, @PathVariable String endDate){
        System.out.println("日期分頁搜尋range: "+ startDate + " ~ " + endDate);
        return u2AMessageService.findBeanPageViewByDateRange(startDate, endDate , currPage, rows);
    }

}
