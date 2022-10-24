package com.naumstore.controller.converter;

import com.naumstore.controller.entity_response.OrderProductLinkEntityResponse;
import com.naumstore.domain.order.OrderProductLinkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderProductLinkEntityMapper {

    @Mapping(target = "productId", expression = "java(entity.getProduct().getId())")
    @Mapping(target = "productName", expression = "java(entity.getProduct().getName())")
    OrderProductLinkEntityResponse mapToResponse(OrderProductLinkEntity entity);
}