package com.naumstore.controller.entity_response;

import com.naumstore.domain.order.OrderStatus;
import lombok.Data;

import java.util.Set;

@Data
public class OrderResponse {

    private Long id;

    private OrderStatus status;

    private Integer totalSale;

    private Double totalPrice;

    private Set<OrderProductLinkEntityResponse> products;
}
