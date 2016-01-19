package com.gqhmt.funds.architect.trade.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * 提现数据传输对象
 * com.gq.p2p.account.Bean
 * @className WithdrawApplyBean<br/> 
 * @author yangfei.eng@gmail.com
 * @createDate 2015-1-16 上午11:22:30<br/>
 * @version 1.0 <br/>
 */
@SuppressWarnings("serial")
public class WithdrawApplyBean implements java.io.Serializable{

    private long id;
    private Integer accountId;
    private Integer custId;
    private String custName;
    private String cellPhone;
    private BigDecimal drawAmount;
    private BigDecimal procedureFee;
    private Integer bussinessType;       //业务类型(1.满标提现 2-月月通代付申请,3-还款归还保证金，4-债权赎回提现申请;5-抵押标借款人提现)
    private Integer bankId;
    private Integer custType;
    private String ipaddr;
    private Integer applyStatus;
    private String  startTime;
    private String  endTime;
    private String  reviewTime;
	
	private ArrayList<BigDecimal> drawAmountSplit;
	
	// 结算类型，0 T+1; 1 T+0; 
	private Integer settleType;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
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
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}
	/**
	 * @param cellPhone the cellPhone to set
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	/**
	 * @return the drawAmount
	 */
	public BigDecimal getDrawAmount() {
		return drawAmount;
	}
	/**
	 * @param drawAmount the drawAmount to set
	 */
	public void setDrawAmount(BigDecimal drawAmount) {
		this.drawAmount = drawAmount;
	}
	/**
	 * @return the procedureFee
	 */
	public BigDecimal getProcedureFee() {
		return procedureFee;
	}
	/**
	 * @param procedureFee the procedureFee to set
	 */
	public void setProcedureFee(BigDecimal procedureFee) {
		this.procedureFee = procedureFee;
	}
	/**
	 * @return the bankId
	 */
	public Integer getBankId() {
		return bankId;
	}
	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	/**
	 * @return the custType
	 */
	public Integer getCustType() {
		return custType;
	}
	/**
	 * @param custType the custType to set
	 */
	public void setCustType(Integer custType) {
		this.custType = custType;
	}
	/**
	 * @return the ipaddr
	 */
	public String getIpaddr() {
		return ipaddr;
	}
	/**
	 * @param ipaddr the ipaddr to set
	 */
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	/**
	 * @return the applyStatus
	 */
	public Integer getApplyStatus() {
		return applyStatus;
	}
	/**
	 * @param applyStatus the applyStatus to set
	 */
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
	/**
	 * @return the drawAmountSplit
	 */
	public ArrayList<BigDecimal> getDrawAmountSplit() {
		return drawAmountSplit;
	}
	/**
	 * @param drawAmountSplit the drawAmountSplit to set
	 */
	public void setDrawAmountSplit(ArrayList<BigDecimal> drawAmountSplit) {
		this.drawAmountSplit = drawAmountSplit;
	}
	
	/**
	 * @return the bussinessType
	 */
	public Integer getBussinessType() {
		return bussinessType;
	}
	/**
	 * @param bussinessType the bussinessType to set
	 */
	public void setBussinessType(Integer bussinessType) {
		this.bussinessType = bussinessType;
	}
	
	public Integer getSettleType() {
		return settleType;
	}
	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	public String getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}
	
	
	
	
}
