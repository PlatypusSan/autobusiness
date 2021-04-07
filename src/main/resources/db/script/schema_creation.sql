--liquibase formatted sql
--changeset platyPuss:1

create table declaration
(
    id bigserial primary key,
    date         timestamp,
    description  varchar(255),
    phone_number varchar(255),
    place        varchar(255),
    vendor_name  varchar(255)
);

alter table declaration
    owner to postgres;

create table details
(
    id bigserial primary key,
    detail_name varchar(255),
    detail_type varchar(255)
);

alter table details
    owner to postgres;

create table car
(
    id bigserial primary key,
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
    dec_id        bigint
    references declaration
);

alter table car
    owner to postgres;

create table car_details
(
    details_id bigint not null
    references details(id),
    car_id     bigint not null
    references car(id)
);

alter table car_details
    owner to postgres;
