package com.gqhmt.fss.architect.loan.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;

/**
 * Filename:    com.gqhmt.fss.architect.trade.mapper.read.FssTradeApplyReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:27
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
public interface FssLoanWriteMapper extends ReadAndWriteMapper<FssLoanEntity> {
	 /**
     * 
     * author:jhz
     * time:2016年3月7日
     * function：添加
     */
	public long insertLending(FssLoanEntity fssLoanEntity);
	/**
	 * 
	 * author:jhz
	 * time:2016年4月20日
	 * function：添加满标转账中
	 */
	public long insertFullBid(FssLoanEntity fssLoanEntity);
}