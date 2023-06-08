package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftOrderListDto {
    private Integer giftOrderId;
    private Integer giftId;
    private String giftName;
    private Integer giftPrice;
    private Integer boughtCount;
    private Integer unitPrice;
    private Integer comId;
    private String comName;
}
