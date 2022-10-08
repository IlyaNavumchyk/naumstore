create or replace function get_product_discount(product_id_param int)
    returns int
    language sql
as
$$
select coalesce(
               (select sum(discount)
                from sales
                where id in (select id
                             from l_sale_product
                             where product_id = product_id_param)
                  and current_date >= sales.start_date
                  and current_date <= sales.end_date),
               0);
$$