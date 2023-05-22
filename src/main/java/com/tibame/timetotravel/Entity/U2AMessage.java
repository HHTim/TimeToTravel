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
    @Column(name = "MSG_ID")
    private Integer msgId;

    @Column(name = "SENDER_ID")
    private Integer senderId;

    @Column(name = "RECEIVER_ID")
    private Integer receiverId;

    @Column(name = "SENDING_TIME")
    private Timestamp msgSendingTime;

    @Column(name = "MSG_TITLE")
    private String msgTitle;

    @Column(name = "MSG_CONTENT")
    private String msgContent;

}
