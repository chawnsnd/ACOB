-- SQL실행 단축키 : 드래그 후 ALT+X

-- 상품 테이블 생성 DDL
create table goods(
	no int not null primary key,
    name varchar(20) not null,
    price int,
    company varchar(20),
    stock int,
    category varchar(20)
)engine=InnoDB default character set = utf8;