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
        List<OrderList> userOrders = orderListService.findUserOrder(3);
        for (OrderList userOrder : userOrders) {
            System.out.println(userOrder);
        }
    }

}