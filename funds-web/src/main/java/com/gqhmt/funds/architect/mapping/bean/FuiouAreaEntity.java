package com.gqhmt.funds.architect.mapping.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * 富友地区代码实体bean
 */
@Entity
@Table(name = "t_fuiou_area")
public class FuiouAreaEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Character areaType;
	private String areaCode;
	private String areaValue;
	private Character isDel;
	private Character isUsed;
	private Character isLeaf;
	private Long createUser;
	private Date createDate;
	private Long parentId;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "area_type", length = 1)
	public Character getAreaType() {
		return this.areaType;
	}

	public void setAreaType(Character areaType) {
		this.areaType = areaType;
	}

	@Column(name = "area_code", length = 50)
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "area_value", length = 200)
	public String getAreaValue() {
		return this.areaValue;
	}

	public void setAreaValue(String areaValue) {
		this.areaValue = areaValue;
	}

	@Column(name = "is_del", length = 1)
	public Character getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Character isDel) {
		this.isDel = isDel;
	}

	@Column(name = "is_used", length = 1)
	public Character getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(Character isUsed) {
		this.isUsed = isUsed;
	}

	@Column(name = "is_leaf", length = 1)
	public Character getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Character isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "create_user")
	public Long getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "parent_id")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
