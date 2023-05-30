package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.BookingPaid;
import com.tibame.timetotravel.dto.RoomOrder;

import java.lang.reflect.InvocationTargetException;

public interface PaidService {
    BookingPaid bookingPaid(Integer userId, Integer roomId, String startDate, String endDate) throws InvocationTargetException, IllegalAccessException;

    Integer insertOrder(Integer userId, Integer roomId, RoomOrder order);

}
