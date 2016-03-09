package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.cost.CostDto;

public interface ITradeRecord {
	/**
	 * 费用收取、费用退回
	 * @param cost
	 * @return
	 * @throws FssException
	 */
	public boolean saveCostTrade(CostDto cost) throws FssException;
		
}
