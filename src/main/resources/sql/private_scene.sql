DROP TABLE IF EXISTS PRIVATE_SCENE;

CREATE TABLE PRIVATE_SCENE
(
    PRIVATE_SCENE_ID   INT         	 NOT NULL COMMENT '私房景點編號' AUTO_INCREMENT,
    COM_ID             INT         	 NOT NULL COMMENT '商家編號',
    PRIVATE_SCENE_NAME VARCHAR(100)  NOT NULL COMMENT '私房景點名稱',
    PRIVATE_SCENE_DESC VARCHAR(1000) NOT NULL COMMENT '私房景點描述',
    PRIVATE_SCENE_PIC  LONGBLOB COMMENT '私房景點照片',
    PRIMARY KEY (PRIVATE_SCENE_ID)
#     CONSTRAINT FK_PRIVATE_SCENE_COM_ID FOREIGN KEY (COM_ID) REFERENCES COMPANY (COM_ID)
) COMMENT '私房景點資料表';

INSERT INTO PRIVATE_SCENE(COM_ID, PRIVATE_SCENE_NAME, PRIVATE_SCENE_DESC, PRIVATE_SCENE_PIC)
VALUES (1, '新竹海之聲', '海之聲被稱「新版海之聲」，在新竹一直以來都是許多攝影師熱門拍照秘境之一', NULL),
       (1, '台中米奇樹IG打卡新亮點！北屯萬坪公園', '形似米老鼠的米奇樹豪好拍！有著落羽松大道的南興公園新地標', NULL),
       (1, '南寮魚鱗天梯', '自南寮旅遊服務中心往風箏賽場大草坪方向走，跨越草坪後，即可到達。', NULL);

INSERT INTO PRIVATE_SCENE(COM_ID, PRIVATE_SCENE_NAME, PRIVATE_SCENE_DESC, PRIVATE_SCENE_PIC)
VALUES (2, '財伯觀光果園', '新竹尖石鄉櫻花除了司馬庫斯，還有「財伯觀光果園」，免費入園，佛心來著!', NULL),
       (2, '新港安和稻草卷', '一捲捲咖啡口味的「捲心酥」散落在田間，搭配秋天乾淨空氣下的藍天白雲', NULL),
       (2, '新竹幸福山丘HappyHill', '在幸福山丘上有著青翠柔軟的草地，穿梭在大樹間的空中走步道，', NULL);

INSERT INTO PRIVATE_SCENE(COM_ID, PRIVATE_SCENE_NAME, PRIVATE_SCENE_DESC, PRIVATE_SCENE_PIC)
VALUES (3, '苦楝花綠色隧道', '全台最長苦楝綠色隧道。朴子溪淺紫樹花綿延2公里，三月天、淺紫色小花茂密綻放了', NULL),
       (3, '台東景點推薦加路蘭海岸', '取名為「我的好朋友」的裝置藝術作品，上面寫道：「歡迎過路的旅者，有一個輕鬆愉快的旅程！」', NULL),
       (3, '台東白色陋屋', '這棟白色陋屋也被稱為「台東阿伯小白屋」、「台版霍爾的移動城堡」', NULL);


SELECT *
FROM PRIVATE_SCENE;
