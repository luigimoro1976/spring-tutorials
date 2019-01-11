package com.luigimoro.spring.tutorial.cache.service;

import com.luigimoro.spring.tutorial.cache.dao.model.CustomerModel;
import com.luigimoro.spring.tutorial.cache.dao.repository.CustomerRepository;
import com.luigimoro.spring.tutorial.cache.dto.CustomerInfo;
import com.luigimoro.spring.tutorial.cache.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerRepository customerRepository;

    @Cacheable(value = "customerBaseCache")
    public CustomerInfo getCustomerInfo(String customerId) {

        System.out.println("Invoking getCustomerInfo method");

        CustomerModel customerModel = customerRepository.findCustomerModelByCustomerId(customerId);

        return BeanMapper.MAPPER.mapCustomerInfo(customerModel);
    }

    @CacheEvict(value = "customerBaseCache", key = "#customerInfo.customerId")
    public void updateCustomerInfo(CustomerInfo customerInfo) {

        CustomerModel customerModel = BeanMapper.MAPPER.mapCustomerModel(customerInfo);

        customerRepository.updateCustomerModel(customerModel);

    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
