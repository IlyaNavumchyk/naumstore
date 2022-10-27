package com.naumstore.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("contact")
@Getter
@Setter
public class OpenAPIConfig {


    private String name;
    private String email;
    private String url;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("NAUMstore")
                .description("API to use the best store in the Galaxy!!!")
                .version("1.0")
                .contact(apiContact())
                .license(apiLicence());
    }

    private Contact apiContact() {
        return new Contact()
                .name(name)
                .email(email)
                .url(url);
    }

    private License apiLicence() {
        return new License()
                .name("Apache 2.0")
                .url("https://springdoc.org");
    }
}
