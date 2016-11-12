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
 * Create at:   2016年11月10日
 * Description:统一支付转账接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年11月10日  柯禹来      1.0     1.0 Version
 */
public class TyzfTransferDto extends SuperDto {

	private String fromAccountId;   //出账账户ID

	private String toAccountId;     //入账账户ID

	private BigDecimal amount;      //转账金额

	private String crdrFlag;//备付金借贷标识（暂时没有平台账，转入户作为内部户处理，借）

	private String postingCurrency;//币种

	private String rate;//汇率

	private String accountType;//记账分类（手工记账，系统记账）

	private String postingType;//记账类型（补账，一次记账——交易一次成功）

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPostingCurrency() {
		return postingCurrency;
	}

	public void setPostingCurrency(String postingCurrency) {
		this.postingCurrency = postingCurrency;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPostingType() {
		return postingType;
	}

	public void setPostingType(String postingType) {
		this.postingType = postingType;
	}

	public String getCrdrFlag() {
		return crdrFlag;
	}

	public void setCrdrFlag(String crdrFlag) {
		this.crdrFlag = crdrFlag;
	}
}
