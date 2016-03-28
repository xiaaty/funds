package com.gqhmt.fss.architect.trade.mapper.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.trade.bean.FssTradeApplyBean;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;

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
public interface FssTradeApplyReadMapper extends ReadMapper<FssTradeApplyEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人提现
	 */
	public List<FssTradeApplyEntity> getBorrowWithDraw(Map map);
	
	/**
	 * 抵押权人代扣
	 * @param map
	 * @return
	 */
	public List<FssTradeApplyBean> queryFssTradeApplyList(Map map);
	/**
	 * 抵押权人代扣
	 * @param map
	 * @return
	 */
	public FssTradeApplyBean queryFssTradeApply(@Param("applyNo")String applyNo);
}
