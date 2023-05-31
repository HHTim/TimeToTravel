package com.tibame.timetotravel.controller;

import com.tibame.timetotravel.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderListController {
    @Autowired
    OrderListService orderListService;
}
