package com.gqhmt.fss.architect.loan.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountEntity;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountParentEntity;

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
public interface FssEnterAccountParentWriteMapper extends ReadAndWriteMapper<FssEnterAccountParentEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年4月7日
	 * function：修改主表执行条数，修改时间
	 */
	void updateEnterParent(FssEnterAccountEntity fssEnterAccountEntity);
}
