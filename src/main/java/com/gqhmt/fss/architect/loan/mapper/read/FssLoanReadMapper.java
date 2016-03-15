package com.gqhmt.fss.architect.loan.mapper.read;

import com.gqhmt.core.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.extServInter.dto.loan.FailedBidResponse;
import com.gqhmt.extServInter.dto.loan.LendingResponse;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawRespons;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月7日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
public interface FssLoanReadMapper extends ReadMapper<FssLoanEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：得到借款人放款回调对象
	 */
	LendingResponse getResponse(Map<String, String> map) throws FssException;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：抵押权人提现回盘
	 */
	MortgageeWithDrawRespons getMortgageeWithDrawRespons(Map<String, String> map)throws FssException;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：通过id得到收费列表
	 */
	List<FssFeeList> getFeeList(Long id);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月8日
	 * function：流标回盘
	 */
	FailedBidResponse getFailedBidResponse(Map<String, String> map)throws FssException;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：抵押权人付款列表
	 */
	public List<FssLoanEntity> findBorrowerLoan(Map map);

	/**
	 * 获取需要执行满标操作的数据
	 * @return
     */
	public List<FssLoanEntity> findLoanBySettle();
	
}
