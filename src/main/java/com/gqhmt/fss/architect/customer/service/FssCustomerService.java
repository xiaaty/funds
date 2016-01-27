package com.gqhmt.fss.architect.customer.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;

import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;

import java.util.Map;

@Service
public class FssCustomerService {


    @Resource
    private FssCustomerReadMapper customerReadMapper;
   
    public List<FssCustomerEntity> findCustomerByParams(Map map){
    	
        return this.customerReadMapper.findCustomerByParams(map);
    }
    
}
