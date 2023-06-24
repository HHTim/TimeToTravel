package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.BookingPaidDto;
import com.tibame.timetotravel.dto.RoomOrderDto;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.service.PaidService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/rooms")
public class PaidController {

    @Autowired
    PaidService paidService;

//    @PostMapping("/redirect-order")
//    public RedirectView redirect(@RequestBody Map<String, Object> requestBody, HttpServletRequest req) {
//        HttpSession session = req.getSession();
//
//        Integer userId = (Integer) requestBody.get("userId");
//        String roomId = (String) requestBody.get("roomId");
//        session.setAttribute("userId", userId);
//        session.setAttribute("roomId", roomId);
//        System.out.println("Paid for: " + userId + " " + roomId);
//
//        return new RedirectView("/booking_paid");
//    }

    @GetMapping("/paid/{roomId}/{startDate}/{endDate}")
    public BookingPaidDto paid(
            @PathVariable Integer roomId,
            @PathVariable String startDate,
            @PathVariable String endDate,
            HttpServletRequest req
    ) throws InvocationTargetException, IllegalAccessException {
        UserSessionDto user = (UserSessionDto) req.getSession().getAttribute("user");
        System.out.println("接受到的sessionID " + user.getUser().getUserId());
        Integer userId = user.getUser().getUserId();
        return paidService.bookingPaid(userId, roomId, startDate, endDate);
    }

    @PostMapping("/paid")
    public void insertOrder(HttpServletRequest req, @RequestBody RoomOrderDto order) {
        UserSessionDto user = (UserSessionDto) req.getSession().getAttribute("user");
        System.out.println("接受到的sessionID " + user.getUser().getUserId());
        Integer userId = user.getUser().getUserId();
        System.out.println(order);
        System.out.println("Insert order: " + userId + " " + order.getRoomId());
        System.out.println(paidService.insertOrder(userId, order));
    }

}
