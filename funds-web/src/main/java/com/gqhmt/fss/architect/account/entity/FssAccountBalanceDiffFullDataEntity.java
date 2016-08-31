package com.gqhmt.fss.architect.account.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 待余额校验的全量账户数据表
 * @author zhaoenyue
 */
@Entity
@Table(name = "t_gq_fss_account_balance_diff_full_data")
public class FssAccountBalanceDiffFullDataEntity {

    @Id
	@Column(name = "id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;				//自增主键

    @Column(name = "account_id")
    private Integer accountId;    	//账户ID

    @Column(name = "cust_id")
    private Integer custId; 		//客户ID

    @Column(name = "user_name")
    private String userName;    	//第三方账户名

    @Column(name = "cust_name")
    private String custName;        //客户姓名
    
    @Column(name = "is_validate")
    private Boolean isValidate;		//是否校验 0未校验 1已校验

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

	public Boolean getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(Boolean isValidate) {
		this.isValidate = isValidate;
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
