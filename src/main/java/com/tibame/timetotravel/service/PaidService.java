package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.BookingPaidDto;
import com.tibame.timetotravel.dto.RoomOrderDto;

import java.lang.reflect.InvocationTargetException;

public interface PaidService {
    BookingPaidDto bookingPaid(Integer userId, Integer roomId, String startDate, String endDate) throws InvocationTargetException, IllegalAccessException;

    Integer insertOrder(Integer userId, RoomOrderDto order);

}
