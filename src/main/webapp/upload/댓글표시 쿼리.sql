select n.id, n.title, n.writer_id, n.regdate, n.hit, n.files, 
      count(c.id) cmt_count from notice n
  left join comments c on n.id = c.notice_id
  group by n.id, n.title, n.writer_id, n.regdate, n.hit, n.files
  order by n.regdate desc;

 create view apt_notice_view as (  
 select n.id, n.title, n.writer_id, n.regdate, n.hit, n.files, 
      count(c.id) cmt_count from apt_notice n
  left join apt_comment c on n.id = c.notice_id
  group by n.id, n.title, n.writer_id, n.regdate, n.hit, n.files );
  
	SELECT * FROM (
	 SELECT ROWNUM NUM, N.* 
	  FROM (SELECT * FROM NOTICE_VIEW
             WHERE title LIKE '%%' ORDER BY REGDATE DESC ) N )
      WHERE NUM BETWEEN 1 AND 10;
  