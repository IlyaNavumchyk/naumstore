package com.naumshop.controller.converters;

import com.naumshop.controller.dto.products.ProductDTOForCreate;
import com.naumshop.controller.dto.products.ProductDTOForUpdate;
import com.naumshop.domain.product.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.math.BigDecimal;

@Mapper
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product mapForCreate(ProductDTOForCreate productDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapForUpdate(ProductDTOForUpdate productDTO, @MappingTarget Product product);
}