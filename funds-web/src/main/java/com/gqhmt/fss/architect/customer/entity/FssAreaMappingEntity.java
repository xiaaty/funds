package com.gqhmt.fss.architect.customer.entity;
import javax.persistence.*;


/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月1日
 * Description:
 * <p>出借系统映射地区表
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月1日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_area_mapping")
public class FssAreaMappingEntity implements java.io.Serializable{

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                                    //int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',

    @Column(name="value")
    private String value;                             // 对应的银行或地区

    @Column(name="four_code")
    private String fourCode;				//地区所对应的四位码
    
    @Column(name="six_code")
    private String sixCode;				//地区所对应的六位码
    
    @Column(name="eight_code")
    private String eightCode;				//地区所对应的八位码
    
    @Column(name="type")
    private String type;				//类型

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFourCode() {
		return fourCode;
	}

	public void setFourCode(String fourCode) {
		this.fourCode = fourCode;
	}

	public String getSixCode() {
		return sixCode;
	}

	public void setSixCode(String sixCode) {
		this.sixCode = sixCode;
	}

	public String getEightCode() {
		return eightCode;
	}

	public void setEightCode(String eightCode) {
		this.eightCode = eightCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    

}