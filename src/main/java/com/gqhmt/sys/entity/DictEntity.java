package com.gqhmt.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Filename:    com.gqhmt.sys.entity.Menu
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/18 18:37
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/18  于泳      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_dict")
public class DictEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Column(name = "dict_id")
    private String dictId;          

    @Column(name = "dict_name")
    private String dictName;           
   
    
    @Column(name = "create_user_id")
    private Long careateUserId;   
    
    @Column(name = "create_time")
    private Date createTime;
    
    
    @Column(name = "modify_user_id")
    private Long modifyUserId;

    @Column(name="modify_time")
    private Date modifyTime;
    
    @Column(name = "parent_id")
    private Long parentId;           
    
    @Column(name = "sort")
    private String sort;//排序

    @Column(name = "is_valid")
    private String isValid;//是否有效
    

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Long getCareateUserId() {
		return careateUserId;
	}

	public void setCareateUserId(Long careateUserId) {
		this.careateUserId = careateUserId;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	
}
