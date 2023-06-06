package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.BookingRoomDto;
import com.tibame.timetotravel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/rooms")
public class BookingController {

    @Autowired
    BookingService bookingService;

//    @PostMapping("/redirect-booking")
//    public RedirectView redirect(@RequestBody Map<String, Object> requestBody, HttpServletRequest req) {
//        HttpSession session = req.getSession();
//
//        String comId = (String) requestBody.get("comId");
//        String roomId = (String) requestBody.get("roomId");
//        session.setAttribute("comId", Integer.parseInt(comId));
//        session.setAttribute("roomId", Integer.parseInt(roomId));
//        System.out.println("Booking for: " + comId + " " + roomId);
//
//        return new RedirectView("/booking_room");
//    }

    @GetMapping("booking/{comId}/{roomId}/{start}/{end}")
    public BookingRoomDto booking(@PathVariable Integer comId, @PathVariable Integer roomId, @PathVariable String start, @PathVariable String end) throws InvocationTargetException, IllegalAccessException {
        System.out.println("商家: " + comId + "房型: " + roomId);
        return bookingService.bookingRoom(comId, roomId, start, end);
    }
}
