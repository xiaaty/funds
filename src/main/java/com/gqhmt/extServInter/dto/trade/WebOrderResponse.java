package com.gqhmt.extServInter.dto.trade;

import com.gqhmt.extServInter.dto.Response;

/**
 * Filename:    com.gqhmt.extServInter.dto.trade.WebOrderResponse
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/2/23 22:24
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/2/23  于泳      1.0     1.0 Version
 */
public class WebOrderResponse extends Response {

    private String order_no;

    private String mchnt;

    private String username;
    
    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getMchnt() {
        return mchnt;
    }

    public void setMchnt(String mchnt) {
        this.mchnt = mchnt;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
