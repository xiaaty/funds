package com.gqhmt.funds.architect.customer.service;

import com.github.pagehelper.Page;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssChangeCardReadMapper;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.sys.entity.DictEntity;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.customer.bean.BankCardBean;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.entity.UserEntity;
import com.gqhmt.funds.architect.customer.mapper.read.BankCardInfoReadMapper;
import com.gqhmt.funds.architect.customer.mapper.read.BankReadMapper;
import com.gqhmt.funds.architect.customer.mapper.read.CustomerInfoReadMapper;
import com.gqhmt.funds.architect.customer.mapper.write.BankCardinfoWriteMapper;
import com.gqhmt.funds.architect.customer.mapper.write.BankWriteMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.p2p.customer.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 16:36
 * Description:银行列表Service
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  柯禹来      1.0     1.0 Version
 */
@Service
public class BankService {

    @Resource
    private BankReadMapper bankReadMapper;
    
    @Resource
    private BankWriteMapper bankWriteMapper;

	/**
	 * 查询所有银行信息
	 * @param bankinfo
	 * @return
	 */
    public List<BankEntity> findAll(){
        return this.bankReadMapper.selectAll();
    }
    
	/**
	 * 查询银行信息
	 * @param bankinfo
	 * @return
	 */
	public List<BankEntity> getBankList(BankEntity bankinfo){
		return bankReadMapper.selectBankList(bankinfo);
	}

	/**
	 * 根据id得到银行信息
	 * @param bankinfo
	 * @return
	 */
	public BankEntity getBankById(Long id){
		return bankReadMapper.selectByPrimaryKey(id);
	}
    

	 
}
