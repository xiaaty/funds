package com.gqhmt.extServInter.dto.trade;

import com.gqhmt.annotations.APIValid;
import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.annotations.APIValidType;
import com.gqhmt.extServInter.dto.SuperDto;
import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年10月19日
 * Description:pos订单创建参数封装
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年10月179日      1.0     1.0 Version
 */
public class PosOrderCreateDto extends SuperDto{
	@APIValidNull(errorCode = "90002010")
	private String cert_no;//客户身份证号
	@APIValidNull(errorCode = "90002021")
	private String busi_no;//业务编号
	@APIValidNull(errorCode = "90004033")
	private String busi_type;//业务类型
	@APIValidNull(errorCode = "90004014")
//	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
    private BigDecimal amt;//交易金额

	public String getBusi_type() {
		return busi_type;
	}

	public void setBusi_type(String busi_type) {
		this.busi_type = busi_type;
	}

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getBusi_no() {
		return busi_no;
	}

	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}
}