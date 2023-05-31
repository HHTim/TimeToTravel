package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.OrderList;
import com.tibame.timetotravel.entity.OrderDetail;
import com.tibame.timetotravel.repository.JourneyRepository;
import com.tibame.timetotravel.repository.OrderDetailRepository;
import com.tibame.timetotravel.repository.RoomRepository;
import com.tibame.timetotravel.service.OrderListService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderListServiceImpl implements OrderListService {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    JourneyRepository journeyRepository;

    @Override
    public List<OrderList> findUserOrder(Integer userId) throws InvocationTargetException, IllegalAccessException {

        // 建立DTO陣列
        List<OrderList> orderLists = new ArrayList<>();

        // 根據userId查出所有消費者的訂房紀錄
        List<OrderDetail> orders = orderDetailRepository.findByUserId(userId);
        // 跑迴圈，每次迴圈建立一個DTO將entity中有的資料塞給DTO
        for (OrderDetail order : orders) {
            OrderList orderList = new OrderList();
            BeanUtils.copyProperties(orderList, order);
            // 取出entity的roomId journeyId查詢商家名稱跟形成名稱塞給DTO
            int roomId = order.getRoomId();
            int journeyId = order.getJourneyId();
            String comName = roomRepository.findComNameByRoomId(roomId);
            String journeyName = journeyRepository.findJourneyNameByJourneyId(journeyId);
            orderList.setComName(comName);
            orderList.setJourneyName(journeyName);
            // 將完備的DTO放到List回傳
            orderLists.add(orderList);
        }
        return orderLists;
    }
}
