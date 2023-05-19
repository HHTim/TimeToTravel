package com.tibame.timetotravel.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "ADMIN_ID")
    private Integer adminId;

    @Column(name = "ANN_SENDING_TIME")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp annSendingTime;

    @Column(name = "ANN_TITLE")
    private String annTitle;

    @Column(name = "ANN_CONTENT")
    private String annContent;

    @Column(name = "COM_ID")
    private Integer comId;
}
