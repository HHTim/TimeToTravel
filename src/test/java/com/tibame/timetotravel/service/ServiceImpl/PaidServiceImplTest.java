package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.BookingPaidDto;
import com.tibame.timetotravel.service.PaidService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

@SpringBootTest
class PaidServiceImplTest {

    @Autowired
    PaidService paidService;

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        BookingPaidDto bookingPaidDto = paidService.bookingPaid(3, 5, "2023-05-01", "2023-05-02");
        System.out.println(bookingPaidDto);
    }
}