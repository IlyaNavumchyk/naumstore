create table if not exists l_order_product
(
    id            bigserial primary key,
    order_id      bigint         not null
        constraint l_order_product_orders_id_fk
            references orders,
    product_id    int            not null
        constraint l_order_product_products_id_fk
            references products,
    product_count         int            not null,
    constraint l_order_product_count_check_more_than_0 check ( product_count > 0 ),
    product_price         numeric(10, 2) not null,
    constraint l_order_product_price_check_more_than_0 check ( product_price > 0),
    creation_date timestamp(6)
);

create index if not exists l_order_product_order_id_index
    on l_order_product (order_id);

create index if not exists l_order_product_order_id_product_id_index
    on l_order_product (order_id, product_id);