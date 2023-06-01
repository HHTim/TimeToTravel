package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.OrderList;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OrderListService {
    List<OrderList> findUserOrder(Integer userId) throws InvocationTargetException, IllegalAccessException;

    List<OrderList> findUserOrderByName(Integer userId, String name) throws InvocationTargetException, IllegalAccessException;

    List<OrderList> findUserOrderByNo(Integer userId, Integer orderId) throws InvocationTargetException, IllegalAccessException;

    void updateCommentByOrderId(Integer orderId, Integer orderRank, String orderComment);
}
