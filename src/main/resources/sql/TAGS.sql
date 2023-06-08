DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `TAG_ID` int NOT NULL AUTO_INCREMENT COMMENT '標籤編號',
  `TAG` varchar(30) NOT NULL COMMENT '標籤',
  PRIMARY KEY (`TAG_ID`)
)  COMMENT='標籤種類';

INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (1,'相撲');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (2,'傳奇');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (3,'大相撲');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (7,'問題');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (8,'eee');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (9,'台南');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (10,'赤崁樓');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (11,'七股鹽山');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (12,'一日遊');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (13,'高雄');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (14,'宜蘭');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (15,'自駕');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (16,'塞車');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (17,'台東');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (18,'台東美術館');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (19,'住宿');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (20,'花東');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (21,'花蓮');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (22,'熱氣球');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (23,'東部');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (24,'疫情');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (25,'海景');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (26,'台11線');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (27,'三仙台');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (28,'奇美博物館');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (29,'單車');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (30,'環島');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (31,'糾團');
INSERT INTO `tags` (`TAG_ID`,`TAG`) VALUES (32,'礁溪');