DROP TABLE IF EXISTS `favorite_article`;
CREATE TABLE `favorite_article` (
  `FAVORITE_ARTICLE` int NOT NULL AUTO_INCREMENT COMMENT '文章收藏編號',
  `USER_ID` int DEFAULT NULL COMMENT '會員編號',
  `POST_ID` int NOT NULL COMMENT '文章編號',
  PRIMARY KEY (`FAVORITE_ARTICLE`)
)  COMMENT='文章收藏';

INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (9,1,4);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (10,1,5);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (11,1,6);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (13,1,8);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (16,1,1);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (18,1,7);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (19,1,2);
INSERT INTO `favorite_article` (`FAVORITE_ARTICLE`,`USER_ID`,`POST_ID`) VALUES (21,1,3);