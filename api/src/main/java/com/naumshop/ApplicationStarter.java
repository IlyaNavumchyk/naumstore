package com.naumshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.naumshop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@EnableTransactionManagement
public class ApplicationStarter {
    public static void main(String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone(args[0]));
        System.out.println(new Date());

        SpringApplication.run(ApplicationStarter.class, args);
    }
}