create table if not exists products
(
    id                serial PRIMARY KEY,
    product_name      varchar(50) not null
        unique,
    category_id       int         not null
        constraint products_categories_id_fk
            references categories
            on update cascade on delete cascade,
    description       text,
    price             decimal(10, 2) not null,
    constraint products_price_check_more_than_0 check (price > 0),
    count             int not null,
    constraint products_count_check_non_negative check (count > -1),
    is_deleted boolean,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);