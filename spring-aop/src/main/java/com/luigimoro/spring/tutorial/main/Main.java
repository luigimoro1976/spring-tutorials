package com.luigimoro.spring.tutorial.main;

import com.luigimoro.spring.tutorial.main.performer.MainSample2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {


    private static final String SPRING_AOP_CONTEXT = "META-INF/spring/application-context.xml";

    public static void main(String[] arg) {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(SPRING_AOP_CONTEXT);

//        MainSample1 mainExample1 = (MainSample1) applicationContext.getBean("mainSample1");
//        mainExample1.performSample();
        MainSample2 mainExample2 = (MainSample2) applicationContext.getBean("mainSample2");
        mainExample2.performSample();


    }
}
