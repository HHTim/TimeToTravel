package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.service.Room2Service;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roomController")
public class Room2Controller {

    @Autowired
    Room2Service room2Service;

    @GetMapping("/search/{keyWord}/{people}/{start}/{end}")
    public List<ViewCompanyRoom> findAvailableCompany(
            @PathVariable String keyWord,
            @PathVariable Integer people,
            @PathVariable String start,
            @PathVariable String end,
            HttpServletResponse resp) {

        resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
        resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求

        System.out.println("收到請求");
        List<ViewCompanyRoom> availableCompanies = room2Service.findAvailableCompany(keyWord, people, start, end);
        return availableCompanies;
    }
}
