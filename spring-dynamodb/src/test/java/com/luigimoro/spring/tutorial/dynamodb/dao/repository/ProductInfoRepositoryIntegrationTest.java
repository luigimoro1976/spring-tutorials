package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.ProductInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
public class ProductInfoRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    ProductInfoRepository repository;

    private static final String EXPECTED_COST = "20";
    private static final String EXPECTED_PRICE = "50";

    private DynamoDBProxyServer server;

    @BeforeEach
    public void setup() throws Exception {

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        try {

            System.setProperty("sqlite4java.library.path", "target/native-libs");
            System.out.println("OK");
            final String port = "8000";
            this.server = ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", port});
            try {
                server.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            server.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @AfterEach
    public void teardown() throws Exception {
        if (server == null) {
            return;
        }
        try {
            System.out.println("Trying to shutdown the server");
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testSampleTestCase() {

        System.out.println("Begin");
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductInfo.class);

        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
        System.out.println("Table created");
        ProductInfo dave = new ProductInfo(EXPECTED_COST, EXPECTED_PRICE);
        repository.save(dave);
        System.out.println("Data saved");

        List<ProductInfo> result = (List<ProductInfo>) repository.findAll();

        assertTrue("No record found", result.size() > 0);
        assertTrue("Contains item with expected cost",
                result.get(0).getCost().equals(EXPECTED_COST));

    }
}