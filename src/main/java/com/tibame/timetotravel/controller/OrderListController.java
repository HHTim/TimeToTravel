package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.dto.OrderList;
import com.tibame.timetotravel.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class OrderListController {
    @Autowired
    OrderListService orderListService;

    @GetMapping("/orders/{userId}")
    public List<OrderList> findUserOrder(@PathVariable Integer userId) throws InvocationTargetException, IllegalAccessException {
        return orderListService.findUserOrder(userId);
    }

    @GetMapping("/orders/{userId}/name/{name}")
    public List<OrderList> findUserOrderByName(@PathVariable Integer userId, @PathVariable String name) throws InvocationTargetException, IllegalAccessException {
        return orderListService.findUserOrderByName(userId, name);
    }

    @GetMapping("/orders/{userId}/no/{no}")
    public OrderList findUserOrderByNo(@PathVariable Integer userId, @PathVariable String no) throws InvocationTargetException, IllegalAccessException {
        System.out.println("test " + userId + " " + no);
        return orderListService.findUserOrderByNo(userId, Integer.parseInt(no));
    }

    @PutMapping("orders/{orderId}")
    public void updateCommentByOrderId(@RequestBody Map<String, Object> commentBody) {
        Integer orderId = (Integer) commentBody.get("orderId");
        Integer orderRank = (Integer) commentBody.get("orderRank");
        String orderComment = (String) commentBody.get("orderComment");
        orderListService.updateCommentByOrderId(orderId, orderRank, orderComment);
    }

}
