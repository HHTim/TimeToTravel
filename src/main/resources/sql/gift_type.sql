# 土產分類 table
CREATE TABLE `gift_type` (
  `GIFT_TYPE_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產分類編號',
  `GIFT_TYPE_NAME` varchar(30) NOT NULL COMMENT '分類名稱',
  PRIMARY KEY (`GIFT_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產分類';

INSERT INTO `gift_type` (`GIFT_TYPE_NAME`)
VALUES ('冷凍食品'),
('彌月食品'),
('熱賣食品'),
('店取限定'),
('團購美食');