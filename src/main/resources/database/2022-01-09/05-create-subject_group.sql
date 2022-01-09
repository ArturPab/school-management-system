--liquibase formatted sql
--changeset apabjan:6

create table subject_group
(
    id         bigint not null auto_increment,
    subject_id bigint not null,
    teacher_id bigint not null,
    school_class_id   bigint not null,
    primary key (id)
) engine=InnoDB AUTO_INCREMENT = 1;