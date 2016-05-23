package com.gqhmt.extServInter.dto.p2p;
import java.math.BigDecimal;
import java.util.List;

import com.gqhmt.extServInter.dto.Response;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月25日
 * Description:
 * <p> 冠E通代扣申请回盘
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月25日 柯禹来      1.0     1.0 Version
 */
public class WithHoldApplyResponse extends Response {

	private String busi_no;	//业务编号
	private BigDecimal real_trade_amount;//实际交易金额
	public String getBusi_no() {
		return busi_no;
	}
	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}
	public BigDecimal getReal_trade_amount() {
		return real_trade_amount;
	}
	public void setReal_trade_amount(BigDecimal real_trade_amount) {
		this.real_trade_amount = real_trade_amount;
	}
	
}

	
    
