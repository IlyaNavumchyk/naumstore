package com.naumshop.repository;

import com.naumshop.domain.order.OrderProductLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductLinkEntityRepository
        extends JpaRepository<OrderProductLinkEntity, Long> {

}
