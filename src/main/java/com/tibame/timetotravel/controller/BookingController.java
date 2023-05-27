package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.BookingRoom;
import com.tibame.timetotravel.service.BookingService;
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
@RequestMapping("/BookingController")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping("forward/{comId}/{roomId}")
    public RedirectView redirect(@PathVariable String comId, @PathVariable String roomId, HttpServletRequest req) {

        System.out.println("test connection " + comId);
        HttpSession session = req.getSession();

        session.setAttribute("comId", Integer.parseInt(comId));
        session.setAttribute("roomId", Integer.parseInt(roomId));
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
