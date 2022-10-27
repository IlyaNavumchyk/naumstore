create table if not exists l_user_product
(
    id            bigserial primary key,
    user_id       bigint not null
        constraint l_user_product_users_id_fk
            references users,
    product_id    int    not null
        constraint l_user_product_products_id_fk
            references products,
    creation_date timestamp(6)
);

create index if not exists l_user_product_user_id
    on l_user_product (user_id);

create unique index if not exists l_user_product_user_id_product_id_uindex
    on l_user_product (user_id, product_id);