package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.SuperDto;

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
public class OfflineRechargeBeanDto extends SuperDto{


    private Long offlineRechargeId;//id


    @APIValidNull(errorCode = "90002006")
    private Long  cust_id;


    public Long getOfflineRechargeId() {
        return offlineRechargeId;
    }

    public void setOfflineRechargeId(Long offlineRechargeId) {
        this.offlineRechargeId = offlineRechargeId;
    }

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }
}
