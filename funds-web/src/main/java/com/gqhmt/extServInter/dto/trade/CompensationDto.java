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
	private Integer cust_no;//客户编号
	@APIValidNull(errorCode = "90004033")
	private Integer busi_type;		//出账账户类型
	@APIValidNull(errorCode = "90004014")
	@APIValid(type = APIValidType.MONEY,errorCode = "90004014")
	private BigDecimal amt;		//交易金额
	private Long busi_no;//业务编号
	//费用相关参数
	private Integer user_no;//用户编号
	private String platform;//所属平台(北京、天津、上海、其他)
	private String region;//所属大区
	private String filiale;//所属分公司
	private String memo;//备注
	private String accounts_type;//账务科目（21992101：服务费；21992102：管理费；21992103：逆服务费；21992104：保证金）

	public Integer getCust_no() {
		return cust_no;
	}

	public void setCust_no(Integer cust_no) {
		this.cust_no = cust_no;
	}

	public Integer getBusi_type() {
		return busi_type;
	}

	public void setBusi_type(Integer busi_type) {
		this.busi_type = busi_type;
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

	public Integer getUser_no() {
		return user_no;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFiliale() {
		return filiale;
	}

	public void setFiliale(String filiale) {
		this.filiale = filiale;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAccounts_type() {
		return accounts_type;
	}

	public void setAccounts_type(String accounts_type) {
		this.accounts_type = accounts_type;
	}
}
