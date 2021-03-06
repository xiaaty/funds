package com.gqhmt.extServInter.dto.trade;

import com.gqhmt.extServInter.dto.SuperDto;

import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年10月20日
 * Description:pos回调参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年10月20日  keyulai      1.0     1.0 Version
 */
public class PosCallBackDto extends SuperDto{

	private String order_no;	//第三方返回订单号
	private String respCode;	//交易结果返回码（成功：0000）

	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
}
