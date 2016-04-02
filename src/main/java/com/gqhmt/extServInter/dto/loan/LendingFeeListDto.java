package com.gqhmt.extServInter.dto.loan;

import java.math.BigDecimal;


/**
 * Filename:    com.gqhmt.extServInter.dto.loan.LendingFeeListDto
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/17 16:41
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/17  于泳      1.0     1.0 Version
 */
public final class LendingFeeListDto {

    private String fee_type;

    private BigDecimal fee_amt;

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public BigDecimal getFee_amt() {
        return fee_amt;
    }

    public void setFee_amt(BigDecimal fee_amt) {
        this.fee_amt = fee_amt;
    }
}
