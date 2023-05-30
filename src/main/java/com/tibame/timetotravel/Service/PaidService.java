package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.BookingPaid;

import java.lang.reflect.InvocationTargetException;

public interface PaidService {
    public BookingPaid bookingPaid(Integer userId, Integer roomId, String startDate, String endDate) throws InvocationTargetException, IllegalAccessException;
}
