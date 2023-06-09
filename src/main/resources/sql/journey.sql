DROP TABLE IF EXISTS JOURNEY;

CREATE TABLE JOURNEY
(
    JOURNEY_ID     INT           NOT NULL COMMENT '私房行程編號' AUTO_INCREMENT,
    COM_ID         INT           NOT NULL COMMENT '商家編號',
    JOURNEY_NAME   VARCHAR(100)  NOT NULL COMMENT '私房行程名稱',
    JOURNEY_PRICE  INT         	 NOT NULL COMMENT '私房行程價格',
    JOURNEY_DESC   VARCHAR(1000) NOT NULL COMMENT '私房行程描述',
    JOURNEY_PIC    LONGBLOB 			  COMMENT '私房行程照片',
    JOURNEY_STATUS TINYINT(1)    NOT NULL COMMENT '私房行程狀態',
    PRIMARY KEY (JOURNEY_ID)
#     CONSTRAINT FK_JOURNEY_COM_ID FOREIGN KEY (COM_ID) REFERENCES COMPANY (COM_ID)
) COMMENT '私房行程資料表';

INSERT INTO JOURNEY(COM_ID, JOURNEY_NAME, JOURNEY_PRICE, JOURNEY_DESC, JOURNEY_PIC, JOURNEY_STATUS)
VALUES (1, '2024日月潭跨年晚會', 9500, '2023年的最後一天，讓我們齊聚日月潭一同迎接2024年', NULL, 1),
       (1, '樂旅日月潭一泊二食專案', 10500, '傾聽著蟲鳴鳥叫聲，漫遊日月湖畔，與我們來場輕旅行的約會吧^^', NULL, 1),
       (1, '蘭陽人文半日遊', 2500, '羅東林場文化園區(參觀森林火車老火車頭、林場日式老街、儲木池、舊鐵軌..) ', NULL, 1),
       (1, '梅花湖樂活之旅', 500, '梅花湖環湖步道漫步(環湖步道漫步或自費租單車環湖)', NULL, 1)
;

INSERT INTO JOURNEY(COM_ID, JOURNEY_NAME, JOURNEY_PRICE, JOURNEY_DESC, JOURNEY_PIC, JOURNEY_STATUS)
VALUES (2, '幾米城市小旅行', 800, '幾米廣場（記憶旅人的片刻風景）', NULL, 1),
       (2, '台南南風俱樂部-旗袍租借體驗', 1500, '感受全台古蹟密度最高的古城魅力來旅行吧！台南可以這樣體驗～', NULL, 1),
       (2, '台南安平-帆船遊艇體驗', 5000, '來到這麼美的遊艇碼頭，怎麼能不找個機會到海上搭搭遊艇、拍拍照呢', NULL, 1),
       (2, '台南四大月老廟參拜半日遊', 500, '月老拜好拜滿求得好姻緣！台南4大月老廟，參拜用途大不同，想要擺脫單身狗來這裡！', NULL, 1)
;

INSERT INTO JOURNEY(COM_ID, JOURNEY_NAME, JOURNEY_PRICE, JOURNEY_DESC, JOURNEY_PIC, JOURNEY_STATUS)
VALUES (3, '(新竹這樣玩)內灣線一日遊', 3000, '搭上內灣線火車來趟內灣線一日遊，在內灣線各站尋找線索解謎', NULL, 1),
       (3, '苗栗雲水度假森林．四十二份湧泉生態步道', 1550, '帶你到猶如置身童話世界般的「蘇維拉莊園」、電影《賽德克巴萊》主要拍攝場景之一「神仙谷瀑布」', NULL, 1),
       (3, '南投麝香木新祕境，紫色風暴開滿山坡', 2050, '在南投、雲林都不少麝香木秘境，開花時呈現一片粉紫色，隨風搖曳', NULL, 1),
       (3, '10條經典馬祖步道，走在山海之間的山脊步道', 2550, '有世界級的「螺山自然步道」、360度觀景台的「 東洋山步道」', NULL, 1)
;


SELECT *
FROM JOURNEY;
