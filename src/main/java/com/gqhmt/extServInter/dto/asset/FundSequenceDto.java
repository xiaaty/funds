package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.PageSuperDto;

/**
 *资产信息接口---资金流水查询接口参数
 */
public class FundSequenceDto extends PageSuperDto {
	@APIValidNull(errorCode = "90002021")
	private Integer fundType;
//	@APIValidNull(errorCode = "90002006")
	private Integer cust_no;//客户编号
	@APIValidNull(errorCode = "90002006")
	private Integer user_no;//用户编号
	
	private Integer busi_no;//业务编号

	public Integer getFundType() {
		return fundType;
	}
	public void setFundType(Integer fundType) {
		this.fundType = fundType;
	}
	public Integer getCust_no() {
		return cust_no;
	}
	public void setCust_no(Integer cust_no) {
		this.cust_no = cust_no;
	}
	public Integer getUser_no() {
		return user_no;
	}
	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}
	public Integer getBusi_no() {
		return busi_no;
	}
	public void setBusi_no(Integer busi_no) {
		this.busi_no = busi_no;
	}
	
}