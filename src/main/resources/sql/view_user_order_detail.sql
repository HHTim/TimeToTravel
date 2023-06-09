CREATE VIEW VIEW_USER_ORDER_DETAIL
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
             USER_ACCOUNT,
             USER_PASSWORD,
             USER_NAME,
             USER_PHONE,
             USER_NICKNAME,
             USER_AVATAR,
             USER_GENDER,
             USER_BIRTHDATE,
             USER_SIGN_DATETIME,
             USER_EMAIL,
             USER_STATUS,
             USER_NEWS_STATUS
                )
AS
SELECT O.*,
       U.USER_ACCOUNT,
       U.USER_PASSWORD,
       U.USER_NAME,
       U.USER_PHONE,
       U.USER_NICKNAME,
       U.USER_AVATAR,
       U.USER_GENDER,
       U.USER_BIRTHDATE,
       U.USER_SIGN_DATETIME,
       U.USER_EMAIL,
       U.USER_STATUS,
       U.USER_NEWS_STATUS
FROM ORDER_DETAIL O
         JOIN USER U
              ON O.USER_ID = U.USER_ID;

SELECT *
FROM VIEW_USER_ORDER_DETAIL;

SELECT *
FROM VIEW_USER_ORDER_DETAIL
WHERE ROOM_ID = 7;

# DROP VIEW VIEW_USER_ORDER_DETAIL;



