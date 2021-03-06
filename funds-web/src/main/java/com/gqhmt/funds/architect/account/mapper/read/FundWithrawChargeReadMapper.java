package com.gqhmt.funds.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.account.entity.FundWithrawCharge;
import org.apache.ibatis.annotations.Param;


/**
 * Filename:    com.gq.funds.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/6/2 15:55
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/6/2  于泳      1.0     1.0 Version
 */
public interface FundWithrawChargeReadMapper extends ReadMapper<FundWithrawCharge> {
    /**
     * jhz
     * 通过orderNo得到对象信息
     * @param orderNo
     * @return
     */
    FundWithrawCharge selectByOrderNo(@Param("orderNo") String orderNo);
}
