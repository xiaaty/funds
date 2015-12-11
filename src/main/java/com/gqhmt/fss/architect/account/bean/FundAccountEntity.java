package com.gqhmt.fss.architect.account.bean;



import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gq.p2p.account.entity
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 15:48
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fund_account")
public class FundAccountEntity {
    private Long id;
    //客户id
    private Integer custId;
    //用户id
    private Integer userId;
    private BigDecimal amount;
    private BigDecimal freezeAmount;
    //1： 客户账户 2 ：A0 3： AX
    private Integer accountType;
    //1借款客户 2 线下出借客户 3线上出借客户 99冻结金账户
    private Integer busiType;
    private Long parentId;
    private String tradePassword;
    private Date createTime;
    private Integer createUserId;
    private Date modifyTime;
    private Integer modifyUserId;
    private String accountNo;
    private String userName;
    private String custName;

    private String cityId;
    private String parentBankId;
    private String bankNo;

    private int hasThirdAccount = 1;



    //结算类型；0 T+0 ; 1 T+1
    private Integer settleType ;


    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cust_id",updatable = false)
    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }
    
    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "freeze_amount")
    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    @Basic
    @Column(name = "account_type",updatable = false)
    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    @Basic
    @Column(name = "busi_type",updatable = false)
    public Integer getBusiType() {
        return busiType;
    }

    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    @Basic
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "trade_password")
    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    @Basic
    @Column(name = "CREATE_TIME",updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "CREATE_USER_ID",updatable = false)
    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "MODIFY_USER_ID")
    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
    
    @Basic
    @Column(name = "account_no",updatable = false)
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundAccountEntity that = (FundAccountEntity) o;

        if (id != that.id) return false;
        if (accountType != null ? !accountType.equals(that.accountType) : that.accountType != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (busiType != null ? !busiType.equals(that.busiType) : that.busiType != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createUserId != null ? !createUserId.equals(that.createUserId) : that.createUserId != null) return false;
        if (custId != null ? !custId.equals(that.custId) : that.custId != null) return false;
        if (freezeAmount != null ? !freezeAmount.equals(that.freezeAmount) : that.freezeAmount != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (modifyUserId != null ? !modifyUserId.equals(that.modifyUserId) : that.modifyUserId != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (tradePassword != null ? !tradePassword.equals(that.tradePassword) : that.tradePassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long result = id;
        if(result == null){
            return 0;
        }
        result = 31 * result + (custId != null ? custId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (freezeAmount != null ? freezeAmount.hashCode() : 0);
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (busiType != null ? busiType.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (tradePassword != null ? tradePassword.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createUserId != null ? createUserId.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (modifyUserId != null ? modifyUserId.hashCode() : 0);
        return result.intValue();
    }

    @Column(name = "has_Third_Account",insertable = false)
    public int getHasThirdAccount() {
        return hasThirdAccount;
    }

    public void setHasThirdAccount(int hasThirdAccount) {
        this.hasThirdAccount = hasThirdAccount;
    }



    @Column(name = "user_name",updatable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "cust_name",updatable = false)
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Column(name = "city_id",updatable = false)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Column(name = "parent_bank_id",insertable = false,updatable = false)
    public String getParentBankId() {
        return parentBankId;
    }

    public void setParentBankId(String parenBankId) {
        this.parentBankId = parenBankId;
    }

    @Column(updatable = false)
    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    @Column(name = "settle_type")
    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }



    
}
