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
@Table(name = "VIEW_ANN")
public class AnnView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANN_ID")
    private Integer annId;

    @Column(name = "ADMIN_ID_REF")
    private Integer adminId;

    @Column(name = "ANN_SENDING_TIME")
    private Timestamp annSendingTime;

    @Column(name = "ANN_TITLE")
    private String annTitle;

    @Column(name = "ANN_CONTENT")
    private String annContent;

    @Column(name = "ANN_PIC")
    private byte[] annPic;

    @Column(name = "COM_ID_REF")
    private Integer comId;

    @Column(name = "ADMIN_ID")
    private Integer adminIdRef;

    @Column(name = "ADMIN_ACCOUNT")
    private String adminAccount;

    @Column(name = "ADMIN_PASSWORD")
    private String adminPassword;

    @Column(name = "ADMIN_NAME")
    private String adminName;

    @Column(name = "COM_ID")
    private Integer comIdRef;

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
}
