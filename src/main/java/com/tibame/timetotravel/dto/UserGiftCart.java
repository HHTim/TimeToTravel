package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGiftCart {
    private Integer userId;
    private Integer giftCount;
    private Integer giftId;
    private String giftName;
    private Integer giftPrice;
    private byte[] giftPhoto;
    private Integer unitPrice;
}
