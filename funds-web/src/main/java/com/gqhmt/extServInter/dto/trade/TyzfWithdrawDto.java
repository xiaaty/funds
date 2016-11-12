package com.gqhmt.extServInter.dto.trade;



import com.gqhmt.extServInter.dto.SuperDto;
import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年10月10日
 * Description:	统一支付提现Dto
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年10月10日  keyulai      1.0     1.0 Version
 */
public class TyzfWithdrawDto extends SuperDto{

	private String withdrawAccountId; //提现账户

	private String withdrawCrdrFlag; //提现账户借贷标识

	private String capitalAccountId; //备付金账户

	private String capitalCrdrFlag; //备付金账户借贷标识

	private String postingAmount; //提现金额

	private String psotingCurrency;//币种

	private BigDecimal rate;//汇率

	private String accountType;//记账分类（手工记账，系统记账）

	private String postingType;//记账类型（补账，一次记账——交易一次成功）

	public String getWithdrawAccountId() {
		return withdrawAccountId;
	}

	public void setWithdrawAccountId(String withdrawAccountId) {
		this.withdrawAccountId = withdrawAccountId;
	}

	public String getWithdrawCrdrFlag() {
		return withdrawCrdrFlag;
	}

	public void setWithdrawCrdrFlag(String withdrawCrdrFlag) {
		this.withdrawCrdrFlag = withdrawCrdrFlag;
	}

	public String getCapitalAccountId() {
		return capitalAccountId;
	}

	public void setCapitalAccountId(String capitalAccountId) {
		this.capitalAccountId = capitalAccountId;
	}

	public String getCapitalCrdrFlag() {
		return capitalCrdrFlag;
	}

	public void setCapitalCrdrFlag(String capitalCrdrFlag) {
		this.capitalCrdrFlag = capitalCrdrFlag;
	}

	public String getPostingAmount() {
		return postingAmount;
	}

	public void setPostingAmount(String postingAmount) {
		this.postingAmount = postingAmount;
	}

	public String getPsotingCurrency() {
		return psotingCurrency;
	}

	public void setPsotingCurrency(String psotingCurrency) {
		this.psotingCurrency = psotingCurrency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
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

}