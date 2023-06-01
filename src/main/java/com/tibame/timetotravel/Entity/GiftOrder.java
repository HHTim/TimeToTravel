package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Table(name = "GIFT_ORDER")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GiftOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_ORDER_ID")
    private Integer giftOrderId;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "GIFT_ORDER_AMOUNT")
    private Integer giftOrderAmount;

    @Column(name = "GIFT_ORDER_DATETIME", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp giftOrderDatetime;

    @Column(name = "GIFT_ORDER_TAKETYPE", nullable = false)
    private Integer giftOrderTaketype;

    @Column(name = "GIFT_ORDER_RECEIVER", length = 30)
    private String giftOrderReceiver;

    @Column(name = "GIFT_ORDER_RECEIVE_ADDRESS", length = 50)
    private String giftOrderReceiveAddress;

    @Column(name = "GIFT_ORDER_CELLPHONE", length = 10)
    private String giftOrderCellphone;

    @Column(name = "GIFT_ORDER_STATUS")
    private Integer giftOrderStatus;

}
