package com.gqhmt.fss.architect.backplate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月19日
 * Description:数据回盘实体
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月19日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_backplate")
public class FssBackplateEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;           		//bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    
    @Column(name = "seq_no")
    private String seqNo;   		//varchar(45) DEFAULT NULL COMMENT '流水号',
    
    @Column(name = "mchn")
    private String mchn  ;			//varchar(30) DEFAULT NULL COMMENT '商户号',
    
    @Column(name = "trade_type")
    private String tradeType   ;         	//varchar(8) DEFAULT NULL COMMENT '交易类型',                          
   
    
    @Column(name = "create_time")
    private Date createTime   ;         	//DEFAULT NULL COMMENT '创建时间',                      
    
    @Column(name = "modify_time")
    private Date modifyTime   ;         	//DEFAULT NULL COMMENT '修改时间',
    
    @Column(name = "repay_count")
    private int repayCount   ;         	//int(11) DEFAULT NULL COMMENT '回盘次数',
    
    @Column(name = "repay_result")
    private String repay_result   ;         	//varchar(45) DEFAULT NULL COMMENT '回盘结果',

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getMchn() {
		return mchn;
	}

	public void setMchn(String mchn) {
		this.mchn = mchn;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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

	public int getRepayCount() {
		return repayCount;
	}

	public void setRepayCount(int repayCount) {
		this.repayCount = repayCount;
	}

	public String getRepay_result() {
		return repay_result;
	}

	public void setRepay_result(String repay_result) {
		this.repay_result = repay_result;
	}
    
    
}