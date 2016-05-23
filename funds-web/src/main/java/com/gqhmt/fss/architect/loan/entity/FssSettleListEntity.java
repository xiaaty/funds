package com.gqhmt.fss.architect.loan.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
 * Description:	清算列表
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_settleList")
public class FssSettleListEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            //bigint(20)     (NULL)           NO      PRI     (NULL)   auto_increment  select,insert,update,references  等于 与account表 id相同
    
    @Column(name = "enter_id",updatable = false)
    private Long enterId;                          //入账表ID
    
    @Column(name = "account_type",updatable = false)
    private String accountType   ;                                //账务科目       YES             (NULL)                   select,insert,update,references  账户总资产
    
    @Column(name = "settle_amt",updatable = false)
    private BigDecimal settleAmt   ;                                //清算金额             (NULL)                   select,insert,update,references  账户总资产
   
    @Column(name = "trade_status")
    private String tradeStatus   ;                                //交易状态        YES             (NULL)                   select,insert,update,references  账户总资产
    
    @Column(name = "result")
    private String result   ;                                // 0成功还是1失败        YES             (NULL)                   select,insert,update,references  账户总资产
    
    @Column(name="rep_code")
    private String repCode;              // 返回码
  
    @Column(name="rep_msg")
    private String repMsg;              // 返回码
    
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
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

	public Long getEnterId() {
		return enterId;
	}

	public void setEnterId(Long enterId) {
		this.enterId = enterId;
	}

	
	

}
