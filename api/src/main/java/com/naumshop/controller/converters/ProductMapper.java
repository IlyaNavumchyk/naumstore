package com.naumshop.controller.converters;

import com.naumshop.controller.entity_request.ProductRequest;
import com.naumshop.controller.response.ProductResponse;
import com.naumshop.domain.product.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product mapForCreate(ProductRequest productDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapForUpdate(ProductRequest productDTO, @MappingTarget Product product);

    ProductResponse mapToResponse(Product product);
}