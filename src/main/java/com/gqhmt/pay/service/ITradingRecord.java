package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.fund.TradingRecordDto;

public interface ITradingRecord {

   
	/**
	 * 查询交易记录
	 * @param tradrecord
	 * @return
	 */
	public boolean getTradingRecord(TradingRecordDto tradrecord) throws FssException;
	
	
}
