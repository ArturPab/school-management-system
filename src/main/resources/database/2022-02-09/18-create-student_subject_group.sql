--liquibase formatted sql
--changeset apabjan:19

create table student_subject_group (
    student_id bigint NOT NULL,
    subject_group_id bigint NOT NULL,
    primary key (student_id, subject_group_id),
    constraint stusubgroupfk foreign key (student_id) references student(id),
    constraint  subjgrostufk foreign key (subject_group_id) references subject_group(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;