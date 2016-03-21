package com.gqhmt.fss.architect.loan.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月7日
 * Description:	放款
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_loan")
public class FssLoanEntity implements Serializable {

    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            //bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  等于 与account表 id相同

    @Column(name = "cust_no",updatable = false)
    private String custNo;                                     // varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "user_no",updatable = false)
    private String userNo  ;                                   //varchar(45)    utf8_general_ci  YES             (NULL)                   select,insert,update,references

    @Column(name = "acc_no",updatable = false)
    private String accNo   ;                                //借款人资金平台账号
   
    @Column(name = "contract_id",updatable = false)
    private String contractId   ;                                //合同ID

    @Column(name = "contract_amt",updatable = false)
    private BigDecimal contractAmt   ;                                //合同金额        YES             (NULL)                   select,insert,update,references  账户总资产

//    @Column(name = "service_amt")
//    private BigDecimal serviceAmt ;                               // 服务费金额        YES             (NULL)                   select,insert,update,references  账户余额

    @Column(name = "pay_amt",updatable = false)
    private BigDecimal payAmt  ;                                  //放款（提现）金额           YES             (NULL)                   select,insert,update,references  账户可用余额
   
//    @Column(name = "bail_amt")
//    private BigDecimal bailAmt  ;                                  //保证金退还        YES             (NULL)                   select,insert,update,references  账户可用余额

    @Column(name = "bespoke_date",updatable = false)
    private Date bespokeDate  ;                               //预约到账日期       (NULL)           YES             (NULL)                   select,insert,update,references
    
    @Column(name = "trade_type_parent",updatable = false)
    private String tradeTypeParent;                               //交易类型前四位    (NULL)           YES             (NULL)                   select,insert,update,references
    
    @Column(name = "trade_type",updatable = false)
    private String tradeType  ;                               //交易类型    (NULL)           YES             (NULL)                   select,insert,update,references
  
    @Column(name = "seq_no",updatable = false)
    private String seqNo  ;                               //交易流水号    (NULL)           YES             (NULL)                   select,insert,update,references
   
   @Column(name = "mortgagee_acc_no",updatable = false)
    private String mortgageeAccNo;						//抵押权人资金平台账号
   	
   @Column(name = "create_time",updatable = false)
   private Date createTime;                           //datetime DEFAULT NULL COMMENT '创建时间',

   @Column(name = "modify_time")
   private Date modifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',
   
   @Column(name="mchn_parent",updatable = false)
   private String mchnParent;             // varchar(45) NOT NULL COMMENT '大商户号',

   @Column(name="mchn_child",updatable = false)
   private String mchnChild;              // varchar(45) DEFAULT NULL COMMENT '子商户号',
   
   @Column(name="loan_platform",updatable = false)
   private String loanPlatform;              // 借款平台 “北京”、"上海"...
   
   @Column(name="status")
   private String status;              // 状态
   
   @Column(name="rep_code")
   private String repCode;              // 返回码
 
   @Column(name="rep_msg")
   private String repMsg;              // 返回码
   
   @Column(name="result")
   private String result;              // 98060001成功98060002部分成功还是98060003失败
   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public BigDecimal getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}


	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	

	public Date getBespokeDate() {
		return bespokeDate;
	}

	public void setBespokeDate(Date bespokeDate) {
		this.bespokeDate = bespokeDate;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getMortgageeAccNo() {
		return mortgageeAccNo;
	}

	public void setMortgageeAccNo(String mortgageeAccNo) {
		this.mortgageeAccNo = mortgageeAccNo;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLoanPlatform() {
		return loanPlatform;
	}

	public void setLoanPlatform(String loanPlatform) {
		this.loanPlatform = loanPlatform;
	}

	public String getRepCode() {
		return repCode;
	}

	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}

	public String getRepMsg() {
		return repMsg;
	}

	public void setRepMsg(String repMsg) {
		this.repMsg = repMsg;
	}

	public String getTradeTypeParent() {
		return tradeTypeParent;
	}

	public void setTradeTypeParent(String tradeTypeParent) {
		this.tradeTypeParent = tradeTypeParent;
	}
    
	}