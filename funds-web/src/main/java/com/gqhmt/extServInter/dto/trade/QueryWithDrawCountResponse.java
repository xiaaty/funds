package com.gqhmt.extServInter.dto.trade;

import com.gqhmt.extServInter.dto.Response;

/**
 * Filename:    com.gqhmt.extServInter.dto.trade.WebOrderResponse
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/23 22:24
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/23  jhz      1.0     1.0 Version
 */
public class QueryWithDrawCountResponse extends Response {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
