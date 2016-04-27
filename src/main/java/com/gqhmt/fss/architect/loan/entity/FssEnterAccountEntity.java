package com.gqhmt.fss.architect.loan.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月25日
 * Description:入账子表
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月25日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_enter_account")
public class FssEnterAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
	
	@Column(name = "parent_id",updatable = false) 
	private Long  parentId ;    
	    
    @Column(name = "trade_type",updatable = false)
    private String tradeType  ;                               //交易类型    (NULL)           YES             (NULL)                   select,insert,update,references
  
    @Column(name = "contract_id",updatable = false)
    private String contractId   ;  
    
    @Column(name = "contract_no",updatable = false)
    private String contractNo   ;  
    
    @Column(name = "serial_number",updatable = false)
    private String serialNumber  ;                               //序列号    (NULL)           YES             (NULL)                   select,insert,update,references
   
    @Column(name = "accounting_no",updatable = false)
    private String accountingNo  ;                               //账务流水号  (NULL)           YES             (NULL)                   select,insert,update,references
    
    @Column(name = "seq_no",updatable = false)
    private String seqNo  ;                               //交易流水号    (NULL)           YES             (NULL)                   select,insert,update,references
   
    @Column(name = "acc_no",updatable = false)
    private String accNo   ;                                //借款人资金平台账号
    
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
  
    @Column(name="result")
    private String result;              // 98060001成功98060002部分成功还是98060003失败
    
    
    @Column(name="loan_platform",updatable = false)
    private String loanPlatform;              // 借款平台
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getAccountingNo() {
		return accountingNo;
	}

	public void setAccountingNo(String accountingNo) {
		this.accountingNo = accountingNo;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
