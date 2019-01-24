package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.luigimoro.spring.tutorial.dynamodb.dao.entity.Smartphone;
import com.luigimoro.spring.tutorial.dynamodb.dao.entity.SmartphoneId;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

@EnableScan
public interface SmartphoneRepository extends PagingAndSortingRepository<Smartphone, SmartphoneId> {

    List<Smartphone> findByBrandOrderByPrice(String brand, Pageable pageable);
}
