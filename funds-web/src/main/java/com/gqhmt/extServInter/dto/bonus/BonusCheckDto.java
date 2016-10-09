package com.gqhmt.extServInter.dto.bonus;

import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.fss.architect.bonus.bean.BonusBean;

import java.util.List;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/24
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/24  jhz     1.0     1.0 Version
 */
public class BonusCheckDto extends SuperDto {

    private String params;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
