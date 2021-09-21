insert into notice(id, title, regdate, content, pub, writer_id)
values (NOTICE_ID_SEQ.nextval,'æ»≥Á«œººø‰',to_char(sysdate,'yyyy.mm.dd hh24:mi'), '123123123','1','demitry');

select * from notice where id = 164;