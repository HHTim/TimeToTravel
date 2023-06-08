DROP TABLE IF EXISTS  `article_type`;
CREATE TABLE `article_type` (
  `POST_TYPE_ID` tinyint NOT NULL AUTO_INCREMENT COMMENT '文章類型編號',
  `POST_TYPE` varchar(50) NOT NULL COMMENT '文章類型',
  PRIMARY KEY (`POST_TYPE_ID`)
)  COMMENT='文章類型';

INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (1,'問題');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (2,'情報');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (3,'心得');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (4,'討論');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (5,'攻略');
INSERT INTO `article_type` (`POST_TYPE_ID`,`POST_TYPE`) VALUES (6,'其他');