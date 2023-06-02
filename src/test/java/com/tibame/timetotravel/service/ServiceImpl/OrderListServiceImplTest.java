package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.OrderList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@SpringBootTest
class OrderListServiceImplTest {
    @Autowired
    OrderListServiceImpl orderListService;

    @Test
    public void findUserOrderTest() throws InvocationTargetException, IllegalAccessException {
        List<OrderList> userOrders = orderListService.findUserOrder(2);
        for (OrderList userOrder : userOrders) {
            System.out.println(userOrder);
        }
    }

    @Test
    public void findUserOrderByName() throws InvocationTargetException, IllegalAccessException {
        List<OrderList> orders = orderListService.findUserOrderByName(2, "noSuch");
        System.out.println(orders.size());
        for (OrderList order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void findUserOrderByNo() throws InvocationTargetException, IllegalAccessException {
        List<OrderList> order = orderListService.findUserOrderByNo(2, 9);
        System.out.println(order);
    }

}