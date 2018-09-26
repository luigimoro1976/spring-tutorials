package com.luigimoro.spring.tutorial.aop.service;

import org.springframework.stereotype.Component;

@Component("mathOperation")
public class MathOperation {

    public int performSum(int num1, int num2) {
        return num1 + num2;
    }
}
