package com.luigimoro.spring.tutorial.cache.service;

import com.luigimoro.spring.tutorial.cache.configuration.AppConfig;
import com.luigimoro.spring.tutorial.cache.dao.model.CustomerModel;
import com.luigimoro.spring.tutorial.cache.dao.repository.CustomerRepository;
import com.luigimoro.spring.tutorial.cache.dto.CustomerInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, CustomerServiceTest.TestConfig.class})
public class CustomerServiceTest {


    private static final String CUSTOMER_ID = "1234567";

    @Configuration
    static class TestConfig {

        @Bean
        CustomerRepository customerRepository() {
            return mock(CustomerRepository.class);
        }
    }

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    private static RedisServer server = null;


    @Before
    public void setup() throws IOException {
        server = new RedisServer();
        server.start();
    }

    @After
    public void teardown() {
        server.stop();
    }

    @Test
    public void testGetCustomerInfoImplementaionExecutedOnlyOnce() {

        when(customerRepository.findCustomerModelByCustomerId(Mockito.anyString())).thenReturn(new CustomerModel("Mario", "Giordano"));

        customerService.getCustomerInfo(CUSTOMER_ID);

        customerService.getCustomerInfo(CUSTOMER_ID);

        verify(customerRepository, times(1)).findCustomerModelByCustomerId(CUSTOMER_ID);
    }

    @Test
    public void testGetCustomerInfoImplementaionExecutedTwoTimes() {

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerId(CUSTOMER_ID);
        customerInfo.setName("Mario");
        customerInfo.setSurname("Rossi");

        when(customerRepository.findCustomerModelByCustomerId(Mockito.anyString())).thenReturn(new CustomerModel("Mario", "Giordano"));

        customerService.getCustomerInfo(CUSTOMER_ID);

        customerService.updateCustomerInfo(customerInfo);

        customerService.getCustomerInfo(CUSTOMER_ID);

        verify(customerRepository, times(2)).findCustomerModelByCustomerId(CUSTOMER_ID);
    }
}