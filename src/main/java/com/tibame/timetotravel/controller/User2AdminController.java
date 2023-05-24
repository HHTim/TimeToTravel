package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.entity.U2AMessage;
import com.tibame.timetotravel.service.U2AMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User2AdminController")
public class User2AdminController {

    @Autowired
    @Qualifier("U2AMessageService")
    U2AMessageService u2AMessageService;

    @PostMapping("/message")
    public String insert(@RequestBody U2AMessage u2AMessage){
        u2AMessageService.insert(u2AMessage);
        return "執行資料庫的 Insert 操作";
    }

    @GetMapping("/message/page/{currPage}/{rows}")
    public PageBean<U2AMessage> readByPage(@PathVariable Integer currPage, @PathVariable Integer rows){
        System.out.println("分頁搜尋");
        return u2AMessageService.findAnnPageByRowData(currPage,rows);
    }

    @GetMapping("/getAll")
    public String getAll(){
        List<U2AMessage> list = u2AMessageService.getALl();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("num: " + i + ",list.getAdmin: "+list.get(i).getU2aMsgId());
        }
        return "執行資料庫的 queryAll 操作";
    }
}
