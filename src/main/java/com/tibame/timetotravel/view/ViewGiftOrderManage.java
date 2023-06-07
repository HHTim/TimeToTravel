package com.tibame.timetotravel.view;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VIEW_GIFT_ORDER_MANAGE")
@Entity
public class ViewGiftOrderManage {

//   創建一個新的VIEW TABLE 方便取資料使用: 使用到的TABLE 有 GIFT、GIFT_ORDER、GIFT_ORDER_DETAILS
//    GIFT - COLUMN:GIFT_NAME、GIFT_PRICE、
//    GIFT_ORDER - COLUMN: GIFT_ORDER_ID、GIFT_ORDER_AMOUNT、GIFT_ORDER_DATETIME、GIFT_ORDER_STATUS
//    USER - COLUMN: USER ID、USER_NAME
//    GIFT_ORDER_DETAILS - COLUMN:  GIFT_ORDER_DETAILS_ID、BOUGHT_COUNT_UNIT_PRICE


//COLUMN 資料:
    //    GIFT_ORDER_ID             訂單編號
    //    USER ID                   會員
    //    USER_ACCOUNT              會員帳號
    //    GIFT_ORDER_AMOUNT         訂單總金額
    //    GIFT_ORDER_DATETIME       成立時間
    //    GIFT_ORDER_STATUS         訂單狀態 (0 已完成 / 1 未完成)

//LIGHTBOX 內資料:
    //            <H2> 商品訂購明細
    //    同上                      訂單編號
    //    USER_NAME                訂購人姓名
    //    商品明細:
       // GIFT_NAME                商品名稱
       // GIFT_PRICE               商品價格
       // BOUGHT_COUNT             訂購數量
       // UNIT_PRICE               (單一品項)價格 = GIFT_PRICE * BOUGHT_COUNT
       // 同上                      訂單金額 UNIT_PRICE * N
    //    同上                     成立時間(訂單日期)
    //    同上                     訂單狀態 (0 已完成 / 1 未完成)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIFT_ORDER_ID", insertable = false, updatable = false)
    private Integer giftOrderId;

    @Column(name = "GIFT_ORDER_AMOUNT")
    private Integer giftOrderAmount;

    @Column(name = "GIFT_ORDER_DATETIME", nullable = false)
    private Timestamp giftOrderDatetime;

    @Column(name = "GIFT_ORDER_STATUS", columnDefinition = "tinyint(1) default 0")
    private Boolean giftOrderStatus;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_ID", nullable = false)
    private Integer userId;

    @Column(name = "GIFT_NAME", nullable = false)
    private String giftName;

    @Column(name = "GIFT_PRICE", nullable = false, columnDefinition = "int unsigned")
    private Integer giftPrice;

    @Column(name = "GIFT_ORDER_DETAILS_ID", insertable = false, updatable = false)
    private Integer giftOrderDetailsId;

    @Column(name = "BOUGHT_COUNT", nullable = false, columnDefinition = "int unsigned")
    private Integer boughtCount;

    @Column(name = "UNIT_PRICE", nullable = false, columnDefinition = "int unsigned")
    private Integer unitPrice;

    @Column(name = "USER_ACCOUNT", nullable = false)
    private String userAccount;






}
