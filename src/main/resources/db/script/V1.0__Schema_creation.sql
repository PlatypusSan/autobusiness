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
    id             bigserial primary key,
    age            integer,
    body           varchar(255),
    brand          varchar(255),
    deleted        boolean,
    drive_unit     varchar(255),
    engine_type    varchar(255),
    engine_volume  double precision,
    generation     varchar(255),
    mile_age       integer,
    model          varchar(255),
    price          integer,
    transmission   varchar(255),
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
/*
CREATE TABLE BATCH_JOB_INSTANCE
(
    JOB_INSTANCE_ID BIGINT       NOT NULL PRIMARY KEY,
    VERSION         BIGINT,
    JOB_NAME        VARCHAR(100) NOT NULL,
    JOB_KEY         VARCHAR(32)  NOT NULL,
    constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
);

CREATE TABLE BATCH_JOB_EXECUTION
(
    JOB_EXECUTION_ID           BIGINT        NOT NULL PRIMARY KEY,
    VERSION                    BIGINT,
    JOB_INSTANCE_ID            BIGINT        NOT NULL,
    CREATE_TIME                TIMESTAMP     NOT NULL,
    START_TIME                 TIMESTAMP DEFAULT NULL,
    END_TIME                   TIMESTAMP DEFAULT NULL,
    STATUS                     VARCHAR(10),
    EXIT_CODE                  VARCHAR(2500),
    EXIT_MESSAGE               VARCHAR(2500),
    LAST_UPDATED               TIMESTAMP,
    JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
    constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
        references BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
);

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS
(
    JOB_EXECUTION_ID BIGINT       NOT NULL,
    TYPE_CD          VARCHAR(6)   NOT NULL,
    KEY_NAME         VARCHAR(100) NOT NULL,
    STRING_VAL       VARCHAR(250),
    DATE_VAL         TIMESTAMP DEFAULT NULL,
    LONG_VAL         BIGINT,
    DOUBLE_VAL       DOUBLE PRECISION,
    IDENTIFYING      CHAR(1)      NOT NULL,
    constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
        references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

CREATE TABLE BATCH_STEP_EXECUTION
(
    STEP_EXECUTION_ID  BIGINT       NOT NULL PRIMARY KEY,
    VERSION            BIGINT       NOT NULL,
    STEP_NAME          VARCHAR(100) NOT NULL,
    JOB_EXECUTION_ID   BIGINT       NOT NULL,
    START_TIME         TIMESTAMP    NOT NULL,
    END_TIME           TIMESTAMP DEFAULT NULL,
    STATUS             VARCHAR(10),
    COMMIT_COUNT       BIGINT,
    READ_COUNT         BIGINT,
    FILTER_COUNT       BIGINT,
    WRITE_COUNT        BIGINT,
    READ_SKIP_COUNT    BIGINT,
    WRITE_SKIP_COUNT   BIGINT,
    PROCESS_SKIP_COUNT BIGINT,
    ROLLBACK_COUNT     BIGINT,
    EXIT_CODE          VARCHAR(2500),
    EXIT_MESSAGE       VARCHAR(2500),
    LAST_UPDATED       TIMESTAMP,
    constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
        references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT
(
    STEP_EXECUTION_ID  BIGINT        NOT NULL PRIMARY KEY,
    SHORT_CONTEXT      VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT,
    constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
        references BATCH_STEP_EXECUTION (STEP_EXECUTION_ID)
);

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT
(
    JOB_EXECUTION_ID   BIGINT        NOT NULL PRIMARY KEY,
    SHORT_CONTEXT      VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT,
    constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
        references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

CREATE SEQUENCE BATCH_STEP_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE BATCH_JOB_SEQ MAXVALUE 9223372036854775807 NO CYCLE;*/