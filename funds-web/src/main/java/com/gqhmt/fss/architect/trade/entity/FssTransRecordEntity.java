package com.gqhmt.fss.architect.trade.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTransRecordEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:34
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_trans_record")
public class FssTransRecordEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                    //bigint(20)     (NULL)           NO      PRI     (NULL)           select,insert,update,references

    @Column(name = "cust_no")
    private String custNo;                             // varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references

    @Column(name = "user_no")
    private String userNo ;                            // varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references

    @Column(name = "acc_no")
    private String accNo ;                             //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  转出账号

    @Column(name = "apply_no")
    private String applyNo ;                          //    varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  申请编号

    @Column(name = "to_acc_no")
    private String toAccNo  ;                         //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references  转入账号

    @Column(name = "amount")
    private BigDecimal amount ;                             //decimal(17,2)  (NULL)           YES             (NULL)           select,insert,update,references  转账金额

    @Column(name = "trade_type")
    private Integer tradeType;                          //int(11)        (NULL)           NO              (NULL)           select,insert,update,references  交易类型，满标，还款，债权转让，收费，其他

    @Column(name = "funds_type")
    private Integer fundsType ;                         //int(11)        (NULL)           NO              (NULL)           select,insert,update,references  账务类型

    @Column(name = "trans_no")
    private String transNo  ;                          //varchar(45)    utf8_general_ci  NO              (NULL)           select,insert,update,references  转账编号

    @Column(name = "trade_date")
    private String tradeDate ;                         // char(8)        utf8_general_ci  YES             (NULL)           select,insert,update,references

    @Column(name = "trade_time")
    private String tradeTime ;                         //char(6)        utf8_general_ci  YES             (NULL)           select,insert,update,references

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

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getToAccNo() {
		return toAccNo;
	}

	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getFundsType() {
		return fundsType;
	}

	public void setFundsType(Integer fundsType) {
		this.fundsType = fundsType;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
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

	@Column(name = "create_time")
    private Date createTime ;                        //datetime       (NULL)           YES             (NULL)           select,insert,update,references

    @Column(name = "modify_time")
    private Date modifyTime ;                        // datetime       (NULL)           YES             (NULL)           select,insert,update,references

    @Column(name = "mchn_parent")
    private String mchnParent ;                        //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references

    @Column(name = "mchn_child")
    private String mchnChild  ;                        //varchar(45)    utf8_general_ci  YES             (NULL)           select,insert,update,references

}
