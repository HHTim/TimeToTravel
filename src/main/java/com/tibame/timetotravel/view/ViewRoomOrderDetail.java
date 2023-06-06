package com.tibame.timetotravel.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "view_room_order_detail")
public class ViewRoomOrderDetail {
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private Integer orderId;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "ROOM_ID", nullable = false)
    private Integer roomId;

    @Column(name = "JOURNEY_ID", nullable = false)
    private Integer journeyId;

    @Column(name = "ORDER_DATETIME", nullable = false)
    private Timestamp orderDatetime;

    @Column(name = "ORDER_AMOUNT", nullable = false)
    private Integer orderAmount;

    @Column(name = "ORDER_CHECK_IN", nullable = false)
    private Date orderCheckIn;

    @Column(name = "ORDER_CHECK_OUT", nullable = false)
    private Date orderCheckOut;

    @Column(name = "ORDER_RANK")
    private Integer orderRank;

    @Column(name = "ORDER_COMMENT")
    private String orderComment;

    @Column(name = "COM_ID", nullable = false)
    private Integer comId;

    @Column(name = "ROOM_NAME", nullable = false)
    private String roomName;

    @Column(name = "ROOM_PRICE", nullable = false)
    private Integer roomPrice;

    @Column(name = "ROOM_DESC", nullable = false)
    private String roomDesc;

    @Column(name = "ROOM_RELEASE", nullable = false)
    private Date roomRelease;

    @Column(name = "ROOM_STOCK", nullable = false)
    private Integer roomStock;

    @Column(name = "ROOM_BED", nullable = false)
    private String roomBed;

    @Column(name = "ROOM_PEOPLE", nullable = false)
    private Integer roomPeople;

    @Column(name = "ROOM_STATUS", nullable = false)
    private Boolean roomStatus;

    @Column(name = "ROOM_PHOTO")
    private byte[] roomPhoto;
}
