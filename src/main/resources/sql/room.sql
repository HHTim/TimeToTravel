DROP TABLE IF EXISTS ROOM;

CREATE TABLE `ROOM`
(
    `ROOM_ID`        INT          NOT NULL AUTO_INCREMENT COMMENT '房型編號',
    `COM_ID`         INT          NOT NULL COMMENT '商家編號',
    `ROOM_NAME`      VARCHAR(100)  NOT NULL COMMENT '房型名稱',
    `ROOM_PRICE`     INT          NOT NULL COMMENT '房型價格',
    `ROOM_DESC`      VARCHAR(1000) NOT NULL COMMENT '房型描述',
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
    `ROOM_PHOTO`	 LONGBLOB			   COMMENT '房型照片',
    PRIMARY KEY (`ROOM_ID`)
) COMMENT ='房型資料表';

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS, ROOM_PHOTO)
VALUES (1, '日月潭日暮山景雙床房', 9000, '細膩規劃出9坪的簡約空間，讓山林富含的芬多精，隨意飄散在輕鬆假期。', 0, 0, 1, 0, 1, 0, 5, '雙人房', 2, 1 ,NULL),
       (1, '日月潭映月經典和洋房', 10000, '寧靜且富饒的漁村景致，讓您滿足且貪戀眼前所得到的感動雲影與湖光的交錯', 1, 0, 1, 1, 1, 1, 5, '雙人房', 2, 1, NULL),
       (1, '日月潭雅緻湖景套房', 12000, '房內以木質地板延伸至戶外的湖景嚮往，給您水天一色的釋然空間。', 0, 1, 1, 0, 1, 0, 10, '單人房', 1, 1, NULL),
       (1, '日月潭雅緻湖景四人房', 15000, '輕觸在悠然自得的湖光水色，一彈一指間的沈迷放肆與您眼前那一片垂手可得', 0, 0, 0, 1, 1, 1, 10, '四人房', 4, 1, NULL);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS, ROOM_PHOTO)
VALUES (2, '台中麗寶閃亮星空', 30000, '夢幻的星空裡有著無數顆星星，只要對著房間壁貼上最大顆的星星許願，今晚就會甜甜進入夢鄉', 0, 0, 1, 0, 1, 0, 5, '四人房', 4, 1, NULL),
       (2, '台中麗寶美味魔法師', 25000, '咻~熊大的魔法棒一揮，變出了許多美味可口的甜點讓你一進房就會不自覺想流口水', 1, 0, 1, 1, 1, 1, 10, '雙人房', 2, 1, NULL),
       (2, '台中麗寶航海冒險', 15000, '以藍白色調為基底的海洋風，象徵每次出發都會有個最棒的開始，給你無限的勇氣和力量。', 0, 1, 1, 0, 1, 0, 3, '四人房', 4, 1, NULL),
       (2, '台中麗寶浪漫滿屋', 20000, '為她/他製造一點點浪漫及驚喜，是生活中必備的儀式感，把日子詮釋成自己喜歡的樣子', 0, 0, 0, 1, 1, 1, 4, '雙人房', 2, 1, NULL);

INSERT INTO ROOM(COM_ID, ROOM_NAME, ROOM_PRICE, ROOM_DESC, ROOM_WIFI, ROOM_PET, ROOM_BREAKFAST,
                 ROOM_PARKING, ROOM_SMOKING, ROOM_24HOURS, ROOM_STOCK, ROOM_BED, ROOM_PEOPLE, ROOM_STATUS, ROOM_PHOTO)
VALUES (3, '墾丁一樓標準海景房', 10000, '陽光灑落、鳥鳴歡笑與您迎接美好早晨，通過綠草廊道直達泳池，享受旅人悠閒自在。', 0, 0, 1, 0, 1, 0, 3, '單人房', 1, 1, NULL),
       (3, '墾丁二樓家庭海景房', 20000, '簡單無華的純淨空間，有著木質溫潤感受，讓您一掃煩雜瑣事，輕鬆自在的寫下幸福篇章。', 0, 1, 1, 0, 1, 0, 5, '四人房', 4, 1, NULL),
       (3, '墾丁一樓雙人山景房', 15000, '時尚恬靜的舒適客房，軟綿的床鋪猶如飄逸藍天中的雲朵，匯集成墾丁美好時光', 0, 0, 0, 1, 1, 1, 10, '雙人房', 2, 1, NULL);

SELECT *
FROM TIMETOTRAVEL.ROOM;
