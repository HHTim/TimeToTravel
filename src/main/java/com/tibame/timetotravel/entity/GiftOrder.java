package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gift_order")
public class GiftOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_ORDER_ID", insertable = false, updatable = false)
    private Integer giftOrderId;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "GIFT_ORDER_AMOUNT")
    private Integer giftOrderAmount;

    @Column(name = "GIFT_ORDER_DATETIME", nullable = false)
    private Timestamp giftOrderDatetime;

    @Column(name = "GIFT_ORDER_STATUS", columnDefinition = "tinyint(1) default 0")
    private Boolean giftOrderStatus;

}
