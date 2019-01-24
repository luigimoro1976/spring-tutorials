package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class ProductInfoRepositoryIntegrationTest extends DynamoDbInitializer {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    void testFindByItWithPageable() {

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        super.createTable(ProductInfo.class, dynamoDBMapper);

        List<ProductInfo> productInfos = new ArrayList<>();

        for (int x = 0; x <  109; x++) {
            productInfos.add(new ProductInfo(String.valueOf(x % 2 == 0 ? 3 : 4), UUID.randomUUID().toString()));
        }
        repository.saveAll(productInfos);

        Page<ProductInfo> page = repository.findByCostOrderByMsrp("4", PageRequest.of(1, 10));

        page.getContent().stream().forEach(x -> System.out.println(x.getMsrp()));

        super.deleteTable(ProductInfo.class, dynamoDBMapper);
    }
}