--liquibase formatted sql
--changeset apabjan:9

create table attendance
(
    id          bigint not null auto_increment,
    was_present bit(6) not null,
    subject_id  bigint not null,
    student_id  bigint not null,
    primary key (id)
) engine=InnoDB AUTO_INCREMENT = 1;