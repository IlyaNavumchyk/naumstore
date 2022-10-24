package com.naumstore.controller.converter;

import com.naumstore.controller.entity_response.OrderResponse;
import com.naumstore.domain.order.Order;
import org.mapstruct.Mapper;

@Mapper(uses = OrderProductLinkEntityMapper.class)
public interface OrderMapper {

    OrderResponse mapToResponse(Order order);
}
