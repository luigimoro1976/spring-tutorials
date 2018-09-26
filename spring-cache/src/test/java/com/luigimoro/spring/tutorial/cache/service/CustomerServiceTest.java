package com.luigimoro.spring.tutorial.cache.service;

import com.luigimoro.spring.tutorial.cache.configuration.AppConfig;
import com.luigimoro.spring.tutorial.cache.dao.model.CustomerModel;
import com.luigimoro.spring.tutorial.cache.dao.repository.CustomerRepository;
import com.luigimoro.spring.tutorial.cache.dto.CustomerInfo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.embedded.RedisServer;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@Import(AppConfig.class)
@ContextConfiguration(classes = {CustomerServiceTest.class})
public class CustomerServiceTest {

    @Bean
    public CustomerRepository customerRepository() {
        return mock(CustomerRepository.class);
    }

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    private static RedisServer server;

    @BeforeClass
    public static void setup() throws IOException {
        server = new RedisServer();
        server.start();
    }

    @AfterClass
    public static void teardown() {
        server.stop();
    }

    @Test
    public void testGetCustomerInfoImplementationExecutedOnlyOnce() {
        final String ONLY_ONCE_CUSTOMER_ID = "ONLY_ONCE_CUSTOMER_ID";

        when(customerRepository.findCustomerModelByCustomerId(ONLY_ONCE_CUSTOMER_ID)).thenReturn(new CustomerModel("Mario", "Giordano"));

        customerService.getCustomerInfo(ONLY_ONCE_CUSTOMER_ID);

        customerService.getCustomerInfo(ONLY_ONCE_CUSTOMER_ID);

        verify(customerRepository, times(1)).findCustomerModelByCustomerId(ONLY_ONCE_CUSTOMER_ID);
    }

    @Test
    public void testGetCustomerInfoImplementationExecutedTwoTimes() {
        String TWO_TIMES_CUSTOMER_ID = "TWO_TIMES_CUSTOMER_ID";

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerId(TWO_TIMES_CUSTOMER_ID);
        customerInfo.setName("Mario");
        customerInfo.setSurname("Rossi");

        when(customerRepository.findCustomerModelByCustomerId(TWO_TIMES_CUSTOMER_ID)).thenReturn(new CustomerModel("Mario", "Giordano"));

        customerService.getCustomerInfo(TWO_TIMES_CUSTOMER_ID );

        customerService.updateCustomerInfo(customerInfo);

        customerService.getCustomerInfo(TWO_TIMES_CUSTOMER_ID);

        verify(customerRepository, times(2)).findCustomerModelByCustomerId(TWO_TIMES_CUSTOMER_ID);
    }
}