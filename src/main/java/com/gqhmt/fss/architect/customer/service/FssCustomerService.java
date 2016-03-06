package com.gqhmt.fss.architect.customer.service;


import java.util.List;

import javax.annotation.Resource;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.fss.architect.customer.mapper.write.FssCustomerWriteMapper;
import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;

import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;

import java.util.Map;

@Service
public class FssCustomerService {


    @Resource
    private FssCustomerReadMapper customerReadMapper;

    private FssCustomerWriteMapper customerWriteMapper;
   
    public List<FssCustomerEntity> findCustomerByParams(Map map){
    	
        return this.customerReadMapper.findCustomerByParams(map);
    }


    public FssCustomerEntity create(SuperDto dto) throws FssException {
        //获取客户信息，通过certNo
        try {
            FssCustomerEntity fssCustomerEntity =  GenerateBeanUtil.GenerateClassInstance(FssCustomerEntity.class,dto);
            customerWriteMapper.insertSelective(fssCustomerEntity);
            return fssCustomerEntity;
        } catch (Exception e) {
            //生成errCode
            throw new FssException("");
        }
    }
    
}
