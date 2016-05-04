package com.gqhmt.fss.architect.loan.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.extServInter.dto.loan.FailedBidResponse;
import com.gqhmt.extServInter.dto.loan.LendingResponse;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawRespons;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;

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
public interface FssFeeListReadMapper extends ReadMapper<FssFeeList> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：通过id得到收费列表
	 */
	List<FssFeeList> getFeeList(Long id);
	
}
