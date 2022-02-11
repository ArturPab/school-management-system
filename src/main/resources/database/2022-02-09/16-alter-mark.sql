--liquibase formatted sql
--changeset apabjan:17

alter table mark add constraint fkmarksubj foreign key (subject_id) references subject(id);
alter table mark add constraint fkmarkstu foreign key (student_id) references student(id);