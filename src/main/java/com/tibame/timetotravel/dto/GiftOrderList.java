package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class GiftOrderList {
    //訂單明細 :
    //訂單編號 GIFT_ORDER_ID
    //訂購人名字 USER_NAME
    //商品名稱 GIFT_NAME
    //商品價格 GIFT_PRICE
    //訂購數量 BOUGHT_COUNT
    //品項價格 UNIT_PRICE = GIFT_PRICE * BOUGHT_COUNT
    //訂單日期 GIFT_ORDER_DATETIME
    //訂單狀態 GIFT_ORDER_STATUS
    //訂單總金額 GIFT_ORDER_AMOUNT = UNIT_PRICE * N

    private Integer giftOrderId;
    private String userName;
//    private List<String> giftName;
//    private List<Integer> giftPrice;
//    private List<Integer> boughtCount;
//    private List<Integer> unitPrice;
    private String giftName;
    private Integer giftPrice;
    private Integer boughtCount;
    private Integer unitPrice;
    private Timestamp giftOrderDatetime;
    private Boolean giftOrderStatus;
    private Integer giftOrderAmount;

    public GiftOrderList(Integer giftOrderId, String userName, String giftName, Integer giftPrice, Integer boughtCount, Integer unitPrice, Timestamp giftOrderDatetime, Boolean giftOrderStatus, Integer giftOrderAmount) {
        this.giftOrderId = giftOrderId;
        this.userName = userName;
        this.giftName = giftName;
        this.giftPrice = giftPrice;
        this.boughtCount = boughtCount;
        this.unitPrice = unitPrice;
        this.giftOrderDatetime = giftOrderDatetime;
        this.giftOrderStatus = giftOrderStatus;
        this.giftOrderAmount = giftOrderAmount;
    }
}
