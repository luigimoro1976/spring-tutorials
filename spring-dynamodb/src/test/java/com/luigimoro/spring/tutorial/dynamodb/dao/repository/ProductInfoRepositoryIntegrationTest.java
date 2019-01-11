package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.luigimoro.spring.tutorial.dynamodb.configuration.AppConfig;
import com.luigimoro.spring.tutorial.dynamodb.configuration.DynamoDBConfig;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.ProductInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, DynamoDBConfig.class})
public class ProductInfoRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    ProductInfoRepository repository;

    private static final String EXPECTED_COST = "20";
    private static final String EXPECTED_PRICE = "50";

    @BeforeEach
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductInfo.class);
        DeleteTableRequest deleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(ProductInfo.class);

        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        System.out.println("Deleting pre-existing table");
        try {
            amazonDynamoDB.deleteTable(deleteTableRequest);
        } catch (ResourceNotFoundException ex) {
            System.out.println("No table to delete.");
        }
        System.out.println("Creating table");
        amazonDynamoDB.createTable(tableRequest);

        dynamoDBMapper.batchDelete((List<ProductInfo>) repository.findAll());
    }

    @Test
    public void sampleTestCase() {
        ProductInfo dave = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
        repository.save(dave);

        System.out.println(dave.getId());

        System.out.println("Data saved");
        List<ProductInfo> result = (List<ProductInfo>) repository.findAll();

        assertTrue("No record found", result.size() > 0);
        assertTrue("Contains item with expected cost",
                result.get(0).getCost().equals(EXPECTED_COST));
    }
}