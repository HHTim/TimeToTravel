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
    private Integer a2cMsgId;

    @Column(name = "A2C_SENDER_ID")
    private Integer a2cSenderId;

    @Column(name = "A2C_RECEIVER_ID")
    private Integer a2cReceiverId;

    @Column(name = "A2C_SENDING_TIME")
    private Timestamp a2cSendingTime;

    @Column(name = "A2C_MSG_TITLE")
    private String a2cMsgTitle;

    @Column(name = "A2C_MSG_CONTENT")
    private String a2cMsgContent;

}
