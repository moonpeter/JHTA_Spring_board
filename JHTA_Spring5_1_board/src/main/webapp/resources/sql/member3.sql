drop table member3 CASCADE CONSTRAINTS;

create table member3(
	id varchar2(15),
	password varchar2(60),
	name varchar2(15),
	age Number(2),
	gender varchar2(6),
	email varchar2(30),
	auth varchar2(50) not null,
	
	PRIMARY KEY(id)
);

select * from member3;

update member3
set auth='ROLE_ADMIN'
where id = 'admin';