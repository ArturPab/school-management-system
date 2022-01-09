--liquibase formatted sql
--changeset apabjan:1

create table admin
(
    id            bigint       not null auto_increment,
    password      varchar(255) not null,
    name          varchar(100) not null,
    lastname      varchar(100) not null,
    date_of_birth date         not null,
    role          varchar(20)  not null,
    primary key (id)
) engine=InnoDB AUTO_INCREMENT = 1000;

--changeset apabjan:2

insert into admin (id, password, name, lastname, date_of_birth, role)
values (null, 'test123', 'admin', 'admin', '2000-09-05', 'ROLE_ADMIN');