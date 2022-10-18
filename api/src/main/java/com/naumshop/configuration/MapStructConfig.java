package com.naumshop.configuration;

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
}
