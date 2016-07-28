package com.gqhmt.extServInter.dto.account;


import com.gqhmt.extServInter.dto.SuperDto;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月20日
 * Description: 银行卡变更接口参数
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月20日  jhz      1.0     1.0 Version
 */
public class DeleteBankCardDto extends SuperDto{


	public String card_id;//要删除的银行卡id

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
}
