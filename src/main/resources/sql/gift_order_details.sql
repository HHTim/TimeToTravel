# 土產訂單明細 table
CREATE TABLE `gift_order_details` (
  `GIFT_ORDER_DETAILS_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產訂單明細編號',
  `GIFT_ORDER_ID` int NOT NULL COMMENT '土產訂單編號',
  `GIFT_ID` int NOT NULL COMMENT '土產編號',
  `BOUGHT_COUNT` int unsigned NOT NULL COMMENT '購買數量',
  `UNIT_PRICE` int unsigned NOT NULL COMMENT '金額',
  PRIMARY KEY (`GIFT_ORDER_DETAILS_ID`)
  # constraint FK_GIFT_ORDER_DETAILS_GIFT_ORDER_ID foreign key (GIFT_ORDER_ID) references GIFT_ORDER (GIFT_ORDER_ID)
  # constraint FK_GIFT_ORDER_DETAILS_GIFT_ID foreign key (GIFT_ID) references GIFT (GIFT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產訂單明細';





INSERT INTO `gift_order_details` (`GIFT_ORDER_ID`, `GIFT_ID`, `BOUGHT_COUNT`, `UNIT_PRICE`)
VALUES (1001, 2, 10, 10),
       (1002, 1, 20, 20),
       (1003, 3, 15, 15),
       (1004, 2, 12, 12),
       (1002, 4, 20, 20);