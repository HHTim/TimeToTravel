CREATE VIEW view_article_comment AS
SELECT 
    ac.COMMENT_NO,
    ac.POST_ID,
    ac.USER_ID,
    ac.COMMENT_CONTENT,
    ac.COMMENT_DATETIME,
    u.USER_NAME,
    u.USER_AVATAR
FROM 
    timetotravel.article_comment ac
    JOIN `user` u ON ac.USER_ID = u.USER_ID;