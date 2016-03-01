package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import java.util.List;

/**
 * Filename:    com.gqhmt.extServInter.dto.asset.BalanceResponse
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/2/28 22:45
 * Description:资金流水
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/2/28  于泳      1.0     1.0 Version
 */
public class TradeFlowResponse extends Response {

    private  List<FundSequenceEntity> list;

	public List<FundSequenceEntity> getList() {
		return list;
	}

	public void setList(List<FundSequenceEntity> list) {
		this.list = list;
	}

}
