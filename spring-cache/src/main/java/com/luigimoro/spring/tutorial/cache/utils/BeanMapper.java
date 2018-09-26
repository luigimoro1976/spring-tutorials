package com.luigimoro.spring.tutorial.cache.utils;

import com.luigimoro.spring.tutorial.cache.dao.model.CustomerModel;
import com.luigimoro.spring.tutorial.cache.dto.CustomerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeanMapper {

    BeanMapper MAPPER = Mappers.getMapper(BeanMapper.class);

    CustomerInfo mapCustomerInfo(CustomerModel customerModel);

    CustomerModel mapCustomerModel(CustomerInfo customerInfo);
}
