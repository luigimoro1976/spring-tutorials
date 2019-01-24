package com.luigimoro.spring.tutorial.dynamodb.dao.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "pet")
public class PetInfo  {

    @Id
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "idx_global_petType")
    private String petType;


    private String petName;
    private int petAge;

    public PetInfo() {

    }

    public PetInfo(String id, String petType, String petName, int petAge) {
        this.setId(id);
        this.setPetAge(petAge);
        this.setPetType(petType);
        this.setPetName(petName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }
}