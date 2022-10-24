package com.naumshop.configuration;

import com.naumshop.controller.converters.CategoryMapper;
import com.naumshop.controller.converters.OrderMapper;
import com.naumshop.controller.converters.OrderProductLinkMapper;
import com.naumshop.controller.converters.ProductMapper;
import com.naumshop.controller.converters.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public UserMapper getUserMapper() {

        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public ProductMapper getProductMapper() {

        return Mappers.getMapper(ProductMapper.class);
    }

    @Bean
    public OrderMapper getOrderMapper() {

        return Mappers.getMapper(OrderMapper.class);
    }

    @Bean
    public OrderProductLinkMapper getOrderProductLinkMapper() {

        return Mappers.getMapper(OrderProductLinkMapper.class);
    }

    @Bean
    public CategoryMapper getCategoryMapper() {

        return Mappers.getMapper(CategoryMapper.class);
    }
}
