--liquibase formatted sql
--changeset apabjan:18

alter table attendance add constraint fkattsubj foreign key (subject_group_id) references subject_group(id);
alter table attendance add constraint fkattstu foreign key (student_id) references student(id);