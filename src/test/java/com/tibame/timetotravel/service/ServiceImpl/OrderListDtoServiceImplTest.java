package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.OrderListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@SpringBootTest
class OrderListDtoServiceImplTest {
    @Autowired
    OrderListServiceImpl orderListService;

    @Test
    public void findUserOrderTest() throws InvocationTargetException, IllegalAccessException {
        PageBean<OrderListDto> userOrders = orderListService.findUserOrder(3, 1);
        System.out.println(userOrders);
    }

    @Test
    public void findUserOrderByName() throws InvocationTargetException, IllegalAccessException {
        List<OrderListDto> orders = orderListService.findUserOrderByName(2, "noSuch");
        System.out.println(orders.size());
        for (OrderListDto order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void findUserOrderByNo() throws InvocationTargetException, IllegalAccessException {
        List<OrderListDto> order = orderListService.findUserOrderByNo(2, 9);
        System.out.println(order);
    }

}