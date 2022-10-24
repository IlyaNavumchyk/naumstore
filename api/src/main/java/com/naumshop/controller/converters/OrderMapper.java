package com.naumshop.controller.converters;

import com.naumshop.controller.response.OrderResponse;
import com.naumshop.domain.order.Order;
import org.mapstruct.Mapper;

@Mapper(uses = OrderProductLinkMapper.class)
public interface OrderMapper {

    OrderResponse mapToResponse(Order order);
}
