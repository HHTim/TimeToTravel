package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.SearchRoomDto;
import com.tibame.timetotravel.service.SearchService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class SearchController {

    @Autowired
    SearchService searchService;

    @PostMapping("/redirect-search")
    public RedirectView redirect(@RequestBody Map<String, Object> requestBody, HttpServletRequest req) {
        HttpSession session = req.getSession();

        String keyword = (String) requestBody.get("keyword");
        String people = (String) requestBody.get("people");
        String startDate = (String) requestBody.get("startDate");
        String endDate = (String) requestBody.get("endDate");

        session.setAttribute("keyword", keyword);
        session.setAttribute("people", people);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);

        System.out.println("Search for: " + keyword + " " + people + " " + startDate + " " + endDate);
        return new RedirectView("/search_room");
    }

    @GetMapping("/search/{currPage}")
    public PageBean<SearchRoomDto> search(@PathVariable Integer currPage, HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        HttpSession session = req.getSession();
        String keyword = (String) session.getAttribute("keyword");
        Integer people = Integer.parseInt((String) session.getAttribute("people"));
        String startDate = (String) session.getAttribute("startDate");
        String endDate = (String) session.getAttribute("endDate");

        return searchService.findAvailableCompany(keyword, people, startDate, endDate, currPage);
    }


//    @GetMapping("/company/{keyword}/{people}/{startDate}/{endDate}")
//    public List<SearchRoomDto> findAvailableCompany(
//            @PathVariable String keyword,
//            @PathVariable Integer people,
//            @PathVariable String startDate,
//            @PathVariable String endDate
//    ) throws InvocationTargetException, IllegalAccessException {
//        System.out.println("收到請求");
//        return searchService.findAvailableCompany(keyword, people, startDate, endDate);
//    }
}
