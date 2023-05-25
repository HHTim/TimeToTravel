package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.service.Room2Service;
import com.tibame.timetotravel.view.ViewCompanyRoom;
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
            @PathVariable String end
    ) {
        System.out.println("收到請求");
        List<ViewCompanyRoom> availableCompanies = room2Service.findAvailableCompany(keyWord, people, start, end);
        return availableCompanies;
    }
}
