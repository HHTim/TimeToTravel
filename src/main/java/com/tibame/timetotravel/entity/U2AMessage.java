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
@Table(name = "USER_TO_ADMIN")
public class U2AMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U2A_MSG_ID")
    private Integer u2aMsgId;

    @Column(name = "U2A_SENDER_ID")
    private Integer u2aSenderId;

    @Column(name = "U2A_RECEIVER_ID")
    private Integer u2aReceiverId;

    @Column(name = "U2A_SENDING_TIME")
    private Timestamp u2aMsgSendingTime;

    @Column(name = "U2A_MSG_TITLE")
    private String u2aMsgTitle;

    @Column(name = "U2A_MSG_CONTENT")
    private String u2aMsgContent;

}
