package com.luigimoro.spring.tutorial.integration.gateway;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSoap {

    public static void main(String[] args) {

        String cxt = "META-INF/spring/application-context.xml";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(cxt);

//        DestinationProvider dp = new DestinationProvider();

    }
}
