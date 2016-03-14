package com.gqhmt.extServInter.dto.loan;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  柯禹来     1.0     1.0 Version
 */
public class WithDrawApplyResponse  extends Response {
	private FssTradeApplyEntity fssTradeApplyEntity;

	public FssTradeApplyEntity getFssTradeApplyEntity() {
		return fssTradeApplyEntity;
	}

	public void setFssTradeApplyEntity(FssTradeApplyEntity fssTradeApplyEntity) {
		this.fssTradeApplyEntity = fssTradeApplyEntity;
	}
	
}
