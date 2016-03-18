package com.gqhmt.extServInter.dto.loan;
import java.util.List;

import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.fss.architect.loan.bean.SettleListBean;

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
 * Description:	入账接口（批量）
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
public class EnterAccount extends Response {

	private Long id;
	@APIValidNull(errorCode = "90002016")
	private String contract_id;			//合同ID
	
	private String serial_number;			//序列号
	
	@APIValidNull(errorCode = "90004021")
	private String accounting_no;			//账务流水号
	
    private String mortgagee_acc_no;			//抵押权人资金平台账号

    private String acc_no;			//借款人资金平台账号
    
    private String loan_platform;			//借款平台

    
    private List<SettleListBean> settle_list;			//清算列表
    
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getAccounting_no() {
		return accounting_no;
	}

	public void setAccounting_no(String accounting_no) {
		this.accounting_no = accounting_no;
	}

	public List<SettleListBean> getSettle_list() {
		return settle_list;
	}

	public void setSettle_list(List<SettleListBean> settle_list) {
		this.settle_list = settle_list;
	}

	public String getLoan_platform() {
		return loan_platform;
	}

	public void setLoan_platform(String loan_platform) {
		this.loan_platform = loan_platform;
	}



    
	}

	
    
