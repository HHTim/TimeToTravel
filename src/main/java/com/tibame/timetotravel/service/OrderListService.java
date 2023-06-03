package com.tibame.timetotravel.service;

import com.tibame.timetotravel.dto.OrderListDto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OrderListService {
    List<OrderListDto> findUserOrder(Integer userId) throws InvocationTargetException, IllegalAccessException;

    List<OrderListDto> findUserOrderByName(Integer userId, String name) throws InvocationTargetException, IllegalAccessException;

    List<OrderListDto> findUserOrderByNo(Integer userId, Integer orderId) throws InvocationTargetException, IllegalAccessException;

    void updateCommentByOrderId(Integer orderId, Integer orderRank, String orderComment);
}
