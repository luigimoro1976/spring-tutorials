package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.PetInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Pet2InfoRepositoryTest extends DynamoDbInitializer{

    @Autowired
    Pet2InfoRepository petInfoRepository;

    @Test
    void testFindByPetType() {

        System.out.println("Inside testFindByPetType");
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        super.createTable(PetInfo.class, mapper );

        System.out.println("OK1");

        PetInfo pet1 = new PetInfo("12345", "Dog", "Snoopy", 3);
        PetInfo pet2 = new PetInfo("13345", "Dog", "Bill", 1);
        PetInfo pet3 = new PetInfo("22311", "Cat", "Mew", 8);
        PetInfo pet4 = new PetInfo("41345", "Dog", "Django", 4);

        System.out.println("OK1");

        List<PetInfo> petList = new ArrayList<>();
        petList.add(pet1);
        petList.add(pet2);
        petList.add(pet3);
        petList.add(pet4);

        System.out.println("OK1");
        petInfoRepository.saveAll(petList);
        System.out.println("OK1");
        assertEquals(petInfoRepository.findById("12345").get().getId(), "12345");
        assertEquals(petInfoRepository.findByPetType("Dog").size(), 3);

        super.deleteTable(PetInfo.class, mapper );

    }

}