CREATE TABLE `TAGS` (
  `TAG_ID` int NOT NULL AUTO_INCREMENT COMMENT '標籤編號',
  `TAG` varchar(30) NOT NULL COMMENT '標籤',
  PRIMARY KEY (`TAG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='標籤種類';

INSERT INTO `TAGS`
(`TAG`) VALUES ('相撲'),('傳奇');
INSERT INTO `TAGS`
(`TAG`) VALUES ('大相撲');