package com.gqhmt.fss.architect.trade.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;

/**
 * Filename:    com.gqhmt.fss.architect.trade.mapper.write.FssTradeApplyWriteMapper
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
public interface FssTradeApplyWriteMapper extends ReadAndWriteMapper<FssTradeApplyEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月28日
	 * function：根据申请编号修改成功条数
	 */
	void updateTradeApplyByApplyNo(String applyNo);
}
