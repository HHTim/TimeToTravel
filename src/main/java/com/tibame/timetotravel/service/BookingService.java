package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.BookingRoomDto;

import java.lang.reflect.InvocationTargetException;

public interface BookingService {
    public BookingRoomDto bookingRoom(Integer comId, Integer roomId, String start, String end) throws InvocationTargetException, IllegalAccessException;

}
