DROP VIEW VIEW_A2U_MSG;

CREATE VIEW VIEW_A2U_MSG AS SELECT a2u.*, a.*, u.*
FROM ADMIN_TO_USER a2u left join ADMIN a 
		on a2u.A2U_SENDER_ID = a.ADMIN_ID left join USER u 
        on a2u.A2U_RECEIVER_ID = u.USER_ID;
        
