package com.gqhmt.extServInter.dto.loan;
import java.math.BigDecimal;
import java.util.Date;

import com.gqhmt.extServInter.dto.Response;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:
 * <p>	借款人提现回盘
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  柯禹来     1.0     1.0 Version
 */
public class WithDrawApplyResponse  extends Response {
	
	private String contract_id;//合同ID
	
	private String contract_no;//合同号
	
	private String acc_no;//借款人资金平台账号
	
	private BigDecimal contract_amt;//合同金额
	
	private BigDecimal pay_amt;//放款（提现）金额
	
	
	private Date  bespoke_date; //预约到账日期


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


	public Date getBespoke_date() {
		return bespoke_date;
	}


	public void setBespoke_date(Date date) {
		this.bespoke_date = date;
	}


	public String getContract_no() {
		return contract_no;
	}


	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	
}
