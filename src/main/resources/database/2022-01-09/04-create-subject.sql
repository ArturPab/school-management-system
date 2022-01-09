--liquibase formatted sql
--changeset apabjan:5

create table subject
(
    id   bigint       not null auto_increment,
    name varchar(100) not null,
    primary key (id)
) engine=InnoDB AUTO_INCREMENT = 1;