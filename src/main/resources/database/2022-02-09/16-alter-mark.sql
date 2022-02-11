--liquibase formatted sql
--changeset apabjan:17

alter table mark add constraint fkmarksubj foreign key (subject_group_id) references subject_group(id);
alter table mark add constraint fkmarkstu foreign key (student_id) references student(id);