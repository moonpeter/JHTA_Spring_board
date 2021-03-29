drop table member2 CASCADE CONSTRAINTS;

create table member2(
	id varchar2(15),
	password varchar2(60),
	name varchar2(15),
	age Number(2),
	gender varchar2(6),
	email varchar2(30),
	PRIMARY KEY(id)
);

select * from member2;