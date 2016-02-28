package com.gqhmt.funds.architect.order.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Filename:    com.gq.p2p.account.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/20 20:32
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/20  于泳      1.0     1.0 Version
 */

public interface FundOrderReadMapper extends ReadMapper<FundOrderEntity> {

    public FundOrderEntity getFundOrder(@Param("orderNo") String orderNo);

    public List<FundOrderEntity> queryFundOrder(@Param("orderType") int orderType,@Param("orderSource")int orderSource,@Param("orderFromId")int orderFromId);

    public int getWithdrawNum(long accountID);
    
}
