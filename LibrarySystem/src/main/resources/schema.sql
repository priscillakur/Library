drop table if exists libuser;
drop table if exists libbook;
drop table if exists libtrx;
create table libuser
(
userid number(10) not null,
name varchar2(255) not null,
email varchar2(255) not null,
password varchar2(255) not null,
CONSTRAINT libuser_pk PRIMARY KEY (userid)  
);
create table libbook
(
bookid number(10) not null,
title varchar2(255) not null,
author varchar2(255) not null,
bookcount number(10) not null,
constraint libbook_pk PRIMARY KEY(bookid)
);
create table libtrx
(
trx_id number(10) not null,
userid varchar2(255) not null,
bookid varchar2(255) not null,
trx_date date not null,
trx_status varchar(10),
constraint trx_id_pk PRIMARY KEY(trx_id)
);
