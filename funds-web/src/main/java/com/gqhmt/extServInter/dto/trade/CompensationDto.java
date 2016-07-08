package com.gqhmt.extServInter.dto.trade;



import com.gqhmt.annotations.APIValid;
import com.gqhmt.annotations.APIValidNull;
import com.gqhmt.annotations.APIValidType;
import com.gqhmt.extServInter.dto.SuperDto;
import java.math.BigDecimal;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年6月28日
 * Description:代偿接口参数
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月28日  柯禹来      1.0     1.0 Version
 */
public class CompensationDto extends SuperDto{

	@APIValidNull(errorCode = "90002006")
	private Integer cust_no;		//出账客户编号
	@APIValidNull(errorCode = "90004033")
	private Integer cust_type;		//出账账户类型
	@APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
	private BigDecimal amt;		//代偿金额
	private Long busi_no;//业务编号

	public Integer getCust_no() {
		return cust_no;
	}

	public void setCust_no(Integer cust_no) {
		this.cust_no = cust_no;
	}

	public Integer getCust_type() {
		return cust_type;
	}

	public void setCust_type(Integer cust_type) {
		this.cust_type = cust_type;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Long getBusi_no() {
		return busi_no;
	}

	public void setBusi_no(Long busi_no) {
		this.busi_no = busi_no;
	}

}
