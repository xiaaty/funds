package com.gqhmt.fss.architect.trade.mapper.read;

import java.util.List;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;

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
public interface FssRepaymentReadMapper extends ReadMapper<FssRepaymentEntity> {
	
	public List<FssRepaymentEntity> queryFssRepayment(FssRepaymentEntity repayment);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：查询该批次交易总数
	 */
	public int getTradeCount(Long parentId);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：查询该批次交易成功数
	 */
	public int getSuccessCount(Long parentId);

	/**
	 *
	 * @param parentId
	 * @return
	 * 根据parent_id查询子表对象信息
     */
	public List<FssRepaymentEntity> searchRepaymentByParentId(Long parentId);

}
