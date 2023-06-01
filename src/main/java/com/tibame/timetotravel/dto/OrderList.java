package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderList {
    private Integer orderId;
    private String comName;
    private String roomName;
    private String roomBed;
    private String orderCheckIn;
    private String orderCheckOut;
    private Integer roomPrice;
    private String journeyName;
    private Integer journeyPrice;
    private String orderDatetime;
    private Integer orderAmount;
}
