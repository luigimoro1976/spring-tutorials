package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.Smartphone;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.SmartphoneId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartphoneRepositoryTest extends DynamoDbInitializer{

    @Autowired
    SmartphoneRepository smartphoneRepository;

    @Test
    void findByPriceOrderById() {

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        super.createTable(Smartphone.class, dynamoDBMapper);

        Smartphone smartphone1 = new Smartphone("Nokia", "P1000", 300.50);
        Smartphone smartphone2 = new Smartphone("Samsung", "Galaxy 1", 600.99);
        Smartphone smartphone3 = new Smartphone("Nokia", "P900", 199.99);
        Smartphone smartphone4 = new Smartphone("LG", "RT5", 200.00);
        Smartphone smartphone5 = new Smartphone("Nokia", "P1500", 400);
        Smartphone smartphone6 = new Smartphone("Samsung", "S9", 1000);

        List<Smartphone> smartphones = new ArrayList<>();
        smartphones.add(smartphone1);
        smartphones.add(smartphone2);
        smartphones.add(smartphone3);
        smartphones.add(smartphone4);
        smartphones.add(smartphone5);
        smartphones.add(smartphone6);

        smartphoneRepository.saveAll(smartphones);

        smartphoneRepository.findAll().forEach(x -> System.out.println(x.getModel()));

        smartphoneRepository.findByBrandOrderByPrice("Nokia", PageRequest.of(1,4));




    }

}