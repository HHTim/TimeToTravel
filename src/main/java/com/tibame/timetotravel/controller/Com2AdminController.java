package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.entity.C2AMessage;
import com.tibame.timetotravel.service.C2AMessageService;
import com.tibame.timetotravel.view.C2AMsgView;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Com2AdminController")
public class Com2AdminController {

    @Autowired
    @Qualifier("C2AMessageService")
    C2AMessageService c2AMessageService;

    @PostMapping("/message")
    public String insert(@RequestBody C2AMessage c2AMessage, HttpSession session){
        UserSessionDto company = (UserSessionDto) session.getAttribute("user");
        c2AMessage.setC2aSenderId(company.getCompany().getComId());
        c2AMessageService.insert(c2AMessage);
        return "執行資料庫的 Insert 操作";
    }

    @GetMapping("/message/page/{currPage}/{rows}")
    public PageBean<C2AMessage> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return c2AMessageService.findMsgByPageRowData(currPage,rows);
    }

    @GetMapping("/message/c2a/view/page/{currPage}/{rows}")
    public PageBean<C2AMsgView> readViewByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("View分頁搜尋");
        return c2AMessageService.findViewByPageRowData(currPage, rows);
    }

    @GetMapping("/message/c2a/view/page/{currPage}/{rows}/keyword/{keyword}")
    public PageBean<C2AMsgView> readViewByKeyWords(@PathVariable Integer currPage, @PathVariable Integer rows ,@PathVariable String keyword){
        System.out.println("關鍵字分頁搜尋: "+ keyword);
        return c2AMessageService.findBeanPageViewByKeyWords(keyword, currPage, rows);
    }

    @GetMapping("/message/c2a/view/page/dateRange/{currPage}/{rows}/{startDate}/{endDate}")
    public PageBean<C2AMsgView> readViewByDateRange(@PathVariable Integer currPage, @PathVariable Integer rows ,@PathVariable String startDate, @PathVariable String endDate){
        System.out.println("日期分頁搜尋range: "+ startDate + " ~ " + endDate);
        return c2AMessageService.findBeanPageViewByDateRange(startDate, endDate , currPage, rows);
    }

}
