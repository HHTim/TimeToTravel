package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "GIFT_ORDER_DETAILS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GiftOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_ORDER_DETAILS_ID")
    private Integer giftOrderDetailsId;

    @Column(name = "GIFT_ORDER_ID", nullable = false)
    private Integer giftOrderId;

    @Column(name = "GIFT_ID", nullable = false)
    private Integer giftId;

    @Column(name = "BOUGHT_COUNT", nullable = false)
    private Integer boughtCount;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Integer unitPrice;
}
