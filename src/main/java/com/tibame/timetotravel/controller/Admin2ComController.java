package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.A2CMessage;
import com.tibame.timetotravel.service.A2CMessageService;
import com.tibame.timetotravel.view.A2CMsgView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin2ComController")
public class Admin2ComController {

    @Autowired
    @Qualifier("A2CMessageService")
    A2CMessageService a2cMessageService;

    @PostMapping("/message")
    public String insert(@RequestBody A2CMessage message){
        System.out.println("執行資料庫Insert");
        System.out.println("+++++++++++++++" + message);
        a2cMessageService.insert(message);
        return "執行資料庫的 Insert 操作";
    }

    @GetMapping("/message/page/{currPage}/{rows}")
    public PageBean<A2CMessage> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return a2cMessageService.findAllByPageRowData(currPage, rows);
    }

    @GetMapping("/message/a2c/view/page/{currPage}/{rows}")
    public PageBean<A2CMsgView> readViewByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("View分頁搜尋");
        return a2cMessageService.findViewByPageRowData(currPage, rows);
    }

}
