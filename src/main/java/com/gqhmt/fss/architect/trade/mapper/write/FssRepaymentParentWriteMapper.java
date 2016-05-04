package com.gqhmt.fss.architect.trade.mapper.write;


import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentParentEntity;

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
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月17日  jhz      1.0     1.0 Version
 */
public interface FssRepaymentParentWriteMapper extends ReadAndWriteMapper<FssRepaymentParentEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月28日
	 * function：修改主表执行条数
	 */
	void updateRepaymentParentSuccessCount(FssRepaymentEntity queryRepayment);

	
}
