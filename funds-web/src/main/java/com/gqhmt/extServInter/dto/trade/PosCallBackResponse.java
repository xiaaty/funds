package com.gqhmt.extServInter.dto.trade;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gqhmt.core.json.BigDecimalSerialize;
import com.gqhmt.extServInter.dto.Response;
import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.extServInter.dto.trade.WebOrderResponse
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/8
 * Description:pos充值富有返回结果
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/8  keyulai      1.0     1.0 Version
 */
public class PosCallBackResponse extends Response {
    private String order_no;//订单号
    private String login_id;//交易用户
    private BigDecimal amt;//交易金额
    private String rem;//备注

    @JsonSerialize(using = BigDecimalSerialize.class)
    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }
}
