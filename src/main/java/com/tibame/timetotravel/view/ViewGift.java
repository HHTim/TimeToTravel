package com.tibame.timetotravel.view;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "view_gift")
public class ViewGift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_ID", insertable = false, updatable = false)
    private Integer giftId;

    @Column(name = "COM_ID", nullable = false)
    private Integer comId;

    @Column(name = "GIFT_NAME", nullable = false)
    private String giftName;

    @Column(name = "GIFT_PRICE", nullable = false)
    private Integer giftPrice;

    @Column(name = "GIFT_STOCK", nullable = false)
    private Integer giftStock;

    @Column(name = "GIFT_STATUS", nullable = false)
    private Boolean giftStatus;

    @Column(name = "GIFT_TYPE_ID", nullable = false)
    private String giftTypeId;

    @Column(name = "GIFT_INTRO", nullable = false)
    private String giftIntro;

    @Column(name = "GIFT_PHOTO")
    private byte[] giftPhoto;

    @Column(name = "COM_NAME", nullable = false)
    private String comName;
}
