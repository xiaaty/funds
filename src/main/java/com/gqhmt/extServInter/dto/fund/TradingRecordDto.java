package com.gqhmt.extServInter.dto.fund;

import com.gqhmt.extServInter.dto.SuperDto;

/**
 *资产信息接口---交易记录查询接口参数
 */
public class TradingRecordDto extends SuperDto {
	
	private Integer id;
	private String trade_no;//交易号码
	private Integer trade_type;//交易类型
	private Integer cust_no;//客户编号
	private Integer user_no;//用户编号
	private Integer busi_no;//业务编号
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public Integer getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(Integer trade_type) {
		this.trade_type = trade_type;
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