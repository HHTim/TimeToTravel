CREATE TABLE PUBLIC_SCENE
(
    SCENE_ID       INT AUTO_INCREMENT COMMENT '大眾景點編號',
    ADMIN_ID       INT COMMENT '管理者編號',
    SCENE_NAME     VARCHAR(30)   NOT NULL COMMENT '大眾景點名稱',
    SCENE_PHOTO    LONGBLOB COMMENT '大眾景點照片',
    SCENE_DESC     VARCHAR(1000) NOT NULL COMMENT '大眾景點描述',
    SCENE_ADDR     VARCHAR(150)  NOT NULL COMMENT '大眾景點地址',
    SCENE_LAT      VARCHAR(50)   NOT NULL COMMENT '大眾景點緯度',
    SCENE_LNG      VARCHAR(50)   NOT NULL COMMENT '大眾景點經度',
    SCENE_PLACE_ID VARCHAR(50)   NOT NULL COMMENT '大眾景點ID',
    PRIMARY KEY (SCENE_ID)
#   CONSTRAINT FK_PUBLIC_SCENE_ID FOREIGN KEY (ADMIN_ID) REFERENCES ADMIN(ADMIN_ID)
) COMMENT '大眾景點表';

DROP TABLE PUBLIC_SCENE;

INSERT INTO PUBLIC_SCENE(SCENE_ID, ADMIN_ID, SCENE_NAME, SCENE_PHTOT, SCENE_DESC, SCENE_ADDR, SCENE_LAT, SCENE_LNG)
VALUES (1, 1, "幾米公園", NULL,
        "「幾米主題廣場」位於宜蘭火車站南側，原為廢棄的鐵路局舊宿舍區，後經過重新規劃整建，除了保留原有的歷史建築與老樹綠蔭外，更以「記憶片刻風景」為主題，置入取自知名繪本作家「幾米」的場景所製成的裝置藝術，包含「向左走‧向右走」、「星空」…等繪本中的著名場景，都在此原貌重現。不但已成為拍照的著名場景，更是全台第一座幾米主題廣場。",
        "260宜蘭縣宜蘭市宜興路一段240號", "24.752733529527834", "121.75705744620144");

INSERT INTO PUBLIC_SCENE(SCENE_ID, ADMIN_ID, SCENE_NAME, SCENE_PHTOT, SCENE_DESC, SCENE_ADDR, SCENE_LAT, SCENE_LNG)
VALUES (2, 1, "伯朗咖啡城堡", NULL,
        "「伯朗咖啡城堡」充滿童話般浪漫的城堡，位於宜蘭外澳，擁有居高臨下的遼闊視野。天氣好的時候，外澳沙灘上的衝浪客和觸立在海洋中的龜山島皆清晰可見。餐廳內部空間寬敞而雅緻，成列的威士忌酒桶做為特色造景，更顯異國風味；旅人們可以點杯香醇的咖啡和精緻餐點，和三五好友愜意度過愉悅的下午時光。",
        "261宜蘭縣頭城鎮石空路95號", "24.88468763983836", "121.83884066304392");

SELECT *
FROM PUBLIC_SCENE;
#DROP TABLE PUBLIC_SCENE;
#DELETE FROM PUBLIC_SCENE;