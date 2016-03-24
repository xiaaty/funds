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
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月24日
 * Description:APP（wap）冠E通后台--代付申请接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月24日  柯禹来      1.0     1.0 Version
 */
public class GET_PrePaymentDto extends SuperDto {

	@APIValidNull(errorCode = "90002006")
	private String cust_id;		//客户编号
	private String cust_type;	//客户类型
	@APIValidNull(errorCode = "90002016")
	private String contract_id;//合同ID
	@APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
	private BigDecimal amt;	//代扣金额
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	
}
