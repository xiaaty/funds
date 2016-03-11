package com.gqhmt.fss.architect.loan.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.extServInter.dto.loan.EnterAccount;
import com.gqhmt.extServInter.dto.loan.FailedBidResponse;
import com.gqhmt.extServInter.dto.loan.LendingResponse;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawRespons;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.entity.FssSettleListEntity;

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
	 * time:2016年3月7日
	 * function：通过id得到费用列表
	 */
	List<FssSettleListEntity> getFssSettleList(Long id)throws FssException;
	/**
	 * author:jhz
	 * time:2016年3月9日
	 * function：入账回盘
	 */
	List<EnterAccount> getEnterAccount(Map<String, String> map)throws FssException;
}
