create table if not exists roles
(
    id                serial primary key,
    role_name         varchar(20) not null
        unique,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);