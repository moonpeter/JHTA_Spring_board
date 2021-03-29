drop table comments CASCADE CONSTRAINTS;

create table comments(
	num			number			primary key,
	id			varchar2(30)	references member2(id),
	content		varchar2(200),
	reg_date	date,
	board_num	number references board(board_num) on delete cascade
);

create sequence com_seq;

select * from comments;