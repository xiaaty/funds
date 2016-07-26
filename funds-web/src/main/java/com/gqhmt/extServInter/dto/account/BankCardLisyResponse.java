package com.gqhmt.extServInter.dto.account;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;

import java.util.List;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/7/25 14:09
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/7/25  jhz     1.0     1.0 Version
 */
public class BankCardLisyResponse extends Response {

	private List<BankCardInfoEntity> card_list;


	public List<BankCardInfoEntity> getCard_list() {
		return card_list;
	}

	public void setCard_list(List<BankCardInfoEntity> card_list) {
		this.card_list = card_list;
	}
}
