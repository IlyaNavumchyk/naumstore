package com.naumstore.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

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
                .name("Navumchyk Ilya")
                .email("dnaum.st.123@gmail.com")
                .url("https://github.com/IlyaNavumchyk/naumstore");
    }

    private License apiLicence() {
        return new License()
                .name("Apache 2.0")
                .url("http://springdoc.org");
    }
}
