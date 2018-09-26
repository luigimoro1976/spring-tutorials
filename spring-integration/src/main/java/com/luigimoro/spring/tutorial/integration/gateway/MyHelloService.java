package com.luigimoro.spring.tutorial.integration.gateway;

public class MyHelloService implements HelloService {

    public void sayHello(String message) {
        System.out.println("Hello " + message);



    }
}
