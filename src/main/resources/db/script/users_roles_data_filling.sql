--liquibase formatted sql
--changeset platyPuss:2

insert into roles(id, name)
values (1, 'ROLE_USER');

insert into roles(id, name)
values (2, 'ROLE_ADMIN');

insert into users(id, username, password)
values (1, 'goose', '$2a$04$enS.7Twq0kz3MmG6KFWyJOrlkT9yMPKa/mTaXXxk2HGJK3ljlEevG');

insert into user_roles(user_id, role_id)
values (1, 1);

insert into user_roles(user_id, role_id)
values (1, 2);

insert into users(id, username, password)
values (2, 'jack', '$2a$04$enS.7Twq0kz3MmG6KFWyJOrlkT9yMPKa/mTaXXxk2HGJK3ljlEevG');

insert into user_roles(user_id, role_id)
values (2, 1);