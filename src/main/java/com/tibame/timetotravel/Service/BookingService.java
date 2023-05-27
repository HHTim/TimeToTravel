package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.BookingRoom;

import java.lang.reflect.InvocationTargetException;

public interface BookingService {
    public BookingRoom bookingRoom(Integer comId, Integer roomId) throws InvocationTargetException, IllegalAccessException;

}
