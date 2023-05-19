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

    @Column(name = "COM_ID")
    private Integer comId;

    @Column(name = "ADMIN_ID")
    private Integer adminIdRef;

    @Column(name = "ADMIN_ACCOUNT")
    private String adminAccount;

    @Column(name = "ADMIN_PASSWORD")
    private String adminPassword;

    @Column(name = "ADMIN_NAME")
    private String adminName;

}
