package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gift_type")
public class GiftType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_TYPE_ID", insertable = false, updatable = false)
    private Integer giftTypeId;

    @Column(name = "GIFT_TYPE_NAME", nullable = false, length = 30)
    private String giftTypeName;

}
