package com.tibame.timetotravel.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftItem {
    private Integer giftFollowId;
    private Integer userId;
    private Integer giftId;
    private Integer comId;
    private String giftName;
    private Integer giftPrice;
    private Integer giftStock;
    private Boolean giftStatus;
    private String giftTypeId;
    private String giftIntro;
    private byte[] giftPhoto;
    private String comName;
}
