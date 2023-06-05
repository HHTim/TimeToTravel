package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCartItem {
    private Integer giftId;
    private Integer giftCount;
}
