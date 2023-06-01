# 土產 table
# 修改 GIFT_TYPE_ID integer 到 string ;

DROP TABLE `gift`;

CREATE TABLE `gift` (
  `GIFT_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產編號',
  `COM_ID` int NOT NULL COMMENT '商家編號',
  `GIFT_NAME` varchar(30) NOT NULL COMMENT '土產名稱',
  `GIFT_PRICE` int unsigned NOT NULL COMMENT '土產單價',
  `GIFT_STOCK` int unsigned NOT NULL DEFAULT '0' COMMENT '土產庫存',
  `GIFT_STATUS` tinyint(1) NOT NULL DEFAULT '0' COMMENT '土產狀態',
  `GIFT_TYPE_ID` varchar(30) NOT NULL COMMENT '土產分類編號',
  `GIFT_INTRO` varchar(300) NOT NULL COMMENT '土產介紹',
  PRIMARY KEY (`GIFT_ID`)
  # constraint FK_GIFT_COM_ID foreign key (COM_ID) references COMPANY (COM_ID)
  # constraint FK_GIFT_GIFT_TYPE_ID foreign key (GIFT_TYPE_ID) references GIFT_TYPE (GIFT_TYPE_ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產';

INSERT INTO `gift` (`COM_ID`, `GIFT_NAME`, `GIFT_PRICE`, `GIFT_STOCK`, `GIFT_STATUS`, `GIFT_TYPE_ID`, `GIFT_INTRO`)
VALUES
(1, '土產A', 50, 100, 1, '冷凍食品', '這是土產A的介紹'),
(2, '土產B', 80, 50, 1, '彌月食品', '這是土產B的介紹'),
(1, '土產C', 60, 200, 1, '冷凍食品', '這是土產C的介紹'),
(3, '土產D', 70, 80, 1, '熱門美食', '這是土產D的介紹'),
(2, '土產E', 90, 120, 1, '店取限定', '這是土產E的介紹'),
(3, '土產F', 65, 150, 1, '彌月食品', '這是土產F的介紹'),
(1, '土產G', 55, 80, 1, '冷凍食品', '這是土產G的介紹'),
(2, '土產H', 75, 100, 1, '熱門美食', '這是土產H的介紹'),
(3, '土產I', 95, 60, 1, '彌月食品', '這是土產I的介紹'),
(1, '土產J', 70, 90, 1, '團購美食', '這是土產J的介紹');