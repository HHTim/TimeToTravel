package com.tibame.timetotravel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "GIFT")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_ID", insertable = false, updatable = false)
    private Integer giftId;

    @Column(name = "COM_ID", nullable = false)
    private Integer comId;

    @Column(name = "GIFT_NAME", nullable = false)
    private String giftName;

    @Column(name = "GIFT_PRICE", nullable = false, columnDefinition = "int unsigned")
    private Integer giftPrice;

    @Column(name = "GIFT_STOCK", nullable = false, columnDefinition = "int unsigned default 0")
    private Integer giftStock;

    @Column(name = "GIFT_STATUS", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean giftStatus;

    @Column(name = "GIFT_TYPE_ID", nullable = false)
    private String giftTypeId;

    @Column(name = "GIFT_INTRO", nullable = false, length = 300)
    private String giftIntro;

    @Column(name = "GIFT_PHOTO")
    private byte[] giftPhoto;

}
