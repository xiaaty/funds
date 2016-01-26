package com.gqhmt.fss.architect.customer.service;


import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FssCustomerService {

    @Resource
    private FssCustomerReadMapper customerReadMapper;
   
    public List<FssCustomerEntity> findCustomerByParams(Map map){
    	
    	
//        return this.customerReadMapper.findCustomerByParams(customer);
        return this.customerReadMapper.findCustomerByParams(map);
    }
    
    
    
}
