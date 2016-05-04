package com.gqhmt.sys.service;

import com.gqhmt.sys.entity.BankDealamountLimitEntity;
import com.gqhmt.sys.mapper.read.BankDealamountLimitReadMapper;

import org.springframework.stereotype.Service;

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
 * <p>银行交易限额service
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月17日  jhz      1.0     1.0 Version
 */
@Service
public class BankDealamountLimitService {

    @Resource
    private BankDealamountLimitReadMapper bankDealamountLimitReadMapper;
    
    public List<BankDealamountLimitEntity> findAll(){
        return  this.bankDealamountLimitReadMapper.selectAll();
    }

}
