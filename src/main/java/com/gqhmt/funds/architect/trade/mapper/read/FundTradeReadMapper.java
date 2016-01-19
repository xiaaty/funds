package com.gqhmt.funds.architect.trade.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;

import java.math.BigDecimal;

/**
* Filename:    com.gq.p2p.account.dao
* Copyright:   Copyright (c)2014
* Company:     冠群驰骋投资管理(北京)有限公司
*
* @author 于泳
* @version: 1.0
* @since: JDK 1.7
* Create at:   2015/1/16 16:39
* Description:
* <p/>
* Modification History:
* Date    Author      Version     Description
* -----------------------------------------------------------------
* 2015/1/16  于泳      1.0     1.0 Version
*/

public interface FundTradeReadMapper extends ReadMapper<FundTradeEntity> {

public BigDecimal getSumBigDecimal(Integer userID);

public BigDecimal getSumBigDecimalByCus(Integer cusID);
}
