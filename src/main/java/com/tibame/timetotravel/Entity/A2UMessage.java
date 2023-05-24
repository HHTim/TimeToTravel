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
@Table(name = "ADMIN_TO_USER")
public class A2UMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A2U_MSG_ID")
    private Integer a2uMsgId;

    @Column(name = "A2U_SENDER_ID")
    private Integer a2uSenderId;

    @Column(name = "A2U_RECEIVER_ID")
    private Integer a2uReceiverId;

    @Column(name = "A2U_SENDING_TIME")
    private Timestamp a2uSendingTime;

    @Column(name = "A2U_MSG_TITLE")
    private String a2uMsgTitle;

    @Column(name = "A2U_MSG_CONTENT")
    private String a2uMsgContent;
}
