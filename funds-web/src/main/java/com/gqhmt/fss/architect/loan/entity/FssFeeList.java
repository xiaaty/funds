package com.gqhmt.fss.architect.loan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Description:	收费列表
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_feeList")
public class FssFeeList implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            //bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  等于 与account表 id相同
    
    @Column(name = "loan_id",updatable = false)
    private Long loanId;                                            //bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  等于 与account表 id相同
    
    @Column(name = "fee_type",updatable = false)
    private String feeType   ;                                //费用类型        YES             (NULL)                   select,insert,update,references  账户总资产
  
    @Column(name = "trade_status")
    private String tradeStatus   ;                                //交易状态        YES             (NULL)                   select,insert,update,references  账户总资产
    
    @Column(name = "result")
    private String result   ;                             // 98060001成功还是98060003失败        YES             (NULL)                   select,insert,update,references  账户总资产
   
    @Column(name="loan_platform",updatable = false)
    private String loanPlatform;              // 借款平台 “北京”、"上海"...
    
    @Column(name = "fee_amt",updatable = false)
    private BigDecimal feeAmt   ;                                //费用金额       YES             (NULL)                   select,insert,update,references  账户总资产
   
    @Column(name="rep_code")
    private String repCode;              // 返回码
  
    @Column(name="rep_msg")
    private String repMsg;              // 返回码

	@Column(name = "create_time",updatable = false)
	private Date createTime;                           //datetime DEFAULT NULL COMMENT '创建时间',

	@Column(name = "modify_time")
	private Date modifyTime;                           // datetime DEFAULT NULL COMMENT '最后修改时间',


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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		if(null==feeAmt||"".equals(feeAmt)){
			feeAmt=BigDecimal.ZERO;
		}
		this.feeAmt = feeAmt;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
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
}
