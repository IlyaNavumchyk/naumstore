create table if not exists sales
(
    id                serial PRIMARY KEY,
    sale_name         varchar(30) not null
        unique,
    discount          int         not null,
    constraint sales_discount_check_more_than_0 check (discount > 0),
    constraint sales_discount_check_less_than_100 check (discount < 100),
    start_date        date        not null,
    constraint sales_start_date_check_is_actual check (start_date >= current_date),
    end_date          date        not null,
    constraint sales_end_date_check_is_actual check (end_date >= current_date),
    creation_date     timestamp(6),
    modification_date timestamp(6)
);