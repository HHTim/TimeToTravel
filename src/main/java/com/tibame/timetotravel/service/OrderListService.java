package com.tibame.timetotravel.service;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.OrderListDto;

import java.lang.reflect.InvocationTargetException;

public interface OrderListService {
    PageBean<OrderListDto> findUserOrder(Integer userId, Integer page) throws InvocationTargetException, IllegalAccessException;

    PageBean<OrderListDto> findUserOrderByName(Integer userId, String name, Integer page) throws InvocationTargetException, IllegalAccessException;

    PageBean<OrderListDto> findUserOrderByNo(Integer userId, Integer orderId, Integer page) throws InvocationTargetException, IllegalAccessException;

    void updateCommentByOrderId(Integer orderId, Integer orderRank, String orderComment);

    PageBean<OrderListDto> findAllUserOrder(Integer page);
}
