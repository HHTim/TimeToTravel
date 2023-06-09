# 土產訂單明細 table

-- DROP TABLE `gift_order_details`;


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


INSERT INTO gift_order_details (GIFT_ORDER_ID, GIFT_ID, BOUGHT_COUNT, UNIT_PRICE)
VALUES (1, 5, 2, 60),
       (2, 3, 1, 60),
       (3, 9, 3, 195),
       (4, 6, 2, 100),
       (5, 1, 1, 150),
       (6, 10, 4, 160),
       (7, 8, 2, 90),
       (8, 12, 1, 90),
       (9, 2, 3, 240),
       (10, 15, 2, 40),
       (11, 4, 1, 200),
       (12, 11, 3, 105),
       (13, 13, 2, 240),
       (14, 7, 1, 70),
       (15, 14, 4, 100);