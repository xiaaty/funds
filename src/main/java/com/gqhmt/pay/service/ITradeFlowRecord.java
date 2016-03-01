package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import java.util.List;

public interface ITradeFlowRecord {

	/**
	 * 查询资金流水
	 * @param tradrecord
	 * @return
	 */
	public List<FundFlowBean> getTradFlow(TradflowDto tradflowDto) throws FssException;
	
}
