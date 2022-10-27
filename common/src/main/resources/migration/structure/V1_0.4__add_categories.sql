create table if not exists categories
(
    id                serial PRIMARY KEY,
    category_name     varchar(30) not null
        unique,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);