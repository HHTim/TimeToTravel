-- 土產追蹤清單 table

# DROP TABLE `gift_follow`;

CREATE TABLE `gift_follow` (
  `GIFT_FOLLOW_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產追蹤清單編號',
  `GIFT_ID` int NOT NULL COMMENT '土產編號',
  `USER_ID` int NOT NULL COMMENT '會員編號',
  PRIMARY KEY (`GIFT_FOLLOW_ID`)
  # constraint FK_GIFT_FOLLOW_GIFT_ID foreign key (GIFT_ID) references GIFT (GIFT_ID)
  # constraint FK_GIFT_FOLLOW_USER_ID foreign key (USER_ID) references MEMBER (USER_ID)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產追蹤清單';

INSERT INTO `gift_follow` (`GIFT_ID`, `USER_ID`)
VALUES
    (1, 1),
    (2, 2),
    (3, 1),
    (4, 6),
    (5, 2),
    (8, 10),
    (12, 10),
    (15, 1),
    (4, 6),
    (6, 2),
    (9, 6),
    (9, 2),
    (3, 2),
    (7, 3),
    (11, 5);



