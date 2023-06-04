package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.OrderListDto;
import com.tibame.timetotravel.service.OrderListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class OrderListController {
    @Autowired
    OrderListService orderListService;

    @GetMapping("/orders/{page}")
    public PageBean<OrderListDto> findUserOrder(HttpServletRequest req, @PathVariable Integer page) throws InvocationTargetException, IllegalAccessException {
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        System.out.println("接受到的sessionID " + userId);
        return orderListService.findUserOrder(3, page);
    }

    @GetMapping("/orders/name/{name}")
    public List<OrderListDto> findUserOrderByName(@PathVariable String name, HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        return orderListService.findUserOrderByName(3, name);
    }

    @GetMapping("/orders/no/{no}")
    public List<OrderListDto> findUserOrderByNo(@PathVariable String no, HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        System.out.println("test " + userId + " " + no);
        return orderListService.findUserOrderByNo(3, Integer.parseInt(no));
    }

    @PutMapping("orders")
    public void updateCommentByOrderId(@RequestBody Map<String, Object> commentBody) {
        Integer orderId = (Integer) commentBody.get("orderId");
        Integer orderRank = (Integer) commentBody.get("orderRank");
        String orderComment = (String) commentBody.get("orderComment");
        orderListService.updateCommentByOrderId(orderId, orderRank, orderComment);
        System.out.println("修改評論與分數成功");
    }

}
