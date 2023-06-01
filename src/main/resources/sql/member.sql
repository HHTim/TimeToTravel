CREATE TABLE `member` (
  `USER_ID` int NOT NULL AUTO_INCREMENT COMMENT '會員編號',
  `USER_ACCOUNT` varchar(50) NOT NULL COMMENT '會員帳號',
  `USER_PASSWORD` varchar(50) NOT NULL COMMENT '會員密碼',
  `USER_NAME` varchar(50) NOT NULL COMMENT '會員姓名',
  `USER_NICKNAME` varchar(50) DEFAULT NULL COMMENT '會員暱稱',
  `USER_GENDER` tinyint(1) NOT NULL COMMENT '會員性別',
  `USER_PHONE` char(10) DEFAULT NULL COMMENT '會員電話',
  `USER_BIRTHDATE` date NOT NULL COMMENT '會員生日',
  `USER_EMAIL` varchar(50) NOT NULL COMMENT '會員信箱',
  `USER_AVATAR` longblob COMMENT '會員頭像',
  `USER_SIGNDATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '會員註冊日期',
  `USER_STATUS` tinyint(1) NOT NULL COMMENT '使用者狀態',
  `NEWS_STATUS` tinyint(1) NOT NULL COMMENT '消息狀態 ',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='會員';


INSERT INTO `MEMBER` (`USER_ACCOUNT`, `USER_PASSWORD`, `USER_NAME`, `USER_NICKNAME`, `USER_GENDER`, `USER_PHONE`, `USER_BIRTHDATE`, `USER_EMAIL`, `USER_AVATAR`, `USER_SIGNDATE`, `USER_STATUS`, `NEWS_STATUS`)
VALUES
    ('account1', 'password1', '會員1', '暱稱1', 1, '0934567890', '1990-01-01', 'email1@example.com', NULL, CURRENT_TIMESTAMP, 1, 1),
    ('account2', 'password2', '會員2', '暱稱2', 0, '0976543210', '1990-02-02', 'email2@example.com', NULL, CURRENT_TIMESTAMP, 1, 1),
    ('account3', 'password3', '會員3', '暱稱3', 1, '0955555555', '1990-03-03', 'email3@example.com', NULL, CURRENT_TIMESTAMP, 1, 1),
    ('account4', 'password4', '會員4', '暱稱4', 0, '0931111111', '1990-04-04', 'email4@example.com', NULL, CURRENT_TIMESTAMP, 1, 1),
    ('account5', 'password5', '會員5', '暱稱5', 1, '0915369999', '1990-05-05', 'email5@example.com', NULL, CURRENT_TIMESTAMP, 1, 1);

