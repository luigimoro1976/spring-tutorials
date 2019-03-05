package com.luigimoro.spring.tutorial.dynamodb.dao.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import java.io.Serializable;

public class SmartphoneId implements Serializable {

    private static final long serialVersionUID = 8002674026639765018L;

    private String brand;
    private String model;


    @DynamoDBHashKey(attributeName = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
