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
@Table(name = "view_company_room")
public class ViewCompanyRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Boolean comStatus;

    @Column(name = "COM_LONGITUDE")
    private String comLongitude;

    @Column(name = "COM_LATITUDE")
    private String comLatitude;

    @Column(name = "COM_AVATAR")
    private byte[] comAvatar;

    @Column(name = "COM_NEWS_STATUS")
    private Boolean comNewsStatus;

    @Column(name = "ROOM_ID")
    private Integer roomId;

    @Column(name = "ROOM_NAME")
    private String roomName;

    @Column(name = "ROOM_PRICE")
    private Integer roomPrice;

    @Column(name = "ROOM_DESC")
    private String roomDesc;

    @Column(name = "ROOM_RELEASE")
    private Date roomRelease;

    @Column(name = "ROOM_WIFI")
    private Boolean roomWifi;

    @Column(name = "ROOM_PET")
    private Boolean roomPet;

    @Column(name = "ROOM_BREAKFAST")
    private Boolean roomBreakfast;

    @Column(name = "ROOM_PARKING")
    private Boolean roomParking;

    @Column(name = "ROOM_SMOKING")
    private Boolean roomSmoking;

    @Column(name = "ROOM_24HOURS")
    private Boolean room24Hours;

    @Column(name = "ROOM_STOCK")
    private Integer roomStock;

    @Column(name = "ROOM_BED")
    private String roomBed;

    @Column(name = "ROOM_PEOPLE")
    private Integer roomPeople;

    @Column(name = "ROOM_STATUS")
    private Boolean roomStatus;

    @Column(name = "ROOM_PHOTO")
    private byte[] roomPhoto;
}
