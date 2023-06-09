CREATE VIEW VIEW_COMPANY_ROOM
            (
             COM_ID,
             COM_ACCOUNT,
             COM_PASSWORD,
             COM_NAME,
             COM_ADDRESS,
             COM_MANAGER,
             COM_PHONE,
             COM_TAXID,
             COM_SIGNDATE,
             COM_EMAIL,
             COM_STATUS,
             COM_LONGITUDE,
             COM_LATITUDE,
             COM_AVATAR,
             COM_NEWS_STATUS,
             ROOM_ID,
             ROOM_NAME,
             ROOM_PRICE,
             ROOM_DESC,
             ROOM_RELEASE,
             ROOM_WIFI,
             ROOM_PET,
             ROOM_BREAKFAST,
             ROOM_PARKING,
             ROOM_SMOKING,
             ROOM_24HOURS,
             ROOM_STOCK,
             ROOM_BED,
             ROOM_PEOPLE,
             ROOM_STATUS,
             ROOM_PHOTO
                )
AS
SELECT C.*,
       R.ROOM_ID,
       R.ROOM_NAME,
       R.ROOM_PRICE,
       R.ROOM_DESC,
       R.ROOM_RELEASE,
       R.ROOM_WIFI,
       R.ROOM_PET,
       R.ROOM_BREAKFAST,
       R.ROOM_PARKING,
       R.ROOM_SMOKING,
       R.ROOM_24HOURS,
       R.ROOM_STOCK,
       R.ROOM_BED,
       R.ROOM_PEOPLE,
       R.ROOM_STATUS,
       R.ROOM_PHOTO
FROM COMPANY C
         LEFT JOIN ROOM R
                   ON C.COM_ID = R.COM_ID;

SELECT *
FROM VIEW_COMPANY_ROOM;

DROP VIEW VIEW_COMPANY_ROOM;
