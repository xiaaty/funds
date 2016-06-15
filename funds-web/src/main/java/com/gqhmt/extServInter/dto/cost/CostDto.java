package com.gqhmt.extServInter.dto.cost;

import java.math.BigDecimal;
import com.gqhmt.extServInter.dto.SuperDto;

/**
 *资产信息接口---费用接口参数
 */
public class CostDto extends SuperDto {
	
	private String trade_no;//交易号码
	private Integer cust_no;//客户编号
	private Integer user_no;//用户编号
	private Integer busi_no;//业务编号（即合同号）
	private String busi_type;//账户类型（1,2,3,96,99）
	private String platform;//所属平台(北京、天津、上海、其他)
	private BigDecimal amt;//收费金额
	private String region;//所属大区
	private String filiale;//所属分公司
	private String memo;//备注
	private String accounts_type;//账务科目

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public Integer getCust_no() {
		return cust_no;
	}

	public void setCust_no(Integer cust_no) {
		this.cust_no = cust_no;
	}

	public Integer getUser_no() {
		return user_no;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}

	public Integer getBusi_no() {
		return busi_no;
	}

	public void setBusi_no(Integer busi_no) {
		this.busi_no = busi_no;
	}

	public String getBusi_type() {
		return busi_type;
	}

	public void setBusi_type(String busi_type) {
		this.busi_type = busi_type;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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