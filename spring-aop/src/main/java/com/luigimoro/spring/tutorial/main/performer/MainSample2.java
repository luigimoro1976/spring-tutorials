package com.luigimoro.spring.tutorial.main.performer;


import com.luigimoro.spring.tutorial.aop.service.CustomerBO;
import org.springframework.beans.factory.annotation.Autowired;

public class MainSample2 implements Performer{

    @Autowired
    CustomerBO customerBO;

    public void performSample() {
        customerBO.addCustomer();
        customerBO.addCustomerReturnValue();
        customerBO.addCustomerAround("abcd");

        try {
            customerBO.addCustomerThrowException();
        } catch (Exception e) {

        }

    }
}
