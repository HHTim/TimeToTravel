# 土產追蹤清單 table
CREATE TABLE `gift_follow` (
  `GIFT_FOLLOW_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產追蹤清單編號',
  `GIFT_ID` int NOT NULL COMMENT '土產編號',
  `USER_ID` int NOT NULL COMMENT '會員編號',
  PRIMARY KEY (`GIFT_FOLLOW_ID`)
  # constraint FK_GIFT_FOLLOW_GIFT_ID foreign key (GIFT_ID) references GIFT (GIFT_ID)
  # constraint FK_GIFT_FOLLOW_USER_ID foreign key (USER_ID) references MEMBER (USER_ID)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產追蹤清單';

