package com.gqhmt.extServInter.dto.loan;

import java.math.BigDecimal;

import com.gqhmt.extServInter.dto.Response;


public class Repayment extends Response{

 	private String serial_number; //序列号
    private String contract_id;   //合同Id
    private String acc_no;        //借款人资金平台账号
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
}
