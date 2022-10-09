package com.naumshop.configuration;

import com.naumshop.dto.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public UserMapper getUserMapper() {

        return Mappers.getMapper(UserMapper.class);
    }
}
