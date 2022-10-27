create table if not exists users
(
    id                bigserial primary key,
    user_login        varchar(20)  not null
        unique,
    constraint users_user_login_check_length_more_than_3 check (length(user_login) > 3),
    user_password     varchar(255) not null,
    email             varchar(100) not null
        unique,
    constraint users_email_check_format check (email similar to '_%@_%.__%'),
    user_name         varchar(20),
    constraint users_users_name_check_length_more_than_2 check (length(user_name) > 2),
    surname           varchar(50),
    constraint users_surname_check_length_more_than_2 check (length(surname) > 2),
    patronymic        varchar(50),
    constraint users_patronymic_check_length_more_than_2 check (length(patronymic) > 2),
    birth             date,
    constraint users_birth_check_after_1900 check (birth > '01.01.1900'),
    gender            varchar(20),
    country           varchar(50),
    city              varchar(50),
    street            varchar(100),
    house             int,
    constraint users_house_check_more_than_0 check (house > 0),
    flat              int,
    constraint users_flat_check_more_than_0 check (flat > 0),
    is_deleted        boolean,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

create index if not exists users_user_login_user_password_index
    on users (user_login, user_password);

create index if not exists users_user_login_is_deleted_index
    on users (user_login, is_deleted);