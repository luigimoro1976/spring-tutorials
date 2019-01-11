package com.luigimoro.spring.tutorial.cache.service;

import com.luigimoro.spring.tutorial.cache.configuration.AppConfig;
import com.luigimoro.spring.tutorial.cache.dao.model.CustomerModel;
import com.luigimoro.spring.tutorial.cache.dao.repository.CustomerRepository;
import com.luigimoro.spring.tutorial.cache.dto.CustomerInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CustomerServiceTest {

    private static final String CUSTOMER_ID = "1234567";

    @Autowired
    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    private static RedisServer server = null;

    @BeforeEach
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
        server = new RedisServer();
        server.start();
    }

    @AfterEach
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