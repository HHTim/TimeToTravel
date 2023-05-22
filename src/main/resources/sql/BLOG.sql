CREATE TABLE `BLOG` (
  `POST_ID` int NOT NULL AUTO_INCREMENT COMMENT '文章編號',
  `USER_ID` int DEFAULT NULL COMMENT '會員編號',
  `POST_TITLE` varchar(50) NOT NULL COMMENT '文章標題',
  `POST_CONTENT` varchar(2500) NOT NULL COMMENT '文章內容',
  `POST_DATE` datetime NOT NULL DEFAULT (now()) COMMENT '發表時間',
  `LIKES` int DEFAULT '0' COMMENT '按讚數',
  `POST_PHOTO` longblob COMMENT '文章圖片',
  `POST_TYPE_ID` tinyint NOT NULL COMMENT '文章類型編號',
  `POST_UPDATE_TIME` datetime DEFAULT NULL COMMENT '上次修改時間',
  `COMMENTS` int DEFAULT '0' COMMENT '留言數',
  PRIMARY KEY (`POST_ID`),
  KEY `BLOG_FK_USER_ID` (`USER_ID`),
  KEY `BLOG_FK_POST_TYPE_ID` (`POST_TYPE_ID`),
  CONSTRAINT `BLOG_FK_POST_TYPE_ID` FOREIGN KEY (`POST_TYPE_ID`) REFERENCES `ARTICLE_TYPE` (`POST_TYPE_ID`),
  CONSTRAINT `BLOG_FK_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `MEMBER` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部落格';

INSERT INTO `leo_test`.`BLOG`
(`USER_ID`,`POST_TITLE`,`POST_CONTENT`,`POST_PHOTO`,
`POST_TYPE_ID`,`POST_UPDATE_TIME`)
VALUES
('1','阿森測試','內容是這樣子的，雷電為右衛門',null,'6',null),
('1','阿森測試2','內容是這樣子的，千代富士',null,'6',null),
('2','假設別人測試3','內容是這樣子的，橫綱',null,'5',null),
('1','阿森測試4','內容是這樣子的4',null,'6',null),
('1','阿森測試5','內容是這樣子的5',null,'1',null),
('1','阿森測試6','內容是這樣子的，6士',null,'6',null),
('1','阿森測試7','內容是這樣子的，7士',null,'6',null),
('1','阿森測試8','內容是這樣子的，8士',null,'6',null),
('1','阿森測試9','內容是這樣子的，9',null,'6',null);