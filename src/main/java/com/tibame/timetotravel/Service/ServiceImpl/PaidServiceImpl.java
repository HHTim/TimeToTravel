package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.BookingPaid;
import com.tibame.timetotravel.entity.Journey;
import com.tibame.timetotravel.entity.User;
import com.tibame.timetotravel.repository.JourneyRepository;
import com.tibame.timetotravel.repository.UserRepository;
import com.tibame.timetotravel.repository.ViewCompanyRoomRepository;
import com.tibame.timetotravel.service.PaidService;
import com.tibame.timetotravel.view.ViewCompanyRoom;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class PaidServiceImpl implements PaidService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ViewCompanyRoomRepository viewCompanyRoomRepository;
    @Autowired
    JourneyRepository journeyRepository;

    public BookingPaid bookingPaid(Integer userId, Integer roomId, String startDate, String endDate) throws InvocationTargetException, IllegalAccessException {
        BookingPaid bookingPaid = new BookingPaid();
        bookingPaid.setCheckIn(startDate);
        bookingPaid.setCheckOut(endDate);
        User user = userRepository.findByUserId(userId);
        BeanUtils.copyProperties(bookingPaid, user);
        ViewCompanyRoom companyRoom = viewCompanyRoomRepository.findByRoomId(roomId);
        BeanUtils.copyProperties(bookingPaid, companyRoom);
        Integer comId = companyRoom.getComId();
        List<Journey> journeys = journeyRepository.findByComId(comId);
        bookingPaid.setJourney(journeys);

        return bookingPaid;
    }
}
