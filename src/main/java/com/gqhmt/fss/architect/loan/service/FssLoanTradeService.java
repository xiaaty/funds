package com.gqhmt.fss.architect.loan.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.mapper.read.FssLoanTadeReadMapper;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月11日
 * Description:
 * <p>抵押权人付款
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月11日  jhz      1.0     1.0 Version
 */
@Service
public class FssLoanTradeService {
	@Resource
	private FssLoanTadeReadMapper fssLoanTadeReadMapper;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：抵押权人付款列表
	 */
	public List<FssLoanEntity> findBorrowerLoan(Map map) {
		return fssLoanTadeReadMapper.findBorrowerLoan(map);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：查看收费列表
	 */
	public List<FssFeeList>  findFeeList(Long loanId) {
		return fssLoanTadeReadMapper.findFeeList(loanId);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人提现
	 */
	public List<FssTradeApplyEntity> getBorrowWithDraw(Map map) {
		return fssLoanTadeReadMapper.getBorrowWithDraw(map);
	}

}
