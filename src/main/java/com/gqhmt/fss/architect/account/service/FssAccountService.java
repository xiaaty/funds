package com.gqhmt.fss.architect.account.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;

/**
 * Filename:    com.gqhmt.fss.architect.account.service.FssAccountService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:34
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
@Service
public class FssAccountService {


	@Resource
    private FssAccountReadMapper accountReadMapper;
   
    public List<FssAccountEntity> findCustomerAccountByParams(Map map){
        return this.accountReadMapper.findCustomerAccountByParams(map);
    }


}
