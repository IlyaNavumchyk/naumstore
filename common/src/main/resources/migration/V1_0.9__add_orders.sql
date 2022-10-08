create table if not exists orders
(
    id                bigserial primary key,
    user_id           bigint      not null
        constraint orders_users_id_fk
            references users
            on update cascade on delete cascade,
    status            varchar(20) not null,
    total_sale        int,
    constraint orders_total_sale_check_less_than_100 check (total_sale < 100),
    total_price       decimal(10, 2),
    constraint orders_total_price_check_more_than_0 check (total_price > 0),
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

create index if not exists orders_user_id_index
    on orders (user_id);