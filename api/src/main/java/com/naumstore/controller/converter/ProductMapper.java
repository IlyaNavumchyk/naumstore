package com.naumstore.controller.converter;

import com.naumstore.controller.entity_request.ProductRequest;
import com.naumstore.controller.entity_response.ProductResponse;
import com.naumstore.domain.product.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product mapForCreate(ProductRequest productRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapForUpdate(ProductRequest productRequest, @MappingTarget Product product);

    ProductResponse mapToResponse(Product product);
}