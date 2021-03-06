package com.gqhmt.extServInter.dto.trade;


import com.gqhmt.annotations.APIValid;
import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.annotations.APIValidType;
import com.gqhmt.extServInter.dto.SuperDto;

import java.math.BigDecimal;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月20日
 * Description:APP（wap）冠E通后台发起代扣申请接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  柯禹来      1.0     1.0 Version
 */
public class GETWithholdAndDrawDto extends SuperDto {

	@APIValidNull(errorCode = "90002006")
	private String cust_no;		//客户编号
	private String busi_no;     //合同号id
	private String cust_type;	//客户类型
	private String apply_type;	//申请类型（充值：1103,提现：1104）
//	@APIValidNull(errorCode = "90002022")
	//直接代扣的没有合同编号
	private String contract_no;//合同编号
	private String settle_type;//提现时效0：T+0，1：T+1
	@APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
	private BigDecimal amt;	//代扣金额
	
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
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
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getBusi_no() {
		return busi_no;
	}
	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}
	public String getApply_type() {
		return apply_type;
	}
	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}
	public String getSettle_type() {
		return settle_type;
	}
	public void setSettle_type(String settle_type) {
		this.settle_type = settle_type;
	}
}
