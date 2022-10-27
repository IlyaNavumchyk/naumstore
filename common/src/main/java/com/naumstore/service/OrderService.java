package com.naumstore.service;

import com.naumstore.domain.order.Order;
import com.naumstore.domain.order.OrderProductLinkEntity;

import java.util.List;

public interface OrderService {

    void create(Order order);

    void saveAll(List<OrderProductLinkEntity> products);
}
