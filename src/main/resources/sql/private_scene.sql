CREATE TABLE PRIVATE_SCENE
(
    PRIVATE_SCENE_ID   INT         NOT NULL COMMENT '私房景點編號' AUTO_INCREMENT,
    COM_ID             INT         NOT NULL COMMENT '商家編號',
    PRIVATE_SCENE_NAME VARCHAR(30) NOT NULL COMMENT '私房景點名稱',
    PRIVATE_SCENE_DESC VARCHAR(50) NOT NULL COMMENT '私房景點描述',
    PRIVATE_SCENE_PIC  LONGBLOB COMMENT '私房景點照片',
    PRIMARY KEY (PRIVATE_SCENE_ID)
#     CONSTRAINT FK_PRIVATE_SCENE_COM_ID FOREIGN KEY (COM_ID) REFERENCES COMPANY (COM_ID)
) COMMENT '私房景點資料表';

INSERT INTO PRIVATE_SCENE(COM_ID, PRIVATE_SCENE_NAME, PRIVATE_SCENE_DESC, PRIVATE_SCENE_PIC)
VALUES (1, '景扁1', '這是景點1這是景點1這是景點1這是景點1這是景點1這是景點1', NULL),
       (1, '景扁2', '這是景點2這是景點2這是景點2這是景點2這是景點2這是景點2', NULL),
       (1, '景扁3', '這是景點3這是景點3這是景點3這是景點3這是景點3這是景點3', NULL);

INSERT INTO PRIVATE_SCENE(COM_ID, PRIVATE_SCENE_NAME, PRIVATE_SCENE_DESC, PRIVATE_SCENE_PIC)
VALUES (2, '景扁1', '這是景點1這是景點1這是景點1這是景點1這是景點1這是景點1', NULL),
       (2, '景扁2', '這是景點2這是景點2這是景點2這是景點2這是景點2這是景點2', NULL),
       (2, '景扁3', '這是景點3這是景點3這是景點3這是景點3這是景點3這是景點3', NULL);

INSERT INTO PRIVATE_SCENE(COM_ID, PRIVATE_SCENE_NAME, PRIVATE_SCENE_DESC, PRIVATE_SCENE_PIC)
VALUES (3, '景扁1', '這是景點1這是景點1這是景點1這是景點1這是景點1這是景點1', NULL),
       (3, '景扁2', '這是景點2這是景點2這是景點2這是景點2這是景點2這是景點2', NULL),
       (3, '景扁3', '這是景點3這是景點3這是景點3這是景點3這是景點3這是景點3', NULL);

INSERT INTO PRIVATE_SCENE(COM_ID, PRIVATE_SCENE_NAME, PRIVATE_SCENE_DESC, PRIVATE_SCENE_PIC)
VALUES (4, '景扁1', '這是景點1這是景點1這是景點1這是景點1這是景點1這是景點1', NULL),
       (4, '景扁2', '這是景點2這是景點2這是景點2這是景點2這是景點2這是景點2', NULL),
       (4, '景扁3', '這是景點3這是景點3這是景點3這是景點3這是景點3這是景點3', NULL);

INSERT INTO PRIVATE_SCENE(COM_ID, PRIVATE_SCENE_NAME, PRIVATE_SCENE_DESC, PRIVATE_SCENE_PIC)
VALUES (5, '景扁1', '這是景點1這是景點1這是景點1這是景點1這是景點1這是景點1', NULL),
       (5, '景扁2', '這是景點2這是景點2這是景點2這是景點2這是景點2這是景點2', NULL),
       (5, '景扁3', '這是景點3這是景點3這是景點3這是景點3這是景點3這是景點3', NULL);

SELECT *
FROM COMPANY;

SELECT *
FROM PRIVATE_SCENE;
