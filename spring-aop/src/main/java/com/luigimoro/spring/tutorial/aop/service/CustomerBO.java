package com.luigimoro.spring.tutorial.aop.service;

public interface CustomerBO {

    void addCustomer();

    String addCustomerReturnValue();

    void addCustomerThrowException() throws Exception;

    void addCustomerAround(String name);
}
