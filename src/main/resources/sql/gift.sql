# 土產 table
# 已做修改 GIFT_TYPE_ID INT 改成 STRING，新增一欄COLUMN GIFT_PHOTO:
# ALTER TABLE `gift` ADD COLUMN `GIFT_PHOTO` longblob COMMENT '房型照片';

DROP TABLE  `gift`;

CREATE TABLE `gift` (
    `GIFT_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產編號',
    `COM_ID` int NOT NULL COMMENT '商家編號',
    `GIFT_NAME` varchar(30) NOT NULL COMMENT '土產名稱',
    `GIFT_PRICE` int unsigned NOT NULL COMMENT '土產單價',
    `GIFT_STOCK` int unsigned NOT NULL DEFAULT '0' COMMENT '土產庫存',
    `GIFT_STATUS` tinyint(1) NOT NULL DEFAULT '0' COMMENT '土產狀態',
    `GIFT_TYPE_ID` varchar(30) NOT NULL COMMENT '土產分類編號',
    `GIFT_INTRO` varchar(300) NOT NULL COMMENT '土產介紹',
    `GIFT_PHOTO` longblob COMMENT '房型照片',
    PRIMARY KEY (`GIFT_ID`)
    # constraint FK_GIFT_COM_ID foreign key (COM_ID) references COMPANY (COM_ID)
    # constraint FK_GIFT_GIFT_TYPE_ID foreign key (GIFT_TYPE_ID) references GIFT_TYPE (GIFT_TYPE_ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產';


INSERT INTO `gift` (`COM_ID`, `GIFT_NAME`, `GIFT_PRICE`, `GIFT_STOCK`, `GIFT_STATUS`, `GIFT_TYPE_ID`, `GIFT_INTRO`)
VALUES
(1, '菜頭', 50, 100, 1, '冷凍食品', '好吃的菜頭'),
(2, '榴槤冰棒', 80, 50, 1, '彌月食品', '貓山王牌'),
(1, '芒果冰', 60, 200, 1, '熱門美食', '愛文芒果'),
(3, '草莓醬', 70, 80, 1, '熱門美食', '大湖特產'),
(2, '梅子酒', 90, 120, 1, '冷凍食品', '梅子釀酒'),
(3, '清酒', 65, 150, 1, '團購美食', '米釀成酒'),
(1, '威士忌', 55, 80, 1, '熱門美食', '這是土產G的介紹'),
(2, '貓罐頭', 75, 100, 1, '冷凍食品', '這是土產H的介紹'),
(3, '香氛蠟燭', 95, 60, 1, '熱門美食', '這是土產I的介紹'),
(1, '檳榔', 70, 90, 1, '冷凍食品', '這是土產J的介紹');