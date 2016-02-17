package com.gqhmt.funds.architect.account.bean;

import java.util.Date;

/**
 * 
 * @author jhz
 * time:2016年2月17日
 * function:资金流水数据传输对象
 */
@SuppressWarnings("serial")
public class FundAccountSequenceBean implements java.io.Serializable{
		
		/**
	 * 
	 */
		private Integer id;
		private String accountNo; 		// 账户编号 
	    private String customerName;     //客户姓名 
	    private Integer accountType;	//账户类型 ' 1： 客户账户 2 ：A0  3： AX     ',
	    private Integer busiType;		//业务类型   '1借款客户   2 线下出借客户 3线上出借客户 96线下用应付款账户 99冻结金账户'
        private Integer actionType;     //流水类型  1充值、2提现、3转账、4冻结、5解冻，6投标成功,7还款，8债权转让'
        private Double amount;			// 流水金额 
        private Date creatTime;			// 创建时间 
        private int thirdPartyType; 	//所属机构  2，富友'
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
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
		public Integer getActionType() {
			return actionType;
		}
		public void setActionType(Integer actionType) {
			this.actionType = actionType;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Date getCreatTime() {
			return creatTime;
		}
		public void setCreatTime(Date creatTime) {
			this.creatTime = creatTime;
		}
		public int getThirdPartyType() {
			return thirdPartyType;
		}
		public void setThirdPartyType(int thirdPartyType) {
			this.thirdPartyType = thirdPartyType;
		}
		
        
}
