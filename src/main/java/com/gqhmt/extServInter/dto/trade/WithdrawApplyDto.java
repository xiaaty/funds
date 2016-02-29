package com.gqhmt.extServInter.dto.trade;



import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.SuperDto;

import java.math.BigDecimal;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月24日
 * Description:委托提现交易申请
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月24日  jhz      1.0     1.0 Version
 */
public class WithdrawApplyDto extends SuperDto{

	@APIValidNull(errorCode = "90002006")
	private String cust_no;		//客户编号
	
	private String user_no;		//用户编号


	@APIValidNull(errorCode = "90004014")
    private BigDecimal amount;			//提现金额
    
    private String busi_no;			//业务编号
    
    private String platform;			//所属平台
    
    private String region;			//所属大区
    
    private String filiale;			//所属分公司
    
    private String settle_type;		//结算类型
    
	public String getCust_no() {
		return cust_no;
	}


	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}


	public String getUser_no() {
		return user_no;
	}


	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getPlatform() {
		return platform;
	}


	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public String getBusi_no() {
		return busi_no;
	}


	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getFiliale() {
		return filiale;
	}


	public void setFiliale(String filiale) {
		this.filiale = filiale;
	}


	public String getSettle_type() {
		return settle_type;
	}


	public void setSettle_type(String settle_type) {
		this.settle_type = settle_type;
	}
	

    
    
}