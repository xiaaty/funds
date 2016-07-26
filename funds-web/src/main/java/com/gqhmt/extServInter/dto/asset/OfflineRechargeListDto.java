package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.PageSuperDto;

/**
 * Filename:    com.gqhmt.extServInter.dto.asset.RechargeAndWithdrawListDto
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/8 16:48
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/8  于泳      1.0     1.0 Version
 */
public class OfflineRechargeListDto extends PageSuperDto{

    @APIValidNull(errorCode = "90002006")
    private String cust_id;//客户编号
    @APIValidNull(errorCode = "90004015")
    private String cust_type;//客户类型
    private String str_trade_time;//交易开始时间
    private String end_trade_time;//交易结束时间

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_type() {
        return cust_type;
    }

    public void setCust_type(String cust_type) {
        this.cust_type = cust_type;
    }

    public String getStr_trade_time() {
        return str_trade_time;
    }

    public void setStr_trade_time(String str_trade_time) {
        this.str_trade_time = str_trade_time;
    }

    public String getEnd_trade_time() {
        return end_trade_time;
    }

    public void setEnd_trade_time(String end_trade_time) {
        this.end_trade_time = end_trade_time;
    }
}
