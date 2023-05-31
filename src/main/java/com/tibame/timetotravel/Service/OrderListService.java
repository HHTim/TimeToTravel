package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.OrderList;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OrderListService {
    List<OrderList> findUserOrder(Integer userId) throws InvocationTargetException, IllegalAccessException;
}
