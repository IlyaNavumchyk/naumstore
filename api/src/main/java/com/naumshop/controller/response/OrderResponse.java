package com.naumshop.controller.response;

import com.naumshop.domain.order.OrderStatus;
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
