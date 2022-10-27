create table if not exists l_sale_product
(
    id            bigserial primary key,
    sale_id       int not null
        constraint l_sale_category_sales_id_fk
            references sales,
    product_id    int not null
        constraint l_sale_category_products_id_fk
            references products,
    creation_date timestamp(6)
);

create index if not exists l_sale_product_sale_id_index
    on l_sale_product (sale_id);

create index if not exists l_sale_product_product_id_index
    on l_sale_product (product_id);

create index if not exists l_sale_category_sale_id_category_id_index
    on l_sale_product (sale_id, product_id);