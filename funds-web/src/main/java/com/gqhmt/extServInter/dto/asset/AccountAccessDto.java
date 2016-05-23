package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.extServInter.dto.SuperDto;

import java.math.BigDecimal;

/**
 *资产信息接口---账户余额接口参数
 */
public class AccountAccessDto extends SuperDto {

    private Integer cust_id;//客户/用户ID
    private String cust_name;//客户姓名
    private BigDecimal amount;//账户余额，不包括冻结金额
    private BigDecimal freeze_amount;//冻结金额
    private Integer account_type;//1： 客户账户 2 ：A0  3： AX     
    private Integer busi_type;//1借款客户   2 线下出借客户 3线上出借客户 96线下用应付款账户 99冻结金账户
    private Integer  parent_id;//父账户Id
    private String trade_password;//交易密码
    private String account_no;//账户编号(唯一账户号指定)
    private Integer city_id;//区域代码
    private String parent_bank_id;//开户银行
    private String bankNo;//银行卡号
    private Integer settle_type;//银行卡号
    private Integer is_change_bank_card;//是否变更
    private String source;//来源
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getFreeze_amount() {
		return freeze_amount;
	}
	public void setFreeze_amount(BigDecimal freeze_amount) {
		this.freeze_amount = freeze_amount;
	}
	public Integer getAccount_type() {
		return account_type;
	}
	public void setAccount_type(Integer account_type) {
		this.account_type = account_type;
	}
	public Integer getBusi_type() {
		return busi_type;
	}
	public void setBusi_type(Integer busi_type) {
		this.busi_type = busi_type;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getTrade_password() {
		return trade_password;
	}
	public void setTrade_password(String trade_password) {
		this.trade_password = trade_password;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public String getParent_bank_id() {
		return parent_bank_id;
	}
	public void setParent_bank_id(String parent_bank_id) {
		this.parent_bank_id = parent_bank_id;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public Integer getSettle_type() {
		return settle_type;
	}
	public void setSettle_type(Integer settle_type) {
		this.settle_type = settle_type;
	}
	public Integer getIs_change_bank_card() {
		return is_change_bank_card;
	}
	public void setIs_change_bank_card(Integer is_change_bank_card) {
		this.is_change_bank_card = is_change_bank_card;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
    
}