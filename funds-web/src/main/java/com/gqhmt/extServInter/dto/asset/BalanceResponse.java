package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.extServInter.dto.Response;

import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.extServInter.dto.asset.BalanceResponse
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/2/28 22:45
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/2/28  于泳      1.0     1.0 Version
 */
public class BalanceResponse extends Response {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private BigDecimal frozeAmount;

    public BigDecimal getFrozeAmount() {
        return frozeAmount;
    }

    public void setFrozeAmount(BigDecimal frozeAmount) {
        this.frozeAmount = frozeAmount;
    }
}
