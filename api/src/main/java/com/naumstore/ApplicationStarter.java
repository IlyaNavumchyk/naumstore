package com.naumstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.naumstore")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@EnableTransactionManagement
public class ApplicationStarter {
    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone(args[0]));

        SpringApplication.run(ApplicationStarter.class, args);
    }
}