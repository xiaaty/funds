package com.gqhmt.extServInter.dto.loan;
import java.math.BigDecimal;

import com.gqhmt.extServInter.dto.SuperDto;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:借款人体现接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  于泳      1.0     1.0 Version
 */
public class LoanWithDrawApplyDto  extends SuperDto {
//	t_gq_fss_trade_apply
	private String contract_id;//合同ID
	private String acc_no;//借款人资金平台账号
	private BigDecimal contract_amt;//合同金额
	private BigDecimal pay_amt;//放款（提现）金额
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}
	public BigDecimal getContract_amt() {
		return contract_amt;
	}
	public void setContract_amt(BigDecimal contract_amt) {
		this.contract_amt = contract_amt;
	}
	public BigDecimal getPay_amt() {
		return pay_amt;
	}
	public void setPay_amt(BigDecimal pay_amt) {
		this.pay_amt = pay_amt;
	}
	//预约到账日期
	
	
}
