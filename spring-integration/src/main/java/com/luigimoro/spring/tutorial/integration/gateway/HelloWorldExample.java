package com.luigimoro.spring.tutorial.integration.gateway;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldExample {

    public static void main(String[] args) {

        String cxt = "META-INF/spring/application-context.xml";

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(cxt);

        HelloService helloService = applicationContext.getBean("helloGateway", HelloService.class);
        HelloService helloService2 = applicationContext.getBean("helloGateway2", HelloService.class);

        helloService.sayHello("World");
        helloService2.sayHello("World");

    }
}
