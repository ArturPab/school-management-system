--liquibase formatted sql
--changeset apabjan:7

create table school_class
(
    id   bigint     not null auto_increment,
    name varchar(2) not null,
    primary key (id)
) engine=InnoDB AUTO_INCREMENT = 1;