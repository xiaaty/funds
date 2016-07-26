package com.gqhmt.extServInter.dto.account;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;

import java.util.List;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/07/26 14:09
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/07/26  xdw      1.0     1.0 Version
 */
public class soleBankCardResponse extends Response {

	private BankCardInfoEntity bankcard;

	public BankCardInfoEntity getBankcard() {
		return bankcard;
	}

	public void setBankcard(BankCardInfoEntity bankcard) {
		this.bankcard = bankcard;
	}
}
