package com.gqhmt.fss.architect.trade.mapper.read;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;

/**
 * Filename:    com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordWriteMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 22:04
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
public interface FssTradeRecordReadMapper extends ReadMapper<FssTradeRecordEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月30日
	 * function：查询出处于划扣中的申请
	 */
	List<FssTradeRecordEntity> selectByTradeState(@Param("tradeState")int tradeState);
	
//	public  List<FssTradeRecordEntity> queryFssTradeRecordList(@Param("applyNo")String applyNo);
	
}
