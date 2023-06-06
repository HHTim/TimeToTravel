CREATE VIEW VIEW_ROOM_ORDER_DETAIL
            (
             ORDER_ID,
             USER_ID,
             ROOM_ID,
             JOURNEY_ID,
             ORDER_DATETIME,
             ORDER_AMOUNT,
             ORDER_CHECK_IN,
             ORDER_CHECK_OUT,
             ORDER_RANK,
             ORDER_COMMENT,
             COM_ID,
             ROOM_NAME,
             ROOM_PRICE,
             ROOM_DESC,
             ROOM_RELEASE,
             ROOM_STOCK,
             ROOM_BED,
             ROOM_PEOPLE,
             ROOM_STATUS,
             ROOM_PHOTO
                )
AS
SELECT O.*,
       R.COM_ID,
       R.ROOM_NAME,
       R.ROOM_PRICE,
       R.ROOM_DESC,
       R.ROOM_RELEASE,
       R.ROOM_STOCK,
       R.ROOM_BED,
       R.ROOM_PEOPLE,
       R.ROOM_STATUS,
       R.ROOM_PHOTO
FROM ORDER_DETAIL O
         JOIN ROOM R ON O.ROOM_ID = R.ROOM_ID;
