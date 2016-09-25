package com.gqhmt.extServInter.dto.bonus;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.fss.architect.bonus.bean.BonusBatchBean;

import java.util.List;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/12 14:09
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/12  jhz      1.0     1.0 Version
 */
public class BonusResponse extends Response {

	private List<BonusBatchBean> bonus_list;

	public List<BonusBatchBean> getBonus_list() {
		return bonus_list;
	}

	public void setBonus_list(List<BonusBatchBean> bonus_list) {
		this.bonus_list = bonus_list;
	}
}
