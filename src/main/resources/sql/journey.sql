CREATE TABLE JOURNEY
(
    JOURNEY_ID     INT         NOT NULL COMMENT '私房行程編號' AUTO_INCREMENT,
    COM_ID         INT         NOT NULL COMMENT '商家編號',
    JOURNEY_NAME   VARCHAR(30) NOT NULL COMMENT '私房行程名稱',
    JOURNEY_PRICE  INT         NOT NULL COMMENT '私房行程價格',
    JOURNEY_DESC   VARCHAR(50) NOT NULL COMMENT '私房行程描述',
    JOURNEY_PIC    LONGBLOB COMMENT '私房行程照片',
    JOURNEY_STATUS TINYINT(1)  NOT NULL COMMENT '私房行程狀態',
    PRIMARY KEY (JOURNEY_ID)
#     CONSTRAINT FK_JOURNEY_COM_ID FOREIGN KEY (COM_ID) REFERENCES COMPANY (COM_ID)
) COMMENT '私房行程資料表';

INSERT INTO JOURNEY(COM_ID, JOURNEY_NAME, JOURNEY_PRICE, JOURNEY_DESC, JOURNEY_PIC, JOURNEY_STATUS)
VALUES (1, '好棒棒行程', 9500, '這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔', NULL, 1),
       (1, '好普通行程', 10500, '這是好普通行程喔這是好普通行程喔好普通行程喔好普通行程喔好普通行程喔', NULL, 1),
       (1, '好盤行程', 12500, '這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程', NULL, 1),
       (1, '好土豪行程', 15500, '這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程', NULL, 1)
;

INSERT INTO JOURNEY(COM_ID, JOURNEY_NAME, JOURNEY_PRICE, JOURNEY_DESC, JOURNEY_PIC, JOURNEY_STATUS)
VALUES (2, '好棒棒行程', 8000, '這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔', NULL, 1),
       (2, '好普通行程', 12500, '這是好普通行程喔這是好普通行程喔好普通行程喔好普通行程喔好普通行程喔', NULL, 1),
       (2, '好盤行程', 15500, '這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程', NULL, 1),
       (2, '好土豪行程', 20500, '這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程', NULL, 1)
;

INSERT INTO JOURNEY(COM_ID, JOURNEY_NAME, JOURNEY_PRICE, JOURNEY_DESC, JOURNEY_PIC, JOURNEY_STATUS)
VALUES (3, '好棒棒行程', 8030, '這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔', NULL, 1),
       (3, '好普通行程', 12550, '這是好普通行程喔這是好普通行程喔好普通行程喔好普通行程喔好普通行程喔', NULL, 1),
       (3, '好盤行程', 15510, '這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程', NULL, 1),
       (3, '好土豪行程', 25500, '這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程', NULL, 1)
;

INSERT INTO JOURNEY(COM_ID, JOURNEY_NAME, JOURNEY_PRICE, JOURNEY_DESC, JOURNEY_PIC, JOURNEY_STATUS)
VALUES (4, '好棒棒行程', 9030, '這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔', NULL, 1),
       (4, '好普通行程', 15550, '這是好普通行程喔這是好普通行程喔好普通行程喔好普通行程喔好普通行程喔', NULL, 1),
       (4, '好盤行程', 20510, '這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程', NULL, 1),
       (4, '好土豪行程', 25050, '這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程', NULL, 1)
;

INSERT INTO JOURNEY(COM_ID, JOURNEY_NAME, JOURNEY_PRICE, JOURNEY_DESC, JOURNEY_PIC, JOURNEY_STATUS)
VALUES (5, '好棒棒行程', 10000, '這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔這是好棒棒行程喔', NULL, 1),
       (5, '好普通行程', 10050, '這是好普通行程喔這是好普通行程喔好普通行程喔好普通行程喔好普通行程喔', NULL, 1),
       (5, '好盤行程', 20000, '這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程這是好盤行程', NULL, 1),
       (5, '好土豪行程', 55555, '這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程這是好土豪行程', NULL, 1)
;


SELECT *
FROM COMPANY;



SELECT *
FROM JOURNEY;