package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDto {
    private Integer comId;
    private Integer roomId;
    private Integer orderId;
    private String comName;
    private String roomName;
    private String roomBed;
    private Date orderCheckIn;
    private Date orderCheckOut;
    private Integer roomPrice;
    private String journeyName;
    private Integer journeyPrice;
    private Timestamp orderDatetime;
    private Integer orderAmount;
}
