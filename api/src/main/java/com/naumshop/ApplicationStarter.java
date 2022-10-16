package com.naumshop;

import com.naumshop.controller.converters.ProductMapper;
import com.naumshop.controller.converters.ProductMapperImpl;
import com.naumshop.controller.dto.products.ProductDTOForCreate;
import com.naumshop.domain.product.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.naumshop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@EnableTransactionManagement
public class ApplicationStarter {
    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone(args[0]));

        SpringApplication.run(ApplicationStarter.class, args);

        ProductDTOForCreate productDTOForCreate = new ProductDTOForCreate();
        productDTOForCreate.setName("gogo");
        productDTOForCreate.setCount(10);
        productDTOForCreate.setCategoryId(5);
        productDTOForCreate.setPrice(100.15);

        ProductMapperImpl productMapper = new ProductMapperImpl();

        Product product = productMapper.mapForCreate(productDTOForCreate);

        System.out.println(product);

    }
}