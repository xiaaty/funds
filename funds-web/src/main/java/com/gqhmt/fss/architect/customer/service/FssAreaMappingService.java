package com.gqhmt.fss.architect.customer.service;


import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.customer.entity.FssAreaMappingEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssAreaMappingReadMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月17日
 * Description:
 * <p>出借系统映射地区service
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月17日  jhz      1.0     1.0 Version
 */
@Service
public class FssAreaMappingService {

    @Resource
    private FssAreaMappingReadMapper fssBankAreaMappingReadMapper;
    /**
     * 
     * author:jhz
     * time:2016年4月7日
     * function：查询所有的地区映射数据
     */
    public List<FssAreaMappingEntity> findAllAreaMapping()throws FssException{
        return  this.fssBankAreaMappingReadMapper.findAllAreaMapping();
    }

}
