package com.naumstore.configuration;

import com.naumstore.controller.converter.CategoryMapper;
import com.naumstore.controller.converter.OrderMapper;
import com.naumstore.controller.converter.OrderProductLinkEntityMapper;
import com.naumstore.controller.converter.ProductMapper;
import com.naumstore.controller.converter.UserMapper;
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
    public OrderProductLinkEntityMapper getOrderProductLinkMapper() {

        return Mappers.getMapper(OrderProductLinkEntityMapper.class);
    }

    @Bean
    public CategoryMapper getCategoryMapper() {

        return Mappers.getMapper(CategoryMapper.class);
    }
}
