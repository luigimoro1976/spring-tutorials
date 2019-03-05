package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.luigimoro.spring.tutorial.dynamodb.dao.entity.Smartphone;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBPagingAndSortingRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@EnableScan
@EnableScanCount
public interface SmartphoneRepository extends DynamoDBPagingAndSortingRepository<Smartphone, String> {

    Page<Smartphone> findByBrandOrderByPrice(String brand, Pageable page);
}
