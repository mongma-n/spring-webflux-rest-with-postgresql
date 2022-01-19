drop table if exists employee;
create table employee (
 seq bigint primary key,
 name varchar(50),
 salary int,
 department varchar(100)
);