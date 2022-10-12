create table if not exists l_user_product
(
    id            bigserial primary key,
    user_id       bigint not null
        constraint l_user_product_users_id_fk
            references users
            on update cascade on delete cascade,
    product_id    int    not null
        constraint l_user_product_products_id_fk
            references products
            on update cascade on delete cascade,
    creation_date timestamp(6)
);

create index if not exists l_user_product_user_id
    on l_user_product (user_id);

create unique index if not exists l_user_product_user_id_product_id_uindex
    on l_user_product (user_id, product_id);