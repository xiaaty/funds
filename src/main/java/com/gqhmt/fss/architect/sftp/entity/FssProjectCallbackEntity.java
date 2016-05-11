package com.gqhmt.fss.architect.sftp.entity;

import java.io.Serializable;

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
 * Create at:   2016年5月6日
 * Description:
 * <p>项目信息回盘
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月6日  jhz      1.0     1.0 Version
 */
@Entity
@Table(name = "t_gq_fss_sftp_project_callback")
public class FssProjectCallbackEntity implements Serializable{


    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name") 
    private String  itemName;  //项目名称
    
    @Column(name = "item_no")
    private String  itemNo;   //项目编号
    
    @Column(name = "pay_channel")
    private String payChannel;		//支付机构平台ID
    
    @Column(name = "resp_code")
    private String respCode;		//应答码(0-成功，1-失败,112115-借款人证件号码不能为空，112110-项目信息已存在,112111-平台信息不存在,112112-系统信息不存在)',
    
    @Column(name = "resp_msg")
    private String respMsg;		//应答描述
    
    @Column(name = "bid_id")
    private String bidId;		//银行标的ID
    
    @Column(name = "failed_msg")
    private String failedMsg;			//拒绝原因
    
    @Column(name = "status")
    private String status;		//审核状态(S-待审核，P-通过)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getFailedMsg() {
		return failedMsg;
	}

	public void setFailedMsg(String failedMsg) {
		this.failedMsg = failedMsg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
    
}