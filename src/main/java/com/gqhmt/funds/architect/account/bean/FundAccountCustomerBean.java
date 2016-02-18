package com.gqhmt.funds.architect.account.bean;

import java.util.Date;

/**
 * 
 * @author jhz
 * time:2016年2月17日
 * function:资金账户查询数据传输对象
 */
@SuppressWarnings("serial")
public class FundAccountCustomerBean implements java.io.Serializable{
		
		private Long id;
	    private Integer custId;			//客户编号
	    private Integer accountType;	//账户类型 ' 1： 客户账户 2 ：A0  3： AX     ',
	    private Integer busiType;		//业务类型   '1借款客户   2 线下出借客户 3线上出借客户 96线下用应付款账户 99冻结金账户'
	    private String customerName;     //客户姓名 
        private int sex;				 // 客户性别    1-男 2-女
        private String  mobilePhone; 	//手机号码 
        private String certNo;           //证件号码 
        private String accountNo; 		// 账户编号 
        private Double amount;			// 账户余额 
        private Double freezeAmount;	// 冻结金额 
        private int hasThirdAccount; 	//是否创建第三方账户  1，未创建'
        private Date creatTime;			// 创建时间 
        private Date modifyTime;		// 修改时间 
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Integer getCustId() {
			return custId;
		}
		public void setCustId(Integer custId) {
			this.custId = custId;
		}
		public Integer getAccountType() {
			return accountType;
		}
		public void setAccountType(Integer accountType) {
			this.accountType = accountType;
		}
		public Integer getBusiType() {
			return busiType;
		}
		public void setBusiType(Integer busiType) {
			this.busiType = busiType;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public int getSex() {
			return sex;
		}
		public void setSex(int sex) {
			this.sex = sex;
		}
		public String getMobilePhone() {
			return mobilePhone;
		}
		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}
		public String getCertNo() {
			return certNo;
		}
		public void setCertNo(String certNo) {
			this.certNo = certNo;
		}
		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Double getFreezeAmount() {
			return freezeAmount;
		}
		public void setFreezeAmount(Double freezeAmount) {
			this.freezeAmount = freezeAmount;
		}
		public int getHasThirdAccount() {
			return hasThirdAccount;
		}
		public void setHasThirdAccount(int hasThirdAccount) {
			this.hasThirdAccount = hasThirdAccount;
		}
		public Date getCreatTime() {
			return creatTime;
		}
		public void setCreatTime(Date creatTime) {
			this.creatTime = creatTime;
		}
		public Date getModifyTime() {
			return modifyTime;
		}
		public void setModifyTime(Date modifyTime) {
			this.modifyTime = modifyTime;
		}
		
        
}
