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
    private Integer msgId;

    @Column(name = "U2A_SENDER_ID")
    private Integer senderId;

    @Column(name = "U2A_RECEIVER_ID")
    private Integer receiverId;

    @Column(name = "U2A_SENDING_TIME")
    private Timestamp msgSendingTime;

    @Column(name = "U2A_MSG_TITLE")
    private String msgTitle;

    @Column(name = "U2A_MSG_CONTENT")
    private String msgContent;

}
