create table if not exists l_order_product
(
    id            bigserial primary key,
    order_id      bigint not null
        constraint l_order_product_orders_id_fk
            references orders
            on update cascade on delete cascade,
    product_id    int    not null
        constraint l_order_product_products_id_fk
            references products
            on update cascade on delete cascade,
    creation_date timestamp(6)
);

create index if not exists l_order_product_order_id_index
    on l_order_product (order_id);

create index if not exists l_order_product_order_id_product_id_index
    on l_order_product (order_id, product_id);