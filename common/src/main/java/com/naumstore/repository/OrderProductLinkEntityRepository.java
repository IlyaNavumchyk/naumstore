package com.naumstore.repository;

import com.naumstore.domain.order.OrderProductLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductLinkEntityRepository
        extends JpaRepository<OrderProductLinkEntity, Long> {

}
