package com.gqhmt.fss.architect.trade.bean;

import java.math.BigDecimal;

/**
 * 资金数据传输对象
 * com.gq.p2p.account.Bean
 * @className FundFlowBean<br/> 
 * @author com.gqhmt.fss.architect.trade.bean
 * @createDate 2015-1-16 上午11:22:30<br/>
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public class FundFlowBean implements java.io.Serializable{

    private Integer fundType;
    private BigDecimal amount;
    private String create_time;
    private String create_ym;
    private String custId;
    private BigDecimal freezeAmount;
    private String accountType;
    private String busiType;
    private String modifyTime;
    private String accountNo;
    private String userName;
    private String hasThirdAccount;
    private String cityId;
    private String bankNo;
    private String actionType;
    
	public Integer getFundType() {
		return fundType;
	}
	public void setFundType(Integer fundType) {
		this.fundType = fundType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCreate_ym() {
		return create_ym;
	}
	public void setCreate_ym(String create_ym) {
		this.create_ym = create_ym;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHasThirdAccount() {
		return hasThirdAccount;
	}
	public void setHasThirdAccount(String hasThirdAccount) {
		this.hasThirdAccount = hasThirdAccount;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
}
