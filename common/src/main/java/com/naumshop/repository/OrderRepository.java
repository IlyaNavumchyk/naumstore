package com.naumshop.repository;

import com.naumshop.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "insert into shop.l_order_product (order_id, product_id, product_count, product_price, creation_date) " +
            "values (:orederId, :productId, :count, :price, current_timestamp(6));", nativeQuery = true)
    void addOrderToLinkTable();
}
