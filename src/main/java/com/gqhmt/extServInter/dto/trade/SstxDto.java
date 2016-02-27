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
 * Create at:   2016年2月24日
 * Description:实时提现接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月24日  柯禹来      1.0     1.0 Version
 */
public class SstxDto extends SuperDto{
	private Integer cust_no;		//客户编号
	private Integer user_no;		//用户编号
	private BigDecimal amt;		//代扣金额
	private Integer busi_type;			//账户类型
	private Long busi_id;			//业务id
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
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public Integer getBusi_type() {
		return busi_type;
	}
	public void setBusi_type(Integer busi_type) {
		this.busi_type = busi_type;
	}
	public Long getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(Long busi_id) {
		this.busi_id = busi_id;
	}
    
}