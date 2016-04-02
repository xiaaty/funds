package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:24
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trade_apply")
public class FssTradeApplyEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            //int(11)        (NULL)           NO      PRI     (NULL)           select,insert,update,references

    @Column(name = "apply_no")
    private String applyNo;                                    //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  申请编号

    @Column(name = "apply_type")
    private Integer applyType;                                  //int(11)        (NULL)           NO              (NULL)           select,insert,update,references  1，充值，2，提现

    @Column(name = "cust_no")
    private String custNo;                                     // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  客户编号

    @Column(name = "user_no")
    private String userNo;                                     // varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  用户编号

    @Column(name = "business_no")
    private String businessNo;                                 //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务编号

    @Column(name = "busi_type")
    private String busiType ;                                  //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  业务类型，借款，出借，冠e通，

    @Column(name = "acc_no")
    private String accNo ;                                     //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  账户编号

    @Column(name = "trade_amount")
    private BigDecimal tradeAmount ;                               // decimal(17,2)  (NULL)           NO              (NULL)           select,insert,update,references  交易金额

    @Column(name = "real_trade_amount")
    private BigDecimal realTradeAmount;                           //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  实际交易金额

    @Column(name = "trade_charge_amount")
    private BigDecimal tradeChargeAmount ;                        //decimal(17,2)  (NULL)           NO              0.00             select,insert,update,references  交易手续费

    @Column(name = "trade_state")
    private String tradeState ;                                //varchar(45)    utf8_general_ci  NO              0                select,insert,update,references  交易状态，未交易，部分成功，成功，失败

    @Column(name = "apply_state")
    private String applyState  ;                               //varchar(45)    utf8_general_ci  NO              0                select,insert,update,references  申请状态，新增，审核成功，，已交易，已回调通知

    @Column(name = "mchn_parent")
    private String mchnParent ;                                //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references

    @Column(name = "mchn_child")
    private String  mchnChild ;                                //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references

    @Column(name = "create_time")
    private Date createTime ;                                //datetime       (NULL)           NO              (NULL)           select,insert,update,references  录入时间

    @Column(name = "modify_time")
    private Date modifyTime ;                                //datetime       (NULL)           NO              (NULL)           select,insert,update,references

    @Column(name = "seq_no")
    private String seqNo     ;                                 //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  api业务交易流水号
    
    @Column(name = "bespoke_date")  //预约到账日期
    private Date bespokedate ;   
    
    @Column(name = "contract_id")//合同Id
    private String contractId;
    
    @Column(name = "channel_no")//交易渠道
    private String channelNo;
    
    @Column(name = "count")		//总条数
    private int count;
    
    @Column(name = "success_count")	//成功条数
    private int successCount;
    
    
    
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
	
}
