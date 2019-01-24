package com.luigimoro.spring.tutorial.dynamodb.dao.repository;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
public class DynamoDbInitializer {

    final static String DYNAMODB_LOCAL_PORT = "8000";

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    static private DynamoDBProxyServer server;

    @BeforeAll
    public static void setup() throws Exception {

        try {
            System.setProperty("sqlite4java.library.path", "target/native-libs");

            System.out.println("Building server");
            server = ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", DYNAMODB_LOCAL_PORT});
            try {
                System.out.println("Trying to stop server in case it has not been previously correctly shut down");
                server.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Starting server");
            server.start();
            System.out.println("Server started");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void teardown() throws Exception {
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

    protected void createTable(Class table, DynamoDBMapper mapper) {

        System.out.println("Creating table");
        CreateTableRequest tableRequest = mapper.generateCreateTableRequest(table);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        if (tableRequest.getGlobalSecondaryIndexes() != null) {
            tableRequest.getGlobalSecondaryIndexes().forEach(x -> x.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L)));
        }

        amazonDynamoDB.createTable(tableRequest);
        System.out.println("Table created");
    }

    protected void deleteTable(Class table, DynamoDBMapper mapper) {

        DeleteTableRequest tableRequest = mapper.generateDeleteTableRequest(table);
        amazonDynamoDB.deleteTable(tableRequest);
    }

}
