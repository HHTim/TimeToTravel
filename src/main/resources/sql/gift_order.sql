# 土產訂單 table
# DROP TABLE gift_order;

CREATE TABLE `gift_order` (
    `GIFT_ORDER_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產訂單編號',
    `USER_ID` int NOT NULL COMMENT '會員編號',
    `GIFT_ORDER_AMOUNT` int unsigned DEFAULT 0 COMMENT '總金額',
    `GIFT_ORDER_DATETIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下訂時間',
    `GIFT_ORDER_STATUS` tinyint(1) DEFAULT 0 COMMENT '訂單狀態',
    PRIMARY KEY (`GIFT_ORDER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產訂單';

INSERT INTO gift_order (USER_ID, GIFT_ORDER_AMOUNT, GIFT_ORDER_DATETIME, GIFT_ORDER_STATUS)
VALUES (1, 100, '2023-05-01 10:00:00', 0),
       (2, 150, '2023-05-05 14:30:00', 1),
       (3, 200, '2023-05-10 16:45:00', 0),
       (4, 120, '2023-06-02 09:15:00', 1),
       (5, 80, '2023-06-05 11:30:00', 0),
       (6, 90, '2023-06-08 13:45:00', 1),
       (1, 180, '2023-07-01 15:00:00', 1),
       (2, 200, '2023-07-05 17:30:00', 1),
       (3, 150, '2023-07-10 10:45:00', 0),
       (4, 160, '2023-08-02 12:15:00', 1),
       (5, 140, '2023-08-05 14:30:00', 0),
       (6, 90, '2023-08-08 16:45:00', 1),
       (1, 120, '2023-08-12 09:00:00', 0),
       (2, 110, '2023-08-15 11:15:00', 0),
       (3, 130, '2023-08-20 13:30:00', 1);