# 土產照片 table
CREATE TABLE `gift_photos` (
  `GIFT_PHOTO_ID` int NOT NULL AUTO_INCREMENT COMMENT '土產照片編號',
  `GIFT_ID` int NOT NULL COMMENT '土產編號',
  `GIFT_PHOTO` longblob NOT NULL COMMENT '土產照片',
  PRIMARY KEY (`GIFT_PHOTO_ID`)
  # constraint FK_GIFT_PHOTOS_GIFT_ID foreign key (GIFT_ID) references GIFT (GIFT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='土產照片';

