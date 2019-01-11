package com.luigimoro.spring.tutorial.cache.dao.repository;

import com.luigimoro.spring.tutorial.cache.dao.model.CustomerModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public CustomerModel findCustomerModelByCustomerId(String customerId) {
        return null;
    }

    @Override
    public void updateCustomerModel(CustomerModel customerModel) {

    }
}
