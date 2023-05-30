package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.BookingRoom;
import com.tibame.timetotravel.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/redirect-booking")
    public RedirectView redirect(@RequestBody Map<String, Object> requestBody, HttpServletRequest req) {
        HttpSession session = req.getSession();

        String comId = (String) requestBody.get("comId");
        String roomId = (String) requestBody.get("roomId");
        session.setAttribute("comId", Integer.parseInt(comId));
        session.setAttribute("roomId", Integer.parseInt(roomId));
        System.out.println("Booking for: " + comId + " " + roomId);

        return new RedirectView("/booking_room");
    }

    @GetMapping("booking")
    public BookingRoom booking(HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        HttpSession session = req.getSession();
        Integer comId = (Integer) session.getAttribute("comId");
        Integer roomId = (Integer) session.getAttribute("roomId");
        System.out.println(comId);
        return bookingService.bookingRoom(comId, roomId);
    }


}
