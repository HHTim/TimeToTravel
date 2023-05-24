CREATE TABLE `ROOM`
(
    `ROOM_ID`        INT          NOT NULL AUTO_INCREMENT COMMENT '房型編號',
    `COM_ID`         INT          NOT NULL COMMENT '商家編號',
    `ROOM_NAME`      VARCHAR(50)  NOT NULL COMMENT '房型名稱',
    `ROOM_PRICE`     INT          NOT NULL COMMENT '房型價格',
    `ROOM_DESC`      VARCHAR(500) NOT NULL COMMENT '房型描述',
    `ROOM_RELEASE`   DATE         NOT NULL DEFAULT (NOW()) COMMENT '房型上架日期',
    `ROOM_WIFI`      TINYINT(1)   NOT NULL COMMENT '是否有WIFI',
    `ROOM_PET`       TINYINT(1)   NOT NULL COMMENT '是否有寵物',
    `ROOM_BREAKFAST` TINYINT(1)   NOT NULL COMMENT '是否有早餐',
    `ROOM_PARKING`   TINYINT(1)   NOT NULL COMMENT '是否有停車場',
    `ROOM_SMOKING`   TINYINT(1)   NOT NULL COMMENT '是否可吸菸',
    `ROOM_24HOURS`   TINYINT(1)   NOT NULL COMMENT '是否24小時服務',
    `ROOM_STOCK`     INT          NOT NULL COMMENT '房型庫存',
    `ROOM_BED`       VARCHAR(30)  NOT NULL COMMENT '房型床位',
    `ROOM_PEOPLE`    INT          NOT NULL COMMENT '房型人數',
    `ROOM_STATUS`    TINYINT(1)   NOT NULL COMMENT '房型狀態',
    PRIMARY KEY (`ROOM_ID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_0900_AI_CI COMMENT ='房型資料表';

SELECT *
FROM TIMETOTRAVEL.ROOM;

# USE TIMETOTRAVEL;
#
# DROP TABLE ROOM;

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (1, '豪華二人房', 9000, '這是一間很豪華的房間', 0, 0, 1, 0, 1, 0, 2, '一張雙人床', 2, 1),
       (1, '豪華三人房', 10000, '這是一間很豪華的房間', 1, 0, 1, 1, 1, 1, 2, '一張雙人床', 3, 1),
       (1, '豪華四人房', 12000, '這是一間很豪華的房間', 0, 1, 1, 0, 1, 0, 1, '一張雙人床', 4, 1),
       (1, '豪華六人房', 15000, '這是一間很豪華的房間', 0, 0, 0, 1, 1, 1, 1, '一張雙人床', 6, 1);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (2, '普通二人房', 10000, '這是一間很豪華的房間', 0, 0, 1, 0, 1, 0, 2, '一張雙人床', 2, 1),
       (2, '普通三人房', 15000, '這是一間很豪華的房間', 1, 0, 1, 1, 1, 1, 2, '一張雙人床', 3, 1),
       (2, '普通四人房', 15000, '這是一間很豪華的房間', 0, 1, 1, 0, 1, 0, 1, '一張雙人床', 4, 1),
       (2, '普通六人房', 20000, '這是一間很豪華的房間', 0, 0, 0, 1, 1, 1, 1, '一張雙人床', 6, 1);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (3, '二人房', 10000, '這是一間很豪華的房間', 0, 0, 1, 0, 1, 0, 2, '一張雙人床', 2, 1),
       (3, '四人房', 15000, '這是一間很豪華的房間', 0, 1, 1, 0, 1, 0, 1, '一張雙人床', 4, 1),
       (3, '六人房', 20000, '這是一間很豪華的房間', 0, 0, 0, 1, 1, 1, 1, '一張雙人床', 6, 1);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (4, '二人房', 15000, '這是一間很豪華的房間', 0, 0, 1, 0, 1, 0, 1, '一張雙人床', 2, 1),
       (4, '四人房', 15000, '這是一間很豪華的房間', 0, 1, 1, 0, 1, 0, 1, '一張雙人床', 4, 1),
       (4, '六人房', 15000, '這是一間很豪華的房間', 0, 0, 0, 1, 1, 1, 1, '一張雙人床', 6, 1);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (5, '超普通二人房', 8000, '這是一間很豪華的房間', 0, 0, 1, 0, 1, 0, 1, '一張雙人床', 2, 1),
       (5, '超普通三人房', 8000, '這是一間很豪華的房間', 1, 0, 1, 1, 1, 1, 1, '一張雙人床', 3, 1),
       (5, '超普通四人房', 18000, '這是一間很豪華的房間', 0, 1, 1, 0, 1, 0, 1, '一張雙人床', 4, 1),
       (5, '超普通六人房', 18000, '這是一間很豪華的房間', 0, 0, 0, 1, 1, 1, 1, '一張雙人床', 6, 1);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (6, '新二人房', 8888, '這是一間很豪華的房間', 1, 0, 1, 0, 1, 0, 1, '一張雙人床', 2, 1),
       (6, '新三人房', 8888, '這是一間很豪華的房間', 1, 0, 1, 1, 1, 1, 1, '一張雙人床', 3, 1),
       (6, '新四人房', 18888, '這是一間很豪華的房間', 1, 1, 1, 0, 1, 0, 1, '一張雙人床', 4, 1),
       (6, '新六人房', 18888, '這是一間很豪華的房間', 1, 0, 0, 1, 1, 1, 1, '一張雙人床', 6, 1);
