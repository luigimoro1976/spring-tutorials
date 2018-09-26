package com.luigimoro.spring.tutorial.main.performer;

import com.luigimoro.spring.tutorial.aop.service.MathOperation;
import org.springframework.beans.factory.annotation.Autowired;

public class MainSample1 implements Performer {

    @Autowired
    MathOperation mathOperation;

    public void performSample() {

        mathOperation.performSum(55,2);

    }


}
