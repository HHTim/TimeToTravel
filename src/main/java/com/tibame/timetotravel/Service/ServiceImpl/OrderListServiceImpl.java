package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.dto.OrderList;
import com.tibame.timetotravel.entity.Journey;
import com.tibame.timetotravel.repository.JourneyRepository;
import com.tibame.timetotravel.repository.OrderDetailRepository;
import com.tibame.timetotravel.repository.RoomRepository;
import com.tibame.timetotravel.repository.ViewRoomOrderListRepository;
import com.tibame.timetotravel.service.OrderListService;
import com.tibame.timetotravel.view.ViewRoomOrderDetail;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderListServiceImpl implements OrderListService {

    @Autowired
    ViewRoomOrderListRepository viewRoomOrderListRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderList> findUserOrder(Integer userId) throws InvocationTargetException, IllegalAccessException {
        // 建立DTO陣列
        List<OrderList> orderLists = new ArrayList<>();

        // 根據userId查出所有消費者的訂房紀錄
        List<ViewRoomOrderDetail> orders = viewRoomOrderListRepository.findByUserId(userId);
        // 跑迴圈，每次迴圈建立一個DTO將entity中有的資料塞給DTO
        for (ViewRoomOrderDetail order : orders) {
            OrderList orderList = new OrderList();
            BeanUtils.copyProperties(orderList, order);
            // 取出entity的roomId journeyId查詢商家名稱跟形成名稱塞給DTO
            int roomId = order.getRoomId();
            int journeyId = order.getJourneyId();
            // 取得journeyName、journeyPrice
            Journey journey = journeyRepository.findByJourneyId(journeyId);
            BeanUtils.copyProperties(orderList, journey);
            // 取得comName
            String comName = roomRepository.findComNameByRoomId(roomId);
            orderList.setComName(comName);
            // 將完備的DTO放到List回傳
            orderLists.add(orderList);
        }
        return orderLists;
    }

    @Override
    public List<OrderList> findUserOrderByName(Integer userId, String name) throws InvocationTargetException, IllegalAccessException {
        // 建立DTO陣列
        List<OrderList> orderLists = new ArrayList<>();

        // 根據userId查出所有消費者的訂房紀錄
        List<ViewRoomOrderDetail> orders = viewRoomOrderListRepository.findByUserId(userId);
        // 跑迴圈，每次迴圈建立一個DTO將entity中有的資料塞給DTO
        for (ViewRoomOrderDetail order : orders) {
            OrderList orderList = new OrderList();
            BeanUtils.copyProperties(orderList, order);
            // 取出entity的roomId journeyId查詢商家名稱跟形成名稱塞給DTO
            int roomId = order.getRoomId();
            int journeyId = order.getJourneyId();
            // 取得comName
            String comName = roomRepository.findComNameByRoomId(roomId);
            // 如果查出來的comName跟輸入欄的查詢值一樣才把值放入否則直接跳過
            if (Objects.equals(comName, name)) {
                orderList.setComName(comName);
            } else {
                continue;
            }
            // 取得journeyName、journeyPrice
            Journey journey = journeyRepository.findByJourneyId(journeyId);
            BeanUtils.copyProperties(orderList, journey);
            // 將完備的DTO放到List回傳
            orderLists.add(orderList);
        }
        return orderLists;
    }

    @Override
    public OrderList findUserOrderByNo(Integer userId, Integer orderId) throws InvocationTargetException, IllegalAccessException {
        System.out.println("test");
        OrderList orderList = new OrderList();
        ViewRoomOrderDetail order = viewRoomOrderListRepository.findByUserIdAndOrderId(userId, orderId);
        BeanUtils.copyProperties(orderList, order);
        int roomId = order.getRoomId();
        int journeyId = order.getJourneyId();
        // 取得journeyName、journeyPrice
        Journey journey = journeyRepository.findByJourneyId(journeyId);
        BeanUtils.copyProperties(orderList, journey);
        // 取得comName
        String comName = roomRepository.findComNameByRoomId(roomId);
        orderList.setComName(comName);
        return orderList;
    }

    @Override
    public void updateCommentByOrderId(Integer orderId, Integer orderRank, String comment) {

    }
}
