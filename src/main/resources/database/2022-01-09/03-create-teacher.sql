--liquibase formatted sql
--changeset apabjan:4

create table teacher
(
    id            bigint       not null auto_increment,
    password      varchar(255) not null,
    name          varchar(100) not null,
    lastname      varchar(100) not null,
    date_of_birth date         not null,
    role          varchar(20)  not null,
    subject_group_id    bigint,
    primary key (id)
) engine=InnoDB AUTO_INCREMENT = 10000;
