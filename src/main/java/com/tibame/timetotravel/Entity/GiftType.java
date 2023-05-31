package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Table(name = "GIFT_TYPE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GiftType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_TYPE_ID")
    private Integer giftTypeId;

    @Column(name = "GIFT_TYPE_NAME", nullable = false)
    private String giftTypeName;
}
