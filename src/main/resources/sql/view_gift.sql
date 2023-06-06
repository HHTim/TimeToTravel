CREATE VIEW view_gift AS
SELECT g.*, c.COM_NAME
FROM gift g
LEFT JOIN company c on g.COM_ID = c.COM_ID;

select * from view_gift;
