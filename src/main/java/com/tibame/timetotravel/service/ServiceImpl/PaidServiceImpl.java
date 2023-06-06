package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.BookingPaidDto;
import com.tibame.timetotravel.dto.RoomOrderDto;
import com.tibame.timetotravel.entity.Journey;
import com.tibame.timetotravel.entity.OrderDetail;
import com.tibame.timetotravel.entity.User;
import com.tibame.timetotravel.repository.JourneyRepository;
import com.tibame.timetotravel.repository.OrderDetailRepository;
import com.tibame.timetotravel.repository.UserRepository;
import com.tibame.timetotravel.repository.ViewCompanyRoomRepository;
import com.tibame.timetotravel.service.PaidService;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Service
public class PaidServiceImpl implements PaidService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ViewCompanyRoomRepository viewCompanyRoomRepository;
    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public BookingPaidDto bookingPaid(Integer userId, Integer roomId, String startDate, String endDate) throws InvocationTargetException, IllegalAccessException {
        BookingPaidDto bookingPaidDto = new BookingPaidDto();
        bookingPaidDto.setCheckIn(startDate);
        bookingPaidDto.setCheckOut(endDate);
        User user = userRepository.findByUserId(userId);
        System.out.println(user);
        BeanUtils.copyProperties(bookingPaidDto, user);
        ViewCompanyRoom companyRoom = viewCompanyRoomRepository.findByRoomId(roomId);
        BeanUtils.copyProperties(bookingPaidDto, companyRoom);
        Integer comId = companyRoom.getComId();
        List<Journey> journeys = journeyRepository.findByComId(comId);
        bookingPaidDto.setJourney(journeys);

        return bookingPaidDto;
    }

    @Transactional
    @Override
    public Integer insertOrder(Integer userId, RoomOrderDto order) {
        // 建立Entity
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setUserId(userId);
        orderDetail.setRoomId(order.getRoomId());
        orderDetail.setOrderAmount(order.getOrderAmount());

        // 將DTO中的日期從字串轉成java.sql.Date型別
        Date checkIn = Date.valueOf(order.getOrderCheckIn());
        Date checkOut = Date.valueOf(order.getOrderCheckOut());
        orderDetail.setOrderCheckIn(checkIn);
        orderDetail.setOrderCheckOut(checkOut);

        // 沒有選購行程則journeyId為null
        Integer journeyId = order.getJourneyId();
        if (Objects.isNull(journeyId)) {
            orderDetail.setJourneyId(0);
        } else {
            orderDetail.setJourneyId(order.getJourneyId());
        }

        orderDetailRepository.save(orderDetail);
        return orderDetail.getOrderId();
    }
}
