--liquibase formatted sql
--changeset apabjan:10

update admin set password='$2a$10$PhqdrDkeeVmW/jUJbpXrw.0oZ8tl8rudxlUEFr2VuEkQEgJ0GldFW' where id=1000