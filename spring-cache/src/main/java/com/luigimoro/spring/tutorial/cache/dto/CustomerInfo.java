package com.luigimoro.spring.tutorial.cache.dto;

import java.io.Serializable;

public class CustomerInfo implements Serializable {

    private String name;
    private String surname;
    private String customerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
