package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.extServInter.dto.PageSuperDto;

/**
 *资产信息接口---资金流水查询接口参数
 */
public class FundSequenceDto extends PageSuperDto {
	
	private Integer fundType;
	private String trade_no;//交易号码
	private Integer cust_no;//客户编号
	private Integer user_no;//用户编号
	private Integer busi_no;//业务编号

	public Integer getFundType() {
		return fundType;
	}
	public void setFundType(Integer fundType) {
		this.fundType = fundType;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
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