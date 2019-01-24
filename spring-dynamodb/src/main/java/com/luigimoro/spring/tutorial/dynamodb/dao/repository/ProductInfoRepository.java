package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.luigimoro.spring.tutorial.dynamodb.dao.entity.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@EnableScan
public interface ProductInfoRepository extends PagingAndSortingRepository<ProductInfo, String> {

    Page<ProductInfo> findByCostOrderByMsrp(String cost, Pageable pageable);

    Page<ProductInfo> findByIdOrderByMsrp(String id, Pageable pageable);
}
