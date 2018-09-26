package com.luigimoro.spring.tutorial.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyTestAspect {

    @Pointcut("execution(* perform*(..))")
    private void myPointCut1() {

    }








}
