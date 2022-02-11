--liquibase formatted sql
--changeset apabjan:14

alter table student add constraint fkschoolclass foreign key (school_class_id) references school_class(id);
