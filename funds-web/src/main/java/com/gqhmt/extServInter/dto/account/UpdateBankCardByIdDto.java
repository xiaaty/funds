package com.gqhmt.extServInter.dto.account;


import com.gqhmt.extServInter.dto.SuperDto;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月20日
 * Description: 银行卡变更接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月20日  jhz      1.0     1.0 Version
 */
public class UpdateBankCardByIdDto extends SuperDto{

	public String cust_no;//客户表id

	public String after_card_id;//变更后银行卡id

	public String before_card_id;//变更前银行卡id


	public String getAfter_card_id() {
		return after_card_id;
	}

	public String getCust_no() {
		return cust_no;
	}

	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}

	public void setAfter_card_id(String after_card_id) {
		this.after_card_id = after_card_id;
	}

	public String getBefore_card_id() {
		return before_card_id;
	}

	public void setBefore_card_id(String before_card_id) {
		this.before_card_id = before_card_id;
	}
}
