package com.gqhmt.extServInter.dto.trade;


import java.math.BigDecimal;

import com.gqhmt.extServInter.dto.SuperDto;

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
 * Description:转账接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  柯禹来      1.0     1.0 Version
 */
public class TransferDto extends SuperDto {
	private Long busi_id;	 //业务Id	
	private Integer from_cust_no;//出账客户编号	
	private Integer from_user_no;//出账用户编号	
	private Integer to_cust_no;//入账客户编号	
	private Integer to_user_no;//入账用户编号		
	private BigDecimal amt; //转账金额
	private Integer  order_ype;//交易子类型
	private Integer  yw_busi_type;//业务类型
	private Integer  zh_busi_type;//账户类型
	
	public Long getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(Long busi_id) {
		this.busi_id = busi_id;
	}
	public Integer getFrom_cust_no() {
		return from_cust_no;
	}
	public void setFrom_cust_no(Integer from_cust_no) {
		this.from_cust_no = from_cust_no;
	}
	public Integer getFrom_user_no() {
		return from_user_no;
	}
	public void setFrom_user_no(Integer from_user_no) {
		this.from_user_no = from_user_no;
	}
	public Integer getTo_cust_no() {
		return to_cust_no;
	}
	public void setTo_cust_no(Integer to_cust_no) {
		this.to_cust_no = to_cust_no;
	}
	public Integer getTo_user_no() {
		return to_user_no;
	}
	public void setTo_user_no(Integer to_user_no) {
		this.to_user_no = to_user_no;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public Integer getOrder_ype() {
		return order_ype;
	}
	public void setOrder_ype(Integer order_ype) {
		this.order_ype = order_ype;
	}
	public Integer getYw_busi_type() {
		return yw_busi_type;
	}
	public void setYw_busi_type(Integer yw_busi_type) {
		this.yw_busi_type = yw_busi_type;
	}
	public Integer getZh_busi_type() {
		return zh_busi_type;
	}
	public void setZh_busi_type(Integer zh_busi_type) {
		this.zh_busi_type = zh_busi_type;
	}
	
	
}
