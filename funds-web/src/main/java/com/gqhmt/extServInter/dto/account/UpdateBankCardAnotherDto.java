package com.gqhmt.extServInter.dto.account;


import com.gqhmt.extServInter.dto.SuperDto;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年07月26日
 * Description: 银行卡变更接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年07月26日  xdw      1.0     1.0 Version
 */
public class UpdateBankCardAnotherDto extends SuperDto{
	private String bankId;				//银行卡id
	private String bankNo;				//银行卡号
	private String cityId;		 		//开户行地址
	private String bankSortName;	 	//开户银行
	private String filePath;	 		//图片

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getBankSortName() {
		return bankSortName;
	}

	public void setBankSortName(String bankSortName) {
		this.bankSortName = bankSortName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
