package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "JOURNEY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOURNEY_ID", nullable = false, insertable = false, updatable = false)
    private Integer journeyId;

    @Column(name = "COM_ID", nullable = false)
    private Integer comId;

    @Column(name = "JOURNEY_NAME", nullable = false)
    private String journeyName;

    @Column(name = "JOURNEY_PRICE", nullable = false)
    private Integer journeyPrice;

    @Column(name = "JOURNEY_DESC", nullable = false)
    private String journeyDesc;

    @Column(name = "JOURNEY_PIC")
    private byte[] journeyPic;

    @Column(name = "JOURNEY_STATUS", nullable = false)
    private Boolean journeyStatus;
}
