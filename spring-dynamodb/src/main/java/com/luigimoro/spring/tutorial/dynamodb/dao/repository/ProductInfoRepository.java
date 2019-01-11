package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.luigimoro.spring.tutorial.dynamodb.dao.entity.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductInfoRepository extends CrudRepository<ProductInfo, String> {

}
