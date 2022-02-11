--liquibase formatted sql
--changeset apabjan:16

alter table subject_group add constraint fksubjgr foreign key (subject_id) references subject(id);
alter table subject_group add constraint fkteachgr foreign key (teacher_id) references teacher(id);
alter table subject_group add constraint fkclagr foreign key (school_class_id) references school_class(id);