package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.BookingPaid;
import com.tibame.timetotravel.service.PaidService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/PaidController")
public class PaidController {

    @Autowired
    PaidService paidService;

    @RequestMapping("redirect/{userId}/{roomId}")
    public RedirectView redirect(@PathVariable Integer userId, @PathVariable Integer roomId, HttpServletRequest req) {
        System.out.println("test paid " + userId + " " + roomId);
        HttpSession session = req.getSession();

        session.setAttribute("userId", userId);
        session.setAttribute("roomId", roomId);
        return new RedirectView("/booking_paid");
    }

    @GetMapping("paid")
    public BookingPaid paid(HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Integer roomId = (Integer) session.getAttribute("roomId");
        String startDate = (String) session.getAttribute("startDate");
        String endDate = (String) session.getAttribute("endDate");

        return paidService.bookingPaid(userId, roomId, startDate, endDate);
    }

}
