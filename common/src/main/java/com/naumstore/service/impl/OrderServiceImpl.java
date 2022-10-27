package com.naumstore.service.impl;

import com.naumstore.domain.order.Order;
import com.naumstore.domain.order.OrderProductLinkEntity;
import com.naumstore.repository.OrderProductLinkEntityRepository;
import com.naumstore.repository.OrderRepository;
import com.naumstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductLinkEntityRepository linkEntityRepository;

    @Transactional
    public void create(Order order) {

        orderRepository.save(order);
    }

    @Transactional
    public void saveAll(List<OrderProductLinkEntity> products) {

        linkEntityRepository.saveAll(products);
    }
}
