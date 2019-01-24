package com.luigimoro.spring.tutorial.dynamodb.dao.repository;

import com.luigimoro.spring.tutorial.dynamodb.dao.entity.PetInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@EnableScanCount
@EnableScan
public interface Pet2InfoRepository extends CrudRepository<PetInfo, String> {

    @Override
    Optional<PetInfo> findById(String id);

    List<PetInfo>findByPetType(String petType);
}
