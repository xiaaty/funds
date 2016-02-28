package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.fund.BankDto;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.extServInter.dto.fund.TradingRecordDto;

public interface ITradeRecord {

   
	/**
	 * 查询交易记录
	 * @param tradrecord
	 * @return
	 */
	public boolean getTradingRecord(TradingRecordDto tradrecord) throws FssException;
	
	/**
	 * 查询资金流水
	 * @param tradrecord
	 * @return
	 */
	public boolean getTradFlow(TradflowDto tradflow) throws FssException;
	
	/**
	 * 费用收取、费用退回
	 * @param cost
	 * @return
	 * @throws FssException
	 */
	public boolean saveCostTrade(CostDto cost) throws FssException;
		
	/**
	 * 银行列表
	 * @param cost
	 * @return
	 * @throws FssException
	 */
	public boolean getBankList(BankDto bank)  throws FssException;
}
