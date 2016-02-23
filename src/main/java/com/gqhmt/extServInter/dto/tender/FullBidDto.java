package com.gqhmt.extServInter.dto.tender;



import java.math.BigDecimal;

import com.gqhmt.extServInter.dto.SuperDto;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月20日
 * Description: 满标接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  柯禹来      1.0     1.0 Version
 */
public class FullBidDto extends SuperDto{
	
	private Integer busi_id;		
	private Integer busi_no;		
	private String state;
	public Integer getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(Integer busi_id) {
		this.busi_id = busi_id;
	}
	public Integer getBusi_no() {
		return busi_no;
	}
	public void setBusi_no(Integer busi_no) {
		this.busi_no = busi_no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}       
    
}