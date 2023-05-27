package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.SearchRoom;
import com.tibame.timetotravel.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/SearchController")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/company/{keyword}/{people}/{startDate}/{endDate}")
    public List<SearchRoom> findAvailableCompany(
            @PathVariable String keyword,
            @PathVariable Integer people,
            @PathVariable String startDate,
            @PathVariable String endDate
    ) throws InvocationTargetException, IllegalAccessException {
        System.out.println("收到請求");
        return searchService.findAvailableCompany(keyword, people, startDate, endDate);
    }


}
