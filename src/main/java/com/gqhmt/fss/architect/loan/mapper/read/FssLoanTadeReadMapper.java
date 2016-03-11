package com.gqhmt.fss.architect.loan.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
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
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月11日  jhz      1.0     1.0 Version
 */
public interface FssLoanTadeReadMapper extends ReadMapper<FssLoanEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：抵押权人付款列表
	 */
	public List<FssLoanEntity> findBorrowerLoan(Map map);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：查看收费列表
	 */
	public List<FssFeeList> findFeeList(Long loanId);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人提现
	 */
	public List<FssTradeApplyEntity> getBorrowWithDraw(Map map);
}
