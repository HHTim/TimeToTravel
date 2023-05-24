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
) COMMENT ='房型資料表';

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (1, '邊緣單人房', 9000, '專為單身邊緣的你所設立', 0, 0, 1, 0, 1, 0, 2, '單人房', 1, 1),
       (1, '情侶雙人房', 10000, '鏡子絕對沒有鏡頭，預設絕對沒有攝影', 1, 0, 1, 1, 1, 1, 2, '雙人房', 2, 1),
       (1, '男男雙人房', 12000, '浴室提供滑滑的東西', 0, 1, 1, 0, 1, 0, 1, '雙人房', 2, 1),
       (1, '家庭四人房', 15000, '牆壁很薄請勿發出噪音', 0, 0, 0, 1, 1, 1, 1, '四人房', 4, 1);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (2, '總統單人房', 10000, '就是鳥籠啦', 0, 0, 1, 0, 1, 0, 2, '單人房', 1, 1),
       (2, '土城單人房', 15000, '扁水哥御用', 1, 0, 1, 1, 1, 1, 2, '單人房', 1, 1),
       (2, '婆媽四人房', 15000, '專為大聲婆的你們所設計，牆壁很厚!', 0, 1, 1, 0, 1, 0, 1, '四人房', 4, 1),
       (2, '寵物雙人房', 20000, '可以帶上你男友(狗)', 0, 0, 0, 1, 1, 1, 1, '雙人房', 2, 1);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS)
VALUES (3, '樂團四人房', 10000, '熱音社請洽談', 0, 0, 1, 0, 1, 0, 2, '四人房', 4, 1),
       (3, '乞丐單人房', 15000, '洪七公已簽約，放心休息吧', 0, 1, 1, 0, 1, 0, 1, '單人房', 1, 1),
       (3, '彼得單人房', 20000, '配有喉糖，安心服用', 0, 0, 0, 1, 1, 1, 1, '單人房', 1, 1);

SELECT *
FROM TIMETOTRAVEL.ROOM;