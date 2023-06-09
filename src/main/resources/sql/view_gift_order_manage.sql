-- 要先建立好 gift, gift_order, gift_order_details, user 後才可以建立 view 表

-- DROP VIEW view_gift_order_manage;


CREATE VIEW view_gift_order_manage AS
SELECT go.GIFT_ORDER_ID, u.USER_ID, u.USER_NAME, u.USER_ACCOUNT, go.GIFT_ORDER_AMOUNT,
       go.GIFT_ORDER_DATETIME, go.GIFT_ORDER_STATUS,
       god.GIFT_ORDER_DETAILS_ID, g.GIFT_NAME, g.GIFT_PRICE, god.BOUGHT_COUNT, god.UNIT_PRICE
FROM gift_order go
	LEFT JOIN user u ON go.USER_ID = u.USER_ID
	LEFT JOIN gift_order_details god ON go.GIFT_ORDER_ID = god.GIFT_ORDER_ID
	LEFT JOIN gift g ON god.GIFT_ID = g.GIFT_ID;