package com.gqhmt.fss.architect.loan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
 * Description:	入账实体
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_enter_account")
public class FssEnterAccountEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            //bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  等于 与account表 id相同
    
    @Column(name = "trade_type")
    private String tradeType  ;                               //交易类型    (NULL)           YES             (NULL)                   select,insert,update,references
  
    @Column(name = "contract_id")
    private String contractId   ;  
    
    @Column(name = "serial_number")
    private String serialNumber  ;                               //序列号    (NULL)           YES             (NULL)                   select,insert,update,references
   
    @Column(name = "accounting_no")
    private String accountingNo  ;                               //账务流水号  (NULL)           YES             (NULL)                   select,insert,update,references
    
    @Column(name = "seq_no")
    private String seqNo  ;                               //交易流水号    (NULL)           YES             (NULL)                   select,insert,update,references
   
    @Column(name = "acc_no")
    private String accNo   ;                                //借款人资金平台账号
    
   @Column(name = "mortgagee_acc_no")
    private String mortgageeAccNo;						//抵押权人资金平台账号
   	
    @Column(name = "create_time")
    private Date createTime;                           //datetime DEFAULT NULL COMMENT '创建时间',

    @Column(name = "modify_time")
    private Date modifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',
    
    @Column(name="mchn_parent")
    private String mchnParent;             // varchar(45) NOT NULL COMMENT '大商户号',

    @Column(name="mchn_child")
    private String mchnChild;              // varchar(45) DEFAULT NULL COMMENT '子商户号',
  
    @Column(name="result")
    private String result;              // 98060001成功98060002部分成功还是98060003失败
    
    @Column(name="rep_code")
    private String repCode;              // 返回码
  
    @Column(name="rep_msg")
    private String repMsg;              // 返回码
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
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

	

}
