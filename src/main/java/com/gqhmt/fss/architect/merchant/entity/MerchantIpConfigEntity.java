package com.gqhmt.fss.architect.merchant.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Filename:    com.gqhmt.sys.entity.Menu
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 李俊龙
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/21 18:37
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/18  李俊龙      1.0     1.0 Version
 */
//ip限制记录表
@Entity
@Table(name="t_gq_fss_mchn_ipconfig")
public class MerchantIpConfigEntity {
	// 主键
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 商户号
    @Column(name = "mchn_no")
    private String mchnNo;
    // ip地址
    @Column(name = "ip_address")
    private String ipAddress;
    // 0黑名单，1白名单
    @Column(name = "type")
    private String type;
    
    // 创建日期
    @Column(name = "create_time")
    private Date createTime;
    // 修改日期
    @Column(name = "modify_time")
    private Date modifyTime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMchnNo() {
		return mchnNo;
	}
	public void setMchnNo(String mchnNo) {
		this.mchnNo = mchnNo;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
