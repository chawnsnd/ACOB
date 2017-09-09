create table goods(
	no int not null primary key,
    name varchar(20) not null,
    price int,
    company varchar(20),
    stock int,
    category varchar(20)
)engine=InnoDB default character set = utf8;