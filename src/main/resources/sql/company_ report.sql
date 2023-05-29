CREATE TABLE `company report` (
  `REPORT_ID` int NOT NULL AUTO_INCREMENT COMMENT '檢舉編號',
  `USER_ID` int DEFAULT NULL COMMENT '(檢舉人)會員編號',
  `COM_ID` int DEFAULT NULL COMMENT '(被檢舉)商家編號',
  `REPORT_TITLE` varchar(50) NOT NULL COMMENT '檢舉標題',
  `REPORT_CONTENT` varchar(300) NOT NULL COMMENT '檢舉內容',
  `REPORT_DATE` datetime NOT NULL COMMENT '檢舉時間',
  `REPORT_STATUS` tinyint(1) NOT NULL COMMENT '處理狀態',
  PRIMARY KEY (`REPORT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商家檢舉';

INSERT INTO `COMPANY_REPORT` (`USER_ID`, `COM_ID`, `REPORT_TITLE`, `REPORT_CONTENT`, `REPORT_DATE`, `REPORT_STATUS`)
VALUES
    (1, 3, '檢舉標題1', '檢舉內容1', CURRENT_TIMESTAMP, 1),
    (2, 4, '檢舉標題2', '檢舉內容2', CURRENT_TIMESTAMP, 1),
    (3, 5, '檢舉標題3', '檢舉內容3', CURRENT_TIMESTAMP, 0),
    (4, 3, '檢舉標題4', '檢舉內容4', CURRENT_TIMESTAMP, 1),
    (5, 2, '檢舉標題5', '檢舉內容5', CURRENT_TIMESTAMP, 0);