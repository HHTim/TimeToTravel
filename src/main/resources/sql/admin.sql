CREATE TABLE ADMIN (
	ADMIN_ID INT AUTO_INCREMENT COMMENT '管理者編號',
	ADMIN_ACCOUNT VARCHAR(50) NOT NULL COMMENT '管理者帳號',
    ADMIN_PASSWORD VARCHAR(50) NOT NULL COMMENT '管理者密碼',
    ADMIN_NAME VARCHAR(50) NOT NULL COMMENT '管理者名稱',
    ADMIN_AVATAR LONGBLOB COMMENT '管理者圖片',
    ADMIN_NEWS_STATUS tinyint(1) COMMENT '管理者消息狀態',
    PRIMARY KEY(ADMIN_ID)
) COMMENT '管理者資料表';

INSERT INTO ADMIN(ADMIN_ID,ADMIN_ACCOUNT,ADMIN_PASSWORD,ADMIN_NAME,ADMIN_AVATAR,ADMIN_NEWS_STATUS)
VALUES(1,"Admin","password","Admin", NULL, 0);

SELECT * FROM ADMIN;

#DELETE FROM ADMIN;

#DROP TABLE ADMIN;
