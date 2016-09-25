package com.gqhmt.fss.architect.bonus.bean;

import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/06/29
 * Description:债权转让实体类
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/06/29 jhz    1.0     1.0 Version
 */
public class BonusBatchBean {
	private String seq_no;
	private String resp_code;
	private String resp_msg;


	public String getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}

	public String getResp_code() {
		return resp_code;
	}

	public void setResp_code(String resp_code) {
		this.resp_code = resp_code;
	}

	public String getResp_msg() {
		return resp_msg;
	}

	public void setResp_msg(String resp_msg) {
		this.resp_msg = resp_msg;
	}

}
