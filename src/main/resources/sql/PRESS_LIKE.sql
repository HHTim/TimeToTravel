DROP TABLE IF EXISTS `press_like`;
CREATE TABLE `press_like` (
  `LIKE_NO` int NOT NULL AUTO_INCREMENT COMMENT '按讚流水號',
  `USER_ID` int NOT NULL COMMENT '會員編號',
  `POST_ID` int NOT NULL COMMENT '文章編號',
  PRIMARY KEY (`LIKE_NO`)
)  COMMENT='按讚';

INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (8,2,1);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (9,3,1);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (11,2,3);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (22,1,3);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (23,1,2);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (24,1,8);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (27,1,13);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (37,1,16);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (38,1,15);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (39,1,5);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (41,1,1);
INSERT INTO `press_like` (`LIKE_NO`,`USER_ID`,`POST_ID`) VALUES (43,1,9);