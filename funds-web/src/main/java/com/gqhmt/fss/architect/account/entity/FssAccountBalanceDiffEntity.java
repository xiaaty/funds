package com.gqhmt.fss.architect.account.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户余额差异表(平台vs富友)
 * @author zhaoenyue
 */
@Entity
@Table(name = "t_gq_fss_account_balance_diff")
public class FssAccountBalanceDiffEntity {

    @Id
	@Column(name = "id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;				//自增主键

    @Column(name = "biz_date")
    private Date bizDate;   		//日期

    @Column(name = "account_id")
    private Integer accountId;    	//账户ID

    @Column(name = "cust_id")
    private Integer custId; 		//客户ID

    @Column(name = "user_name")
    private String userName;    	//第三方账户名

    @Column(name = "cust_name")
    private String custName;        //客户姓名

    @Column(name = "plat_amount")
    private BigDecimal platAmount;  //平台账户总金额(分)

    @Column(name = "fuiou_amount")
    private BigDecimal fuiouAmount; //富友账户总金额(分)
    
    @Column(name = "create_time")
    private Date createTime;		//创建时间
    
    @Column(name = "modify_time")
    private Date modifyTime;		//更新时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public BigDecimal getPlatAmount() {
		return platAmount;
	}

	public void setPlatAmount(BigDecimal platAmount) {
		this.platAmount = platAmount;
	}

	public BigDecimal getFuiouAmount() {
		return fuiouAmount;
	}

	public void setFuiouAmount(BigDecimal fuiouAmount) {
		this.fuiouAmount = fuiouAmount;
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
