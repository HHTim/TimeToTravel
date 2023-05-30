package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.SearchRoom;
import com.tibame.timetotravel.service.SearchService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SearchController")
public class SearchController {

    @Autowired
    SearchService searchService;


    @PostMapping("/redirect")
    public RedirectView redirect(@RequestBody Map<String, Object> requestBody, HttpServletRequest req) {
        String keyword = (String) requestBody.get("keyword");
        String people = (String) requestBody.get("people");
        String startDate = (String) requestBody.get("startDate");
        String endDate = (String) requestBody.get("endDate");
        HttpSession session = req.getSession();

        session.setAttribute("keyword", keyword);
        session.setAttribute("people", people);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);

        System.out.println("TEST CONNECTION " + keyword + " " + people + " " + startDate + " " + endDate);
        return new RedirectView("/search_room");
    }

    @GetMapping("/search")
    public List<SearchRoom> search(HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        HttpSession session = req.getSession();
        String keyword = (String) session.getAttribute("keyword");
        Integer people = Integer.parseInt((String) session.getAttribute("people"));
        String startDate = (String) session.getAttribute("startDate");
        String endDate = (String) session.getAttribute("endDate");

        return searchService.findAvailableCompany(keyword, people, startDate, endDate);
    }


//    @GetMapping("/company/{keyword}/{people}/{startDate}/{endDate}")
//    public List<SearchRoom> findAvailableCompany(
//            @PathVariable String keyword,
//            @PathVariable Integer people,
//            @PathVariable String startDate,
//            @PathVariable String endDate
//    ) throws InvocationTargetException, IllegalAccessException {
//        System.out.println("收到請求");
//        return searchService.findAvailableCompany(keyword, people, startDate, endDate);
//    }
}
