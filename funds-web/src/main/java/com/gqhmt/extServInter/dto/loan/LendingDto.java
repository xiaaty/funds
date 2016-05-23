package com.gqhmt.extServInter.dto.loan;
import com.gqhmt.annotations.APIValid;
import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.annotations.APIValidType;
import com.gqhmt.extServInter.dto.SuperDto;

import java.math.BigDecimal;
import java.util.List;



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
 * Description:	借款人放款接口
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
public class LendingDto extends SuperDto {

	@APIValidNull(errorCode = "90002016")
	private String contract_id;			//contract_id

	@APIValidNull(errorCode = "90002022")
	private String contract_no;			//合同编号
	
	@APIValidNull(errorCode = "90002023")
    private BigDecimal contract_interest;		//合同利息
	
	
    private String mortgagee_acc_no;			//抵押权人资金平台账号

	@APIValidNull(errorCode = "90004023")
    private String acc_no;			//借款人资金平台账号

    @APIValidNull(errorCode = "90004014")
    @APIValid(type = APIValidType.MONEY,errorCode = "90004014")
    private BigDecimal contract_amt;			//合同金额
    
    @APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
    private BigDecimal pay_amt;			//放款金额
    
    private List<LendingFeeListDto> fee_list;			//收费列表
    
    @APIValidNull(errorCode = "90004025")
    private String loan_platform;			//借款平台
    
    
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


	public String getLoan_platform() {
		return loan_platform;
	}

	public void setLoan_platform(String loan_platform) {
		this.loan_platform = loan_platform;
	}

	public List<LendingFeeListDto> getFee_list() {
		return fee_list;
	}

	public void setFee_list(List<LendingFeeListDto> fee_list) {
		this.fee_list = fee_list;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public BigDecimal getContract_interest() {
		return contract_interest;
	}

	public void setContract_interest(BigDecimal contract_interest) {
		this.contract_interest = contract_interest;
	}
	
	


}

	
    
