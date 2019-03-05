package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.Smartphone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

class SmartphoneRepositoryTest extends DynamoDbInitializer {

    @Autowired
    SmartphoneRepository smartphoneRepository;

    @Test
    void findByPriceOrderById() {

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        super.createTable(Smartphone.class, dynamoDBMapper);

        List<Smartphone> smartphones = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            smartphones.add(new Smartphone(generateBrand(), "P1000", generatePrice()));
        }
        smartphoneRepository.saveAll(smartphones);

        System.out.println("Page 0");
        smartphoneRepository.findByBrandOrderByPrice("Nokia", PageRequest.of(0, 4).first()).
                forEach(x -> System.out.println(x.getBrand() + " " + x.getPrice()));

        System.out.println("Page 1");
        smartphoneRepository.findByBrandOrderByPrice("Nokia", PageRequest.of(1, 4)).
                forEach(x -> System.out.println(x.getBrand() + " " + x.getPrice()));

        System.out.println("Page 2");
        smartphoneRepository.findByBrandOrderByPrice("Nokia", PageRequest.of(2, 4)).
                forEach(x -> System.out.println(x.getBrand() + " " + x.getPrice()));
    }

    private double generatePrice() {
        return Math.random() * 1000;
    }


    private String generateBrand() {

        int rdmValue = (int)Math.round(Math.random() * 10);

        switch (rdmValue) {

            case 0: return "Xiaomi";
            case 1: return "LG";
            case 2: return "Samsung";
            default: return "Nokia";


        }

    }

}