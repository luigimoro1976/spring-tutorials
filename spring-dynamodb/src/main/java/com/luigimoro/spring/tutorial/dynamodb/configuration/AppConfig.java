package com.luigimoro.spring.tutorial.dynamodb.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.luigimoro.spring.tutorial.dynamodb"})
public class AppConfig {

    public AppConfig() {
        System.out.println("Initializing app config...");
    }


}