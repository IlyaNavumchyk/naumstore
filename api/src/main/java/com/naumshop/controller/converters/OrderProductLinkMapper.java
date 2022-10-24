package com.naumshop.controller.converters;

import com.naumshop.controller.response.OrderProductLinkEntityResponse;
import com.naumshop.domain.order.OrderProductLinkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderProductLinkMapper {

    @Mapping(target = "productId", expression = "java(entity.getProduct().getId())")
    @Mapping(target = "productName", expression = "java(entity.getProduct().getName())")
    OrderProductLinkEntityResponse convert(OrderProductLinkEntity entity);
}