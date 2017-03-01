package com.gqhmt.fss.architect.trade.bean;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
public class FssTradeApplyBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	 	private Long id;                                          
	    private String applyNo;                                    //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  申请编号
	    private Integer applyType;                                  //int(11)        (NULL)           NO              (NULL)           select,insert,update,references  1，充值，2，提现
	    private Long custId;  
	    private String custNo;                                     // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  客户编号
	    private String userNo;                                     // varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  用户编号
	    private String businessNo;                                 //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务编号
	    private String busiType ;                                  //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务类型，借款，出借，冠e通，
	    private String accNo ;                                     //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  账户编号
	    private BigDecimal tradeAmount ;                               // decimal(17,2)  (NULL)           NO              (NULL)           select,insert,update,references  交易金额
	    private BigDecimal auditAmount ;                               // decimal(17,2)  (NULL)           NO              (NULL)           select,insert,update,references  审核金额
	    private BigDecimal realTradeAmount;                           //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  实际交易金额
	    private BigDecimal tradeChargeAmount ;                        //decimal(17,2)  (NULL)           NO              0.00             select,insert,update,references  交易手续费
	    private String tradeState ;                                //varchar(45)    utf8_general_ci  NO              0                select,insert,update,references  交易状态，未交易，部分成功，成功，失败
	    private String applyState  ;                               //varchar(45)    utf8_general_ci  NO              0                select,insert,update,references  申请状态，新增，审核成功，，已交易，已回调通知
	    private String mchnParent ;                                //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
	    private String  mchnChild ;                                //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references
	    private Date createTime ;                                //datetime       (NULL)           NO              (NULL)           select,insert,update,references  录入时间
	    private Date modifyTime ;                                //datetime       (NULL)           NO              (NULL)           select,insert,update,references
	    private String seqNo     ;                                 //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  api业务交易流水号
	    private Date bespokedate ;   
	    private String contractId;
	    private String channelNo;
	    private int count;
	    private int successCount;
	    private String custName;           //客户名称
	    private String custMobile;        //客户手机
	    private String certNo;            //证件号码
		private String settleType;//提现时效

		private String contractAmt;    //合同保证金， loan表的 contractAmt
		private String riskSeserveFund;   //风险备用金  feelist表 fee_type=21992105
		private String paymentDeposit;	  //准时还款保证金  feelist表 fee_type=10990006
		private String consultingServices;   //咨询服务费  feelist表 fee_type=10990003
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getApplyNo() {
			return applyNo;
		}
		public void setApplyNo(String applyNo) {
			this.applyNo = applyNo;
		}
		public Integer getApplyType() {
			return applyType;
		}
		public void setApplyType(Integer applyType) {
			this.applyType = applyType;
		}
		public Long getCustId() {
			return custId;
		}
		public void setCustId(Long custId) {
			this.custId = custId;
		}
		public String getCustNo() {
			return custNo;
		}
		public void setCustNo(String custNo) {
			this.custNo = custNo;
		}
		public String getUserNo() {
			return userNo;
		}
		public void setUserNo(String userNo) {
			this.userNo = userNo;
		}
		public String getBusinessNo() {
			return businessNo;
		}
		public void setBusinessNo(String businessNo) {
			this.businessNo = businessNo;
		}
		public String getBusiType() {
			return busiType;
		}
		public void setBusiType(String busiType) {
			this.busiType = busiType;
		}
		public String getAccNo() {
			return accNo;
		}
		public void setAccNo(String accNo) {
			this.accNo = accNo;
		}
		public BigDecimal getTradeAmount() {
			return tradeAmount;
		}
		public void setTradeAmount(BigDecimal tradeAmount) {
			this.tradeAmount = tradeAmount;
		}
		public BigDecimal getRealTradeAmount() {
			return realTradeAmount;
		}
		public void setRealTradeAmount(BigDecimal realTradeAmount) {
			this.realTradeAmount = realTradeAmount;
		}
		public BigDecimal getTradeChargeAmount() {
			return tradeChargeAmount;
		}
		public void setTradeChargeAmount(BigDecimal tradeChargeAmount) {
			this.tradeChargeAmount = tradeChargeAmount;
		}
		public String getTradeState() {
			return tradeState;
		}
		public void setTradeState(String tradeState) {
			this.tradeState = tradeState;
		}
		public String getApplyState() {
			return applyState;
		}
		public void setApplyState(String applyState) {
			this.applyState = applyState;
		}
		public String getMchnParent() {
			return mchnParent;
		}
		public void setMchnParent(String mchnParent) {
			this.mchnParent = mchnParent;
		}
		public String getMchnChild() {
			return mchnChild;
		}
		public void setMchnChild(String mchnChild) {
			this.mchnChild = mchnChild;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Date getModifyTime() {
			return modifyTime;
		}
		public void setModifyTime(Date modifyTime) {
			this.modifyTime = modifyTime;
		}
		public String getSeqNo() {
			return seqNo;
		}
		public void setSeqNo(String seqNo) {
			this.seqNo = seqNo;
		}
		public Date getBespokedate() {
			return bespokedate;
		}
		public void setBespokedate(Date bespokedate) {
			this.bespokedate = bespokedate;
		}
		public String getContractId() {
			return contractId;
		}
		public void setContractId(String contractId) {
			this.contractId = contractId;
		}
		public String getChannelNo() {
			return channelNo;
		}
		public void setChannelNo(String channelNo) {
			this.channelNo = channelNo;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getSuccessCount() {
			return successCount;
		}
		public void setSuccessCount(int successCount) {
			this.successCount = successCount;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		public String getCustMobile() {
			return custMobile;
		}
		public void setCustMobile(String custMobile) {
			this.custMobile = custMobile;
		}
		public String getCertNo() {
			return certNo;
		}
		public void setCertNo(String certNo) {
			this.certNo = certNo;
		}
		public String getSettleType() {
		return settleType;
		}
		public void setSettleType(String settleType) {
		this.settleType = settleType;
		}

	public BigDecimal getAuditAmount() {
		return auditAmount;
	}

	public void setAuditAmount(BigDecimal auditAmount) {
		this.auditAmount = auditAmount;
	}

	public String getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(String contractAmt) {
		this.contractAmt = contractAmt;
	}

	public String getRiskSeserveFund() {
		return riskSeserveFund;
	}

	public void setRiskSeserveFund(String riskSeserveFund) {
		this.riskSeserveFund = riskSeserveFund;
	}

	public String getPaymentDeposit() {
		return paymentDeposit;
	}

	public void setPaymentDeposit(String paymentDeposit) {
		this.paymentDeposit = paymentDeposit;
	}

	public String getConsultingServices() {
		return consultingServices;
	}

	public void setConsultingServices(String consultingServices) {
		this.consultingServices = consultingServices;
	}
}
