package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.SearchRoom;
import com.tibame.timetotravel.service.Room2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/roomController")
public class Room2Controller {

    @Autowired
    Room2Service room2Service;

    @GetMapping("/search/{keyword}/{people}/{startDate}/{endDate}")
    public List<SearchRoom> findAvailableCompany(
            @PathVariable String keyword,
            @PathVariable Integer people,
            @PathVariable String startDate,
            @PathVariable String endDate
    ) throws InvocationTargetException, IllegalAccessException {
        System.out.println("收到請求");
        return room2Service.findAvailableCompany(keyword, people, startDate, endDate);
    }
}
