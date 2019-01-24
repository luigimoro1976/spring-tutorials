package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.CustomerInfo;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.ProductInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
public class CustomerCrudRepositoryTest extends DynamoDbInitializer{

    @Autowired
    CustomerCrudRepository customerCrudRepository;

    @Test
    void testCreateNewCustomer() {

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        super.createTable(CustomerInfo.class, dynamoDBMapper);

        CustomerInfo customerInfo_1 = new CustomerInfo();
        customerInfo_1.setId("123456");
        customerInfo_1.setFirstName("Mario");
        customerInfo_1.setLastName("Rossi");

        CustomerInfo customerInfo_2 = new CustomerInfo();
        customerInfo_2.setId("334333");
        customerInfo_2.setFirstName("Gianni");
        customerInfo_2.setLastName("Mela");

        List<CustomerInfo> customerInfoList = new ArrayList<>();
        customerInfoList.add(customerInfo_1);
        customerInfoList.add(customerInfo_2);

        customerCrudRepository.save(customerInfo_1);
        customerCrudRepository.saveAll(customerInfoList);

        assertTrue(customerCrudRepository.count() == 2);

        super.deleteTable(CustomerInfo.class, dynamoDBMapper);
    }

    @Test
    void testFindCustomer() {

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        super.createTable(CustomerInfo.class, dynamoDBMapper);

        CustomerInfo customerInfo_1 = new CustomerInfo();
        customerInfo_1.setId("123456");
        customerInfo_1.setFirstName("Mario");
        customerInfo_1.setLastName("Rossi");

        CustomerInfo customerInfo_2 = new CustomerInfo();
        customerInfo_2.setId("334333");
        customerInfo_2.setFirstName("Gianni");
        customerInfo_2.setLastName("Mela");

        customerCrudRepository.save(customerInfo_1);
        customerCrudRepository.save(customerInfo_2);

        Optional<CustomerInfo> customerInfo = customerCrudRepository.findById("334333");

        assertNotNull(customerInfo);
        assertEquals(customerInfo.get().getFirstName(), "Gianni");

        super.deleteTable(CustomerInfo.class, dynamoDBMapper);

    }







}