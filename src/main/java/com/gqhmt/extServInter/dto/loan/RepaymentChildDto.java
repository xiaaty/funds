package com.gqhmt.extServInter.dto.loan;

import java.math.BigDecimal;
import com.gqhmt.annotations.APIValidNull;
/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:还款划扣接口参数
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  柯禹来      1.0     1.0 Version
 */
public class RepaymentChildDto{
	
	@APIValidNull(errorCode = "90002019")
	private String serial_number; //序列号
	
	@APIValidNull(errorCode = "90002016")
    private String contract_id;   //合同Id
	
	@APIValidNull(errorCode = "90002022")
	private String contract_no;			//合同编号
	
	@APIValidNull(errorCode = "90002018")
    private String acc_no;        //借款人资金平台账号
	
	@APIValidNull(errorCode = "90002020")
    private BigDecimal amt;       //还款金额
    
    private String remark;    //还款备注
    
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
		
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
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
    
}
