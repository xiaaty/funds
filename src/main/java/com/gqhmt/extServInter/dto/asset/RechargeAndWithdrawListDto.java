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

    public String getCust_no() {
        return cust_no;
    }

    public void setCust_no(String cust_no) {
        this.cust_no = cust_no;
    }
}
