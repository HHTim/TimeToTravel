package com.tibame.timetotravel.service.ServiceImpl;

import com.tibame.timetotravel.common.PageBean;
import com.tibame.timetotravel.dto.OrderListDto;
import com.tibame.timetotravel.entity.Journey;
import com.tibame.timetotravel.entity.OrderDetail;
import com.tibame.timetotravel.repository.JourneyRepository;
import com.tibame.timetotravel.repository.OrderDetailRepository;
import com.tibame.timetotravel.repository.RoomRepository;
import com.tibame.timetotravel.repository.ViewRoomOrderListRepository;
import com.tibame.timetotravel.service.OrderListService;
import com.tibame.timetotravel.view.ViewRoomOrderDetail;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public PageBean<OrderListDto> findUserOrder(Integer userId, Integer page) throws InvocationTargetException, IllegalAccessException {
        // 建立DTO陣列
        List<OrderListDto> orderListDtos = new ArrayList<>();

        // 根據userId查出所有消費者的訂房紀錄
        List<ViewRoomOrderDetail> orders = viewRoomOrderListRepository.findByUserId(userId);
        // 跑迴圈，每次迴圈建立一個DTO將entity中有的資料塞給DTO
        for (ViewRoomOrderDetail order : orders) {
            OrderListDto orderListDto = new OrderListDto();
            BeanUtils.copyProperties(orderListDto, order);
            // 取出entity的roomId journeyId查詢商家名稱跟形成名稱塞給DTO
            int roomId = order.getRoomId();
            int journeyId = order.getJourneyId();
            System.out.println("Journey ID: " + journeyId);
            // 取得journeyName、journeyPrice
            if (journeyId != 0) {
                Journey journey = journeyRepository.findByJourneyId(journeyId);
                System.out.println(journey.getJourneyId());
                BeanUtils.copyProperties(orderListDto, journey);
            } else {
                orderListDto.setJourneyName("");
                orderListDto.setJourneyPrice(0);
            }
            // 取得comName
            String comName = roomRepository.findComNameByRoomId(roomId);
            Integer comId = roomRepository.findComIdByRoomId(roomId);
            orderListDto.setComName(comName);
            orderListDto.setComId(comId);
            // 將完備的DTO放到List回傳
            orderListDtos.add(orderListDto);
        }
        PageBean<OrderListDto> pageBean = new PageBean<>();
        pageBean.setPageSize(orderListDtos.size());
        pageBean.setRows(orderListDtos.stream().skip((page - 1) * 5).limit(5).collect(Collectors.toList()));
        return pageBean;
    }

    @Override
    public PageBean<OrderListDto> findUserOrderByName(Integer userId, String name, Integer page) throws InvocationTargetException, IllegalAccessException {
        // 建立DTO陣列
        List<OrderListDto> orderListDtos = new ArrayList<>();

        // 根據userId查出所有消費者的訂房紀錄
        List<ViewRoomOrderDetail> orders = viewRoomOrderListRepository.findByUserId(userId);
        // 跑迴圈，每次迴圈建立一個DTO將entity中有的資料塞給DTO
        for (ViewRoomOrderDetail order : orders) {
            OrderListDto orderListDto = new OrderListDto();
            BeanUtils.copyProperties(orderListDto, order);
            // 取出entity的roomId journeyId查詢商家名稱跟形成名稱塞給DTO
            int roomId = order.getRoomId();
            int journeyId = order.getJourneyId();
            // 取得comName
            Integer comId = roomRepository.findComIdByRoomId(roomId);
            String comName = roomRepository.findComNameByRoomId(roomId);
            // 如果查出來的comName跟輸入欄的查詢值一樣才把值放入否則直接跳過
            if (Objects.equals(comName, name)) {
                orderListDto.setComName(comName);
                orderListDto.setComId(comId);
            } else {
                continue;
            }
            // 取得journeyName、journeyPrice
            if (journeyId != 0) {
                Journey journey = journeyRepository.findByJourneyId(journeyId);
                System.out.println(journey.getJourneyId());
                BeanUtils.copyProperties(orderListDto, journey);
            } else {
                orderListDto.setJourneyName("");
                orderListDto.setJourneyPrice(0);
            }
            // 將完備的DTO放到List回傳
            orderListDtos.add(orderListDto);
        }
        PageBean<OrderListDto> pageBean = new PageBean<>();
        pageBean.setPageSize(orderListDtos.size());
        pageBean.setRows(orderListDtos.stream().skip((page - 1) * 5).limit(5).collect(Collectors.toList()));
        return pageBean;
    }

    @Override
    public PageBean<OrderListDto> findUserOrderByNo(Integer userId, Integer orderId, Integer page) throws InvocationTargetException, IllegalAccessException {
        // 建立DTO陣列
        List<OrderListDto> orderListDtos = new ArrayList<>();

        List<ViewRoomOrderDetail> orders = viewRoomOrderListRepository.findByUserIdAndOrderId(userId, orderId);
        for (ViewRoomOrderDetail order : orders) {
            OrderListDto orderListDto = new OrderListDto();
            BeanUtils.copyProperties(orderListDto, order);
            int roomId = order.getRoomId();
            int journeyId = order.getJourneyId();
            // 取得journeyName、journeyPrice
            if (journeyId != 0) {
                Journey journey = journeyRepository.findByJourneyId(journeyId);
                System.out.println(journey.getJourneyId());
                BeanUtils.copyProperties(orderListDto, journey);
            } else {
                orderListDto.setJourneyName("");
                orderListDto.setJourneyPrice(0);
            }
            // 取得comName
            String comName = roomRepository.findComNameByRoomId(roomId);
            Integer comId = roomRepository.findComIdByRoomId(roomId);
            orderListDto.setComName(comName);
            orderListDto.setComId(comId);

            orderListDtos.add(orderListDto);
        }
        PageBean<OrderListDto> pageBean = new PageBean<>();
        pageBean.setPageSize(orderListDtos.size());
        pageBean.setRows(orderListDtos.stream().skip((page - 1) * 5).limit(5).collect(Collectors.toList()));
        return pageBean;
    }

    @Override
    @Transactional
    public void updateCommentByOrderId(Integer orderId, Integer orderRank, String orderComment) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).orElse(null);
        orderDetail.setOrderRank(orderRank);
        orderDetail.setOrderComment(orderComment);
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public PageBean<OrderListDto> findAllUserOrder(Integer page) {
        PageBean<OrderListDto> pageBean = new PageBean<>();
        List<OrderListDto> dtos = orderDetailRepository.findUserOrder();
        List<OrderListDto> result = dtos.stream().skip((page - 1) * 5).limit(5).collect(Collectors.toList());
        pageBean.setPageSize(Math.max(dtos.size()/5,1));
        pageBean.setRows(result);
        return pageBean;
    }
}
