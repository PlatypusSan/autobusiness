--liquibase formatted sql
--changeset platyPuss:1

create table declaration
(
    id           bigserial primary key,
    date         timestamp,
    description  varchar(255),
    phone_number varchar(255),
    place        varchar(255),
    vendor_name  varchar(255)
);

create table details
(
    id          bigserial primary key,
    detail_name varchar(255),
    detail_type varchar(255)
);

create table car
(
    id            bigserial primary key,
    age           integer,
    body          varchar(255),
    brand         varchar(255),
    deleted       boolean,
    drive_unit    varchar(255),
    engine_type   varchar(255),
    engine_volume double precision,
    generation    varchar(255),
    mile_age      integer,
    model         varchar(255),
    price         integer,
    transmission  varchar(255),
    declaration_id bigint
        references declaration
);

create table car_details
(
    details_id bigint not null
        references details (id),
    car_id     bigint not null
        references car (id)
);

create table users
(
    id       bigserial primary key,
    username varchar(20),
    password varchar(255)
);

create table roles
(
    id   bigserial primary key,
    name varchar(20)
);

create table user_roles
(
    user_id bigint not null
        references users (id),
    role_id bigint not null
        references roles (id)
);

create table dealership
(
    id       bigserial primary key,
    name     varchar(20),
    city     varchar(20),
    time     varchar(20),
    property jsonb
);

create table contact
(
    id            bigserial primary key,
    type          varchar(20),
    value         varchar(20),
    dealership_id bigint references dealership
);

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;