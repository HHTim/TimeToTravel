package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.A2UMessage;
import com.tibame.timetotravel.service.A2UMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin2UserController")
public class Admin2UserController {

    @Autowired
    @Qualifier("A2UMessageService")
    A2UMessageService a2uMessageService;

    @PostMapping("/message")
    public String insert(@RequestBody A2UMessage message){
        System.out.println("執行資料庫Insert");
        System.out.println("+++++++++++++++" + message);
        a2uMessageService.insert(message);
        return "執行資料庫的 Insert 操作";
    }

    @GetMapping("/message/page/{currPage}/{rows}")
    public PageBean<A2UMessage> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return a2uMessageService.findAnnPageByRowData(currPage,rows);
    }

    @GetMapping("/getAll")
    public String getAll(){
        List<A2UMessage> list = a2uMessageService.getALl();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("num: " + i + ",list.getAdmin: "+list.get(i).getA2uMsgId());
        }
        return "執行資料庫的 queryAll 操作";
    }
}
