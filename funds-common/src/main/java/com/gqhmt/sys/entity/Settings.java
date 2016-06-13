package com.gqhmt.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
 * 16/02/14  jhz     1.0     1.0 Version
 */
@Entity
@Table(name="t_gq_fss_sys_setting")
public class Settings {
		@Id
	    @Column(name = "id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;                                // int(11) NOT NULL AUTO_INCREMENT,

	    @Column(name = "seting_type")
	    private String setingType;                      //  varchar(45) NOT NULL COMMENT 

	    @Column(name="type")
	    private String type;  							//  varchar(45) NOT NULL COMMENT 
	    
	    @Column(name="mchn_no")
	    private String mchnNo;                      	//  varchar(45) NOT NULL COMMENT 
	    
	    @Column(name="set_key")
	    private String setKey;                             //  varchar(45) NOT NULL COMMENT 
	    
	    @Column(name="set_value")
	    private String setValue;       					//  varchar(45) NOT NULL COMMENT 

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSetingType() {
			return setingType;
		}

		public void setSetingType(String setingType) {
			this.setingType = setingType;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getMchnNo() {
			return mchnNo;
		}

		public void setMchnNo(String mchnNo) {
			this.mchnNo = mchnNo;
		}

		public String getSetKey() {
			return setKey;
		}

		public void setSetKey(String setKey) {
			this.setKey = setKey;
		}

		public String getSetValue() {
			return setValue;
		}

		public void setSetValue(String setValue) {
			this.setValue = setValue;
		}

		
	    
	    

}
