create table if not exists products
(
    id                serial PRIMARY KEY,
    product_name      varchar(50)    not null
        unique,
    constraint products_product_name_check_length_more_than_4 check ( length(product_name) > 4 ),
    category_id       int            not null
        constraint products_categories_id_fk
            references categories,
    description       text,
    price             decimal(10, 2) not null,
    constraint products_price_check_more_than_0 check (price >= 0.01),
    count             int            not null,
    constraint products_count_check_non_than_0 check (count > 0),
    is_deleted        boolean,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);