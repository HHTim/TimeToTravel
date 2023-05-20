# 土產 table
CREATE TABLE `gift` (
  `GIFT_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產編號',
  `COM_ID` int NOT NULL COMMENT '商家編號',
  `GIFT_NAME` varchar(30) NOT NULL COMMENT '土產名稱',
  `GIFT_PRICE` int unsigned NOT NULL COMMENT '土產單價',
  `GIFT_STOCK` int unsigned NOT NULL DEFAULT '0' COMMENT '土產庫存',
  `GIFT_STATUS` tinyint(1) NOT NULL DEFAULT '0' COMMENT '土產狀態',
  `GIFT_TYPE_ID` int NOT NULL COMMENT '土產分類編號',
  `GIFT_INTRO` varchar(300) NOT NULL COMMENT '土產介紹',
  PRIMARY KEY (`GIFT_ID`)
  # constraint FK_GIFT_COM_ID foreign key (COM_ID) references COMPANY (COM_ID)
  # constraint FK_GIFT_GIFT_TYPE_ID foreign key (GIFT_TYPE_ID) references GIFT_TYPE (GIFT_TYPE_ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產';

