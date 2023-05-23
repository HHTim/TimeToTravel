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
@Table(name = "ADMIN_TO_COM")
public class A2CMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A2C_MSG_ID")
    private Integer A2CMsgId;

    @Column(name = "A2C_SENDER_ID")
    private Integer A2CSenderId;

    @Column(name = "A2C_RECEIVER_ID")
    private Integer A2CReceiverId;

    @Column(name = "A2C_SENDING_TIME")
    private Timestamp A2CSendingTime;

    @Column(name = "A2C_MSG_TITLE")
    private String A2CMsgTitle;

    @Column(name = "A2C_MSG_CONTENT")
    private String A2CContent;

}
