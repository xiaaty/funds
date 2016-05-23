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
public class RechargeAndWithdrawListDto extends PageSuperDto{

    @APIValidNull(errorCode = "90002006")
    private String cust_no;

    @APIValidNull(errorCode = "90004015")
    private int  type;

    //@APIValidNull(errorCode = "90003001")
    private String str_trade_time;              //交易开始时间
    //@APIValidNull(errorCode = "90003001")
    private String end_trade_time;//交易结束时间

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
