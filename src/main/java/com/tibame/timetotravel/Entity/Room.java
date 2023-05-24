package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROOM")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID", insertable = false, updatable = false)
    private Integer roomId;

    @Column(name = "COM_ID", nullable = false)
    private Integer comId;

    @Column(name = "ROOM_NAME", nullable = false)
    private String roomName;

    @Column(name = "ROOM_PRICE", nullable = false)
    private Integer roomPrice;

    @Column(name = "ROOM_DESC", nullable = false)
    private String roomDesc;

    @Column(name = "ROOM_RELEASE", nullable = false)
    private Timestamp roomRelease;

    @Column(name = "ROOM_WIFI", nullable = false)
    private Boolean roomWifi;

    @Column(name = "ROOM_PET", nullable = false)
    private Boolean roomPet;

    @Column(name = "ROOM_BREAKFAST", nullable = false)
    private Boolean roomBreakfast;

    @Column(name = "ROOM_PARKING", nullable = false)
    private Boolean roomParking;

    @Column(name = "ROOM_SMOKING", nullable = false)
    private Boolean roomSmoking;

    @Column(name = "ROOM_24HOURS", nullable = false)
    private Boolean room24Hours;

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
