package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ANN")
public class Ann {
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

}
