--liquibase formatted sql
--changeset apabjan:8

create table mark
(
    id   bigint not null auto_increment,
    mark float(4, 2
) not null,
    inserted datetime(6),
    description varchar(255),
    subject_id bigint not null,
    student_id bigint not null,
    primary key (id)
) engine=InnoDB AUTO_INCREMENT = 1;