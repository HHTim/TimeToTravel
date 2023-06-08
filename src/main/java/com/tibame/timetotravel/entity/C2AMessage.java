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
@Table(name = "COM_TO_ADMIN")
public class C2AMessage {
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
}
