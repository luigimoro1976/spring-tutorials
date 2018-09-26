package com.luigimoro.spring.tutorial.cache.dao.repository;

import com.luigimoro.spring.tutorial.cache.dao.model.CustomerModel;

public interface CustomerRepository {

    CustomerModel findCustomerModelByCustomerId(String customerId);

    void updateCustomerModel(CustomerModel customerModel);
}
