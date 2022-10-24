package com.naumshop.service;

import com.naumshop.domain.order.Order;
import com.naumshop.domain.order.OrderProductLinkEntity;
import com.naumshop.repository.OrderProductLinkEntityRepository;
import com.naumshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {


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
