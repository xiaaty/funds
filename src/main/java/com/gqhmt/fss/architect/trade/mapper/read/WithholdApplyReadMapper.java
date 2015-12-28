package com.gqhmt.fss.architect.trade.mapper.read;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.Page;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.trade.bean.WithholdApplyBean;
import com.gqhmt.fss.architect.trade.entity.FundTradeEntity;
import com.gqhmt.fss.architect.trade.entity.WithholdApplyEntity;

/**
 * Filename:    com.gq.funds.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author guofu
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/04/08  22:29
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/04/08  guofu      1.0     1.0 Version
 */
public interface WithholdApplyReadMapper extends ReadMapper<WithholdApplyEntity>{
	
	/**
	 * 根据条件对象查询并返回对应的提现信息列表
	 * @param pageReq
	 * @return
	 * @throws AppException
	 */
	public Page querywithholdByConditionList(WithholdApplyBean withholdBean);
	
	/**
	 * 根据条件对象查询并返回对应的提现信息列表
	 * @param bussid
	 * @param busstype
	 * @return list
	 */
	public List<WithholdApplyEntity> queryWithholdListByBussinessIdAndType(int bussid ,int busstype);

}
