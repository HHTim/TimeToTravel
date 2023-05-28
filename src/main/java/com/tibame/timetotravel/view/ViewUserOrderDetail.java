package com.tibame.timetotravel.view;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "view_user_order_detail")
public class ViewUserOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false, insertable = false, updatable = false)
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

    @Column(name = "ORDER_RANK", nullable = false)
    private Integer orderRank;

    @Column(name = "ORDER_COMMENT", nullable = false)
    private String orderComment;

    @Column(name = "USER_ACCOUNT", nullable = false)
    private String userAccount;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_PHONE", nullable = false)
    private String userPhone;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String userNickname;

    @Column(name = "USER_AVATAR")
    private byte[] userAvatar;

    @Column(name = "USER_GENDER", nullable = false)
    private Boolean userGender;

    @Column(name = "USER_BIRTHDATE", nullable = false)
    private Date userBirthdate;

    @Column(name = "USER_SIGN_DATETIME", nullable = false)
    private Timestamp userSignDatetime;

    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;

    @Column(name = "USER_STATUS", nullable = false)
    private Boolean userStatus;

    @Column(name = "USER_NEWS_STATUS", nullable = false)
    private Boolean userNewsStatus;
}
