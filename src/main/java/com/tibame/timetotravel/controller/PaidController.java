package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.BookingPaid;
import com.tibame.timetotravel.service.PaidService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class PaidController {

    @Autowired
    PaidService paidService;

    @PostMapping("/redirect-paid")
    public RedirectView redirect(@RequestBody Map<String, Object> requestBody, HttpServletRequest req) {
        HttpSession session = req.getSession();

        Integer userId = (Integer) requestBody.get("userId");
        String roomId = (String) requestBody.get("roomId");
        session.setAttribute("userId", userId);
        session.setAttribute("roomId", roomId);
        System.out.println("Paid for: " + userId + " " + roomId);

        return new RedirectView("/booking_paid");
    }

    @GetMapping("paid")
    public BookingPaid paid(HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String roomId = (String) session.getAttribute("roomId");
        String startDate = (String) session.getAttribute("startDate");
        String endDate = (String) session.getAttribute("endDate");

        return paidService.bookingPaid(userId, Integer.parseInt(roomId), startDate, endDate);
    }

}
