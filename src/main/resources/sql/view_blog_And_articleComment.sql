
DROP VIEW IF EXISTS `default_blog`;   
CREATE VIEW default_blog AS
SELECT 
    b.POST_ID AS POST_ID, 
    b.USER_ID AS USER_ID, 
    b.POST_TITLE AS POST_TITLE, 
    b.POST_CONTENT AS POST_CONTENT, 
    b.POST_DATE AS POST_DATE, 
    b.LIKES AS LIKES, 
    b.POST_PHOTO AS POST_PHOTO, 
    b.POST_TYPE_ID AS POST_TYPE_ID, 
    b.POST_UPDATE_TIME AS POST_UPDATE_TIME, 
    b.COMMENTS AS COMMENTS,
    ac.USER_ID AS LAST_COMMENT_USER_ID, 
    ac.COMMENT_CONTENT AS LAST_COMMENT, 
    ac.COMMENT_DATETIME AS LAST_COMMENT_DATETIME, 
    at.POST_TYPE AS LAST_POST_TYPE,
    u.USER_NAME AS USER_NAME,
    u2.USER_NAME AS LAST_COMMENT_USER_NAME
FROM 
    blog b 
    LEFT JOIN (
        SELECT 
            c.* 
        FROM 
            article_comment c 
            INNER JOIN (
                SELECT 
                    POST_ID, 
                    MAX(COMMENT_DATETIME) AS MAX_COMMENT_DATETIME
                FROM 
                    article_comment
                GROUP BY 
                    POST_ID
            ) max_c ON c.POST_ID = max_c.POST_ID AND c.COMMENT_DATETIME = max_c.MAX_COMMENT_DATETIME
    ) ac ON b.POST_ID = ac.POST_ID
    LEFT JOIN article_type at ON b.POST_TYPE_ID = at.POST_TYPE_ID
    LEFT JOIN `user` u ON b.USER_ID = u.USER_ID
    LEFT JOIN `user` u2 ON ac.USER_ID = u2.USER_ID;
DROP VIEW IF EXISTS `view_article_comment`;    
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