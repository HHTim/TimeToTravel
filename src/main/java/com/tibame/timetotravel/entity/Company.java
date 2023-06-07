package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COM_ID", updatable = false, insertable = false)
    private Integer comId;

    @Column(name = "COM_ACCOUNT", nullable = false)
    private String comAccount;

    @Column(name = "COM_PASSWORD", nullable = false)
    private String comPassword;

    @Column(name = "COM_NAME", nullable = false)
    private String comName;

    @Column(name = "COM_ADDRESS", nullable = false)
    private String comAddress;

    @Column(name = "COM_MANAGER", nullable = false)
    private String comManager;

    @Column(name = "COM_PHONE", nullable = false)
    private String comPhone;

    @Column(name = "COM_TAXID", nullable = false)
    private String comTaxId;

    @Column(name = "COM_SIGNDATE", nullable = false)
    private Timestamp comSignDate;

    @Column(name = "COM_EMAIL", nullable = false)
    private String comEmail;

    @Column(name = "COM_STATUS", nullable = false)
    private Integer comStatus;

    @Column(name = "COM_LONGITUDE", nullable = false)
    private String comLongitude;

    @Column(name = "COM_LATITUDE", nullable = false)
    private String comLatitude;

    @Column(name = "COM_AVATAR", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] comAvatar;

    @Column(name = "COM_NEWS_STATUS", nullable = false)
    private Integer comNewsStatus;
}
