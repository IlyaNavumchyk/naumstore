package com.naumshop.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
@ConfigurationProperties("default-system-time-zone")
@Data
public class DefaultSystemTimeZoneConfig {

    private String timezone;

    @Bean
    public void setDefaultSystemTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    }
}
