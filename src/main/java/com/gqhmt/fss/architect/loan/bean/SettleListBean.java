package com.gqhmt.fss.architect.loan.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月7日
 * Description:	清算列表bean
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月18日  jhz      1.0     1.0 Version
 */
public class SettleListBean implements Serializable {

	@JsonIgnore
    private long enterId;
    
    private String account_type   ;                                //账务科目       YES             (NULL)                   select,insert,update,references  账户总资产
    
    private BigDecimal settle_amt   ;                                //清算金额             (NULL)                   select,insert,update,references  账户总资产

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public BigDecimal getSettle_amt() {
		return settle_amt;
	}

	public void setSettle_amt(BigDecimal settle_amt) {
		this.settle_amt = settle_amt;
	}

	public long getEnterId() {
		return enterId;
	}

	public void setEnterId(long enterId) {
		this.enterId = enterId;
	}

	
   
}