package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.PageSuperDto;
/**
 *交易记录查询接口参数
 */
public class FundTradeDto extends PageSuperDto {
	
	@APIValidNull(errorCode = "90003001")
	private String str_trade_time;//交易开始时间
//	@APIValidNull(errorCode = "90003001")
	private String end_trade_time;//交易结束时间
	private String trade_no;//交易号码
	@APIValidNull(errorCode = "90002006")
	private Integer cust_no;//客户编号
	private Integer user_no;//用户编号
	private Integer busi_no;//业务编号
	private String tradeFilters;//业务标识
	
	public String getStr_trade_time() {
		return str_trade_time;
	}
	public void setStr_trade_time(String str_trade_time) {
		this.str_trade_time = str_trade_time;
	}
	public String getEnd_trade_time() {
		return end_trade_time;
	}
	public void setEnd_trade_time(String end_trade_time) {
		this.end_trade_time = end_trade_time;
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
	public String getTradeFilters() {
		return tradeFilters;
	}
	public void setTradeFilters(String tradeFilters) {
		this.tradeFilters = tradeFilters;
	}
	
}