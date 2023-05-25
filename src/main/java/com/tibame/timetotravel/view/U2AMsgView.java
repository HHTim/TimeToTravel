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
@Table(name = "VIEW_U2A_MSG")
public class U2AMsgView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U2A_MSG_ID")
    private Integer u2aMsgId;

    @Column(name = "U2A_SENDER_ID")
    private Integer u2aSenderId;

    @Column(name = "U2A_RECEIVER_ID")
    private Integer u2aReceiverId;

    @Column(name = "U2A_SENDING_TIME")
    private Timestamp u2aSendingTime;

    @Column(name = "U2A_MSG_TITLE")
    private String u2aMsgTitle;

    @Column(name = "U2A_MSG_CONTENT")
    private String u2aMsgContent;

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
    private boolean userGender;

    @Column(name = "USER_BIRTHDATE")
    private Timestamp userBirthdate;

    @Column(name = "USER_SIGN_DATETIME")
    private Timestamp userSignDatetime;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_STATUS")
    private boolean userStatus;

    @Column(name = "USER_NEWS_STATUS")
    private boolean userNewsStatus;

    @Column(name = "ADMIN_ID")
    private Integer adminId;

    @Column(name = "ADMIN_ACCOUNT")
    private String adminAccount;

    @Column(name = "ADMIN_PASSWORD")
    private String adminPassword;

    @Column(name = "ADMIN_NAME")
    private String adminName;

}
