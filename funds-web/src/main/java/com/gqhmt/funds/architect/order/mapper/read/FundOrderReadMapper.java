package com.gqhmt.funds.architect.order.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.order.bean.FundOrderBean;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


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

    public List<FundOrderEntity> queryFundOrder(Map<Object,Object> map);

    public int getWithdrawNum(@Param("accountID") Long accountId);

    public List<FundOrderBean> getFundOrderRechargeAndWithdraw(@Param("custId") Integer custId,@Param("order_type")int orderType,@Param("strTime")String strTime,@Param("endTime")String endTime);

    public FundOrderEntity getFundOrderByAccountId(@Param("accountId") Long accountId);
}
