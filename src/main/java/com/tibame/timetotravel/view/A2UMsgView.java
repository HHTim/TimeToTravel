package com.tibame.timetotravel.view;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VIEW_A2U_MSG")
public class A2UMsgView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A2U_MSG_ID")
    private Integer a2uMsgId;

    @Column(name = "A2U_SENDER_ID")
    private Integer a2uSenderId;

    @Column(name = "A2U_RECEIVER_ID")
    private Integer a2uReceiverId;

    @Column(name = "A2U_SENDING_TIME")
    private Timestamp a2uSendingTime;

    @Column(name = "A2U_MSG_TITLE")
    private String a2uMsgTitle;

    @Column(name = "A2U_MSG_CONTENT")
    private String a2uMsgContent;

    @Column(name = "ADMIN_ID")
    private Integer adminId;

    @Column(name = "ADMIN_ACCOUNT")
    private String adminAccount;

    @Column(name = "ADMIN_PASSWORD")
    private String adminPassword;

    @Column(name = "ADMIN_NAME")
    private String adminName;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "USER_ACCOUNT")
    private String userAccount;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PHONE")
    private String userPhone;

    @Column(name = "USER_NICKNAME")
    private String userNickname;

    @Column(name = "USER_AVATAR")
    private byte[] userAvatar;

    @Column(name = "USER_GENDER")
    private Boolean userGender;

    @Column(name = "USER_BIRTHDATE")
    private Timestamp userBirthdate;

    @Column(name = "USER_SIGN_DATETIME")
    private Timestamp userSignDatetime;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_STATUS")
    private Boolean userStatus;

    @Column(name = "USER_NEWS_STATUS")
    private Boolean userNewsStatus;
}
