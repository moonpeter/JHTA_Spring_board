drop table chat purge
create table chat(
	id			varchar2(30) primary key,
	password	varchar2(30),
	savefile	varchar2(50),
	originalfile varchar2(30)
)