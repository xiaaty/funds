package com.gqhmt.fss.architect.account.bean;

import java.math.BigDecimal;

/**
 * 资金账户查询数据传输对象
 * com.gq.p2p.account.Bean
 * @className FundsAccountBean<br/> 
 * @author yangfei.eng@gmail.com
 * @createDate 2015-1-16 上午11:22:30<br/>
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public class FundsAccountBean implements java.io.Serializable{

    private Long id;
    private Integer custId;			//客户编号
    private Integer accountType;	//账户类型
    private Integer busiType;		//业务类型
	private String orderColumn;//排序字段
	private boolean isAsc;//排序
//	private PageReq pageReq;//分页
    private BigDecimal amount;
    private BigDecimal freezeAmount;
    private Integer parentId;
    private String tradePassword;
    private String accountNo;
    private String actionType;  //流水类型
    private String custName;  //流水类型
    private String createTime;
    private String endCreateTime;
    private String customerName;
    //初期检索用flg
    private String firstFlg;

	/**
	 * @return the custId
	 */
	public Integer getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	/**
	 * @return the accountType
	 */
	public Integer getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return the busiType
	 */
	public Integer getBusiType() {
		return busiType;
	}
	/**
	 * @param busiType the busiType to set
	 */
	public void setBusiType(Integer busiType) {
		this.busiType = busiType;
	}
	/**
	 * @return the orderColumn
	 */
	public String getOrderColumn() {
		return orderColumn;
	}
	/**
	 * @param orderColumn the orderColumn to set
	 */
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	/**
	 * @return the isAsc
	 */
	public boolean isAsc() {
		return isAsc;
	}
	/**
	 * @param isAsc the isAsc to set
	 */
	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the freezeAmount
	 */
	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}
	/**
	 * @param freezeAmount the freezeAmount to set
	 */
	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the tradePassword
	 */
	public String getTradePassword() {
		return tradePassword;
	}
	/**
	 * @param tradePassword the tradePassword to set
	 */
	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	public String getFirstFlg() {
		return firstFlg;
	}
	public void setFirstFlg(String firstFlg) {
		this.firstFlg = firstFlg;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
