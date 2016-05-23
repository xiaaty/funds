package com.gqhmt.fss.architect.holiday.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月22日
 * Description:节假日entity
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月22日  jhz      1.0     1.0 Version
 */

@Entity
@Table(name="t_gq_holiday_manage")
public class FssHolidayEntity implements Serializable {

    private static final long serialVersionUID = 8282814179630102060L;
    
    @Id
    @Column(name="id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "year")
    private String year;
    
    @Column(name = "holiday_date")
    private String holidayDate;
    
    @Column(name = "holiday_type")
    private Integer holidayType;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "create_time")
    private String createTime;
    
    @Column(name = "create_user_id")
    private Integer createUserId;
    
    @Column(name = "modify_time")
    private String modifyTime;
    
    @Column(name = "modify_user_id")
    private Integer modifyUserId;
    
    @Column(name = "is_delete")
    private Integer isDelete;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public Integer getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(Integer holidayType) {
		this.holidayType = holidayType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
