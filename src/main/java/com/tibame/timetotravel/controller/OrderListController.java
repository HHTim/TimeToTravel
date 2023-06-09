package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.OrderListDto;
import com.tibame.timetotravel.dto.UserSessionDto;
import com.tibame.timetotravel.service.OrderListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class OrderListController {
    @Autowired
    OrderListService orderListService;

    @GetMapping("/orders/{page}")
    public PageBean<OrderListDto> findUserOrder(HttpServletRequest req, @PathVariable Integer page) throws InvocationTargetException, IllegalAccessException {
        UserSessionDto user = (UserSessionDto) req.getSession().getAttribute("user");
        System.out.println("接受到的sessionID " + user.getUser().getUserId());
        Integer userId = user.getUser().getUserId();
        return orderListService.findUserOrder(userId, page);
    }

    @GetMapping("/orders/name/{name}/{page}")
    public PageBean<OrderListDto> findUserOrderByName(@PathVariable String name, @PathVariable Integer page, HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        UserSessionDto user = (UserSessionDto) req.getSession().getAttribute("user");
        System.out.println("接受到的sessionID " + user.getUser().getUserId());
        Integer userId = user.getUser().getUserId();
        return orderListService.findUserOrderByName(userId, name, page);
    }

    @GetMapping("/orders/no/{no}/{page}")
    public PageBean<OrderListDto> findUserOrderByNo(@PathVariable String no, @PathVariable Integer page, HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        UserSessionDto user = (UserSessionDto) req.getSession().getAttribute("user");
        System.out.println("接受到的sessionID " + user.getUser().getUserId());
        Integer userId = user.getUser().getUserId();
        return orderListService.findUserOrderByNo(userId, Integer.parseInt(no), page);
    }

    @PutMapping("orders")
    public void updateCommentByOrderId(@RequestBody Map<String, Object> commentBody) {
        Integer orderId = (Integer) commentBody.get("orderId");
        Integer orderRank = (Integer) commentBody.get("orderRank");
        String orderComment = (String) commentBody.get("orderComment");
        orderListService.updateCommentByOrderId(orderId, orderRank, orderComment);
        System.out.println("修改評論與分數成功");
    }

    @GetMapping("/orders/all/{page}")
    public PageBean<OrderListDto> findAllUserOrder(@PathVariable Integer page) {
        return orderListService.findAllUserOrder(page);
    }

}
