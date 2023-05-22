CREATE TABLE `USER` (
  `USER_ID` 			int  		 COMMENT '會員編號' NOT NULL AUTO_INCREMENT,
  `USER_ACCOUNT` 		varchar(50)  COMMENT '會員帳號' NOT NULL,
  `USER_PASSWORD` 		varchar(50)  COMMENT '會員密碼' NOT NULL,
  `USER_NAME` 			varchar(50)  COMMENT '會員姓名' NOT NULL,
  `USER_PHONE` 			varchar(50)  COMMENT '會員電話' NOT NULL,
  `USER_NICKNAME` 		varchar(50)  COMMENT '會員暱稱' DEFAULT NULL,
  `USER_AVATAR` 		longblob	 COMMENT '會員頭像',
  `USER_GENDER` 		bit(1) 		 COMMENT '會員性別' NOT NULL,
  `USER_BIRTHDATE` 		date 		 COMMENT '會員生日' NOT NULL,
  `USER_SIGN_DATETIME` 	datetime 	 COMMENT '註冊日期' NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_EMAIL` 			varchar(50)  COMMENT '會員信箱' NOT NULL,
  `USER_STATUS` 		bit(1)  	 COMMENT '會員狀態' NOT NULL,
  PRIMARY KEY (`USER_ID`)
) COMMENT '使用者資料表'
