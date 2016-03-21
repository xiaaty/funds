package com.gqhmt.extServInter.dto.loan;
import java.math.BigDecimal;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.SuperDto;

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
 * Description:	抵押权人提现接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
public class MortgageeWithDrawDto extends SuperDto {

	@APIValidNull(errorCode = "90002016")
	private String contract_id;			//合同ID

	@APIValidNull(errorCode = "90004022")
    private String mortgagee_acc_no;			//抵押权人资金平台账号
    
	@APIValidNull(errorCode = "90004023")
    private String acc_no;				//借款人资金平台账号
    
    @APIValidNull(errorCode = "90004014")
    private BigDecimal contract_amt;	//合同金额
    
    @APIValidNull(errorCode = "90004014")
    private BigDecimal pay_amt;			//提现金额
    
    
	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getMortgagee_acc_no() {
		return mortgagee_acc_no;
	}

	public void setMortgagee_acc_no(String mortgagee_acc_no) {
		this.mortgagee_acc_no = mortgagee_acc_no;
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

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

}    
