--liquibase formatted sql
--changeset apabjan:15

alter table teacher add constraint fksubj foreign key (subject_group_id) references subject_group(id);