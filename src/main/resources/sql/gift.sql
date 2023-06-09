# 土產 table
# GIFT_TYPE_ID INT 改成 STRING，新增一欄COLUMN GIFT_PHOTO:

# DROP TABLE `gift`;

CREATE TABLE `gift` (
    `GIFT_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產編號',
    `COM_ID` int NOT NULL COMMENT '商家編號',
    `GIFT_NAME` varchar(128) NOT NULL COMMENT '土產名稱',
    `GIFT_PRICE` int unsigned NOT NULL COMMENT '土產單價',
    `GIFT_STOCK` int unsigned NOT NULL DEFAULT '0' COMMENT '土產庫存',
    `GIFT_STATUS` tinyint(1) NOT NULL DEFAULT '0' COMMENT '土產狀態',
    `GIFT_TYPE_ID` varchar(128) NOT NULL COMMENT '土產分類編號',
    `GIFT_INTRO` varchar(1000) NOT NULL COMMENT '土產介紹',
    `GIFT_PHOTO` longblob COMMENT '房型照片',
    PRIMARY KEY (`GIFT_ID`)
    # constraint FK_GIFT_COM_ID foreign key (COM_ID) references COMPANY (COM_ID)
    # constraint FK_GIFT_GIFT_TYPE_ID foreign key (GIFT_TYPE_ID) references GIFT_TYPE (GIFT_TYPE_ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產';


INSERT INTO `gift` (`COM_ID`, `GIFT_NAME`, `GIFT_PRICE`, `GIFT_STOCK`, `GIFT_STATUS`, `GIFT_TYPE_ID`, `GIFT_INTRO`)
VALUES
    (1, '脆皮雞腿', 150, 50, 1, '冷凍食品', '香脆可口的炸雞腿，外皮酥脆，內裡多汁，是理想的下午茶零食。'),
    (2, '滋補養生湯包', 80, 30, 1, '彌月食品', '滋補養生湯包內含豐富的草藥和食材，適合產後調養，補充體力與營養。'),
    (3, '台灣珍珠奶茶', 60, 100, 1, '熱門美食', '道地的台灣珍珠奶茶，濃郁的茶香搭配Q彈的珍珠，絕對讓你回味無窮。'),
    (4, '台灣特色小吃禮盒', 200, 20, 1, '團購美食', '精選台灣特色小吃，包含蚵仔煎、肉圓、豬血糕等，讓你一次品嚐多種美食。'),
    (5, '巧克力棒', 30, 80, 1, '團購美食', '香濃的巧克力口味，一口咬下即可感受到融化在嘴裡的美味。'),
    (6, '麥片餅乾', 50, 60, 1, '團購美食', '口感酥脆的麥片餅乾，充滿著營養價值，適合當做早餐或下午茶點心。'),
    (7, '芒果冰淇淋', 70, 40, 1, '冷凍食品', '新鮮芒果製成的冰淇淋，口感濃郁，帶著芒果的香甜，讓你忍不住一口接一口。'),
    (8, '綠茶口味餅乾', 45, 50, 1, '熱門美食', '清爽的綠茶口味，加入餅乾中，讓你同時滿足茶香和酥脆的口感。'),
    (9, '鹹蛋黃餅', 65, 30, 1, '彌月食品', '鹹蛋黃餅外層酥脆，內裡豐富的鹹蛋黃餡料，是下午茶的絕佳選擇。'),
    (10, '草莓果醬', 40, 60, 1, '熱門美食', '新鮮草莓製成的果醬，香甜多汁，適合搭配吐司或鬆餅享用。'),
    (11, '炸薯條', 35, 80, 1, '冷凍食品', '金黃酥脆的炸薯條，是下午茶或電影時的經典小吃之一。'),
    (12, '牛肉乾', 90, 40, 1, '熱門美食', '口感嚼勁十足的牛肉乾，肉香四溢，是適合外出旅行或郊遊時的伴手禮。'),
    (13, '迷你蛋糕禮盒', 120, 20, 1, '熱門美食', '精緻迷你蛋糕禮盒，包含多種口味，適合送禮或享用自己的小甜點。'),
    (14, '蒜味薯片', 25, 70, 1, '團購美食', '帶有蒜香的薯片，讓你一嚼下去就停不下來，適合聚會或電影夜。'),
    (15, '牛奶糖', 20, 100, 1, '熱門美食', '濃郁的牛奶味道，口感柔軟，是大人小孩都喜愛的經典糖果。');