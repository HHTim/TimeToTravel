package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false, insertable = false, updatable = false)
    private Integer orderId;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "ROOM_ID", nullable = false)
    private Integer roomId;

    @Column(name = "JOURNEY_ID", nullable = false, columnDefinition = "INT default 0")
    private Integer journeyId = 0;

    @Column(name = "ORDER_DATETIME", nullable = false)
    private Timestamp orderDateTime;

    @Column(name = "ORDER_AMOUNT", nullable = false)
    private Integer orderAmount;

    @Column(name = "ORDER_CHECK_IN", nullable = false)
    private Date orderCheckIn;

    @Column(name = "ORDER_CHECK_OUT", nullable = false)
    private Date orderCheckOut;

    @Column(name = "ORDER_RANK", nullable = false, columnDefinition = "INT default 0")
    private Integer orderRank = 0;

    @Column(name = "ORDER_COMMENT", nullable = false)
    private String orderComment;

    @PrePersist
    public void setDefaultDateTime() {
        this.orderDateTime = new Timestamp(System.currentTimeMillis());
    }

    public OrderDetail() {
        this.journeyId = 0;
        this.orderRank = 0;
    }
}
