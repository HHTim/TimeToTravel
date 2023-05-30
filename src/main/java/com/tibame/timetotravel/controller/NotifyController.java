package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.NotifyBean;
import com.tibame.timetotravel.entity.User;
import com.tibame.timetotravel.service.A2UMessageService;
import com.tibame.timetotravel.service.CompanyService;
import com.tibame.timetotravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NotifyController")
public class NotifyController {

    @Autowired
    @Qualifier("A2UMessageService")
    A2UMessageService a2uMessageService;

//    @Autowired
//    @Qualifier("CompanyService")
//    CompanyService companyService;

//    @GetMapping("/user/news")
//    public NotifyBean<User> getUserNewsStatus(){
//        System.out.println("使用者資料查詢");
//        return userService.findByPageRowData(currPage,rows);
//    }
}
