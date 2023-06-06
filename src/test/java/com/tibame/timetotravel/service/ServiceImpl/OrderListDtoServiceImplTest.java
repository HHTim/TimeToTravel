package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.OrderListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class OrderListDtoServiceImplTest {
    @Autowired
    OrderListServiceImpl orderListService;

    @Test
    public void findUserOrderTest() throws InvocationTargetException, IllegalAccessException {
        PageBean<OrderListDto> userOrders = orderListService.findUserOrder(3, 1);
        System.out.println(userOrders.getRows());
    }

    @Test
    public void findUserOrderByName() throws InvocationTargetException, IllegalAccessException {
        PageBean<OrderListDto> orders = orderListService.findUserOrderByName(2, "noSuch", 1);
        System.out.println(orders);
    }

    @Test
    public void findUserOrderByNo() throws InvocationTargetException, IllegalAccessException {
        PageBean<OrderListDto> order = orderListService.findUserOrderByNo(2, 9, 1);
        System.out.println(order);
    }

}