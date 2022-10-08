package com.naumshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication(scanBasePackages = "com.naumshop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
public class ApplicationStarter {
    public static void main(String[] args) {

        //TimeZone.setDefault(TimeZone.getTimeZone(args[0]));

        SpringApplication.run(ApplicationStarter.class, args);
    }
}