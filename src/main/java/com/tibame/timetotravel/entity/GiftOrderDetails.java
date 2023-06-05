package com.tibame.timetotravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gift_order_details")
public class GiftOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_ORDER_DETAILS_ID", insertable = false, updatable = false)
    private Integer giftOrderDetailsId;

    @Column(name = "GIFT_ORDER_ID", nullable = false)
    private Integer giftOrderId;

    @Column(name = "GIFT_ID", nullable = false)
    private Integer giftId;

    @Column(name = "BOUGHT_COUNT", nullable = false, columnDefinition = "int unsigned")
    private Integer boughtCount;

    @Column(name = "UNIT_PRICE", nullable = false, columnDefinition = "int unsigned")
    private Integer unitPrice;

}
