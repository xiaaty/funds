package com.gqhmt.funds.architect.customer.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 银行信息
 * @author keyulai
 *
 */
@Entity
@Table(name = "t_gq_fss_bank_list")
public class BankEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//银行名称
	@Column(name = "name",updatable = false)
	private String bankName;
	//银行简称
	@Column(name = "sort_name",updatable = false)
	private String sortName;
	//银行标识图片存储路径
	@Column(name = "bank_icon",updatable = false)
	private String bankIcon;
	//网银限额图片存储路劲	
	@Column(name = "limit_page",updatable = false)
	private String limitPage;
	//创建时间
	@Column(name = "create_time",updatable = false)
	private Date createTime;
	//创建者
	@Column(name = "create_user_id",updatable = false)
	private Long createUserId;
	//修改时间
	@Column(name = "modify_time",updatable = false)
	private Date modifyTime;
	//修改者
	@Column(name = "modify_user_id",updatable = false)
	private Long modifyUserId;
	
	@Column(name = "tmplate_page",updatable = false)
	private String tmplatePage;
	
	@Column(name = "is_set_limit_page",updatable = false)
	private Integer isSetLimitPage;
	//备注
	@Column(name = "bank_code",updatable = false)
	private String bankCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getBankIcon() {
		return bankIcon;
	}
	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
	}
	public String getLimitPage() {
		return limitPage;
	}
	public void setLimitPage(String limitPage) {
		this.limitPage = limitPage;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Long getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getTmplatePage() {
		return tmplatePage;
	}
	public void setTmplatePage(String tmplatePage) {
		this.tmplatePage = tmplatePage;
	}
	public Integer getIsSetLimitPage() {
		return isSetLimitPage;
	}
	public void setIsSetLimitPage(Integer isSetLimitPage) {
		this.isSetLimitPage = isSetLimitPage;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
}
