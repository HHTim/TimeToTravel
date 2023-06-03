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
    private Integer C2AMsgId;

    @Column(name = "C2A_SENDER_ID")
    private Integer C2ASenderId;

    @Column(name = "C2A_RECEIVER_ID")
    private Integer C2AReceiverId;

    @Column(name = "C2A_SENDING_TIME")
    private Timestamp C2ASendingTime;

    @Column(name = "C2A_MSG_TITLE")
    private String C2AMsgTitle;

    @Column(name = "C2A_MSG_CONTENT")
    private String C2AMsgContent;
}
