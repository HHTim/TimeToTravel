package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.SearchRoomDto;
import com.tibame.timetotravel.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class SearchController {

    @Autowired
    @Qualifier("searchService")
    SearchService searchService;

//    @PostMapping("/redirect-search")
//    public RedirectView searchRoom(@RequestBody Map<String, Object> requestBody, HttpServletRequest req) {
//        HttpSession session = req.getSession();
//
//        String keyword = (String) requestBody.get("keyword");
//        String sceneKeyword = (String) requestBody.get("sceneKeyword");
//        Integer people = (Integer) requestBody.get("people");
//        String startDate = (String) requestBody.get("startDate");
//        String endDate = (String) requestBody.get("endDate");
//        Integer isSearchRoom = (Integer) requestBody.get("isSearchRoom");
//
//        session.setAttribute("keyword", keyword);
//        session.setAttribute("people", people);
//        session.setAttribute("startDate", startDate);
//        session.setAttribute("endDate", endDate);
//
//        System.out.println("Search for: " + keyword + " " + sceneKeyword + " " + people + " " + startDate + " " + endDate + " " + isSearchRoom);
//        if (isSearchRoom == 1) {
//            return new RedirectView("/search_room");
//        } else {
//            return new RedirectView("/search");
//        }
//    }

    @GetMapping("/search/{keyword}/{people}/{startDate}/{endDate}/{currPage}")
    public PageBean<SearchRoomDto> search(
            @PathVariable String keyword,
            @PathVariable Integer people,
            @PathVariable String startDate,
            @PathVariable String endDate,
            @PathVariable Integer currPage
    ) throws InvocationTargetException, IllegalAccessException {
        return searchService.findAvailableCompany(keyword, people, startDate, endDate, currPage);
    }

    @GetMapping("/search/{keyword}/{people}/{startDate}/{endDate}")
    public List<SearchRoomDto> searchForScenes(
            @PathVariable String keyword,
            @PathVariable Integer people,
            @PathVariable String startDate,
            @PathVariable String endDate
    ) throws InvocationTargetException, IllegalAccessException {
        return searchService.findNearSceneRooms(keyword, people, startDate, endDate);
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
