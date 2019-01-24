package com.luigimoro.spring.tutorial.dynamodb.dao.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "smartphone")
public class Smartphone {

    public Smartphone() {}

    public Smartphone(String brand, String model, double price) {
        setBrand(brand);
        setModel(model);
        this.price = price;
    }

    @Id
    private SmartphoneId smartphoneId;

    private double price;
    private double screenSize;

    @DynamoDBHashKey(attributeName = "brand")
    public String getBrand() {
        if (smartphoneId == null) {
            smartphoneId = new SmartphoneId();
        }
        return smartphoneId.getBrand();
    }

    public void setBrand(String brand) {
        if (smartphoneId == null) {
            smartphoneId = new SmartphoneId();
        }
        smartphoneId.setBrand(brand);
    }

    @DynamoDBRangeKey(attributeName = "model")
    public String getModel() {
        if (smartphoneId == null) {
            smartphoneId = new SmartphoneId();
        }
        return smartphoneId.getModel();
    }

    public void setModel(String model) {
        if (smartphoneId == null) {
            smartphoneId = new SmartphoneId();
        }
        smartphoneId.setModel(model);
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "index_base_1")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "index_base_1")
    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }


}
