package com.gqhmt.funds.architect.customer.service;

import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.mapper.read.BankReadMapper;
import com.gqhmt.funds.architect.customer.mapper.write.BankWriteMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

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
