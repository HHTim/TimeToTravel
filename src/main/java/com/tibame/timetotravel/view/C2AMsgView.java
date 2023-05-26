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
@Table(name = "VIEW_C2A_MSG")
public class C2AMsgView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C2A_MSG_ID")
    private Integer c2aMsgId;

    @Column(name = "C2A_SENDER_ID")
    private Integer c2aSenderId;

    @Column(name = "C2A_RECEIVER_ID")
    private Integer c2aReceiverId;

    @Column(name = "C2A_SENDING_TIME")
    private Timestamp c2aSendingTime;

    @Column(name = "C2A_MSG_TITLE")
    private String c2aMsgTitle;

    @Column(name = "C2A_MSG_CONTENT")
    private String c2aMsgContent;

    @Column(name = "COM_ID")
    private Integer comId;

    @Column(name = "COM_ACCOUNT")
    private String comAccount;

    @Column(name = "COM_PASSWORD")
    private String comPassword;

    @Column(name = "COM_NAME")
    private String comName;

    @Column(name = "COM_ADDRESS")
    private String comAddress;

    @Column(name = "COM_MANAGER")
    private String comManager;

    @Column(name = "COM_PHONE")
    private String comPhone;

    @Column(name = "COM_TAXID")
    private String comTaxid;

    @Column(name = "COM_SIGNDATE")
    private Timestamp comSigndate;

    @Column(name = "COM_EMAIL")
    private String comEmail;

    @Column(name = "COM_STATUS")
    private boolean comStatus;

    @Column(name = "COM_LONGITUDE")
    private String comLongitude;

    @Column(name = "COM_LATITUDE")
    private String comLatitude;

    @Column(name = "COM_AVATAR")
    private byte[] comAvatar;

    @Column(name = "COM_NEWS_STATUS")
    private boolean comNewsStatus;

    @Column(name = "ADMIN_ID")
    private Integer adminId;

    @Column(name = "ADMIN_ACCOUNT")
    private String adminAccount;

    @Column(name = "ADMIN_PASSWORD")
    private String adminPassword;

    @Column(name = "ADMIN_NAME")
    private String adminName;

}
