package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.fund.BankDto;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.extServInter.dto.asset.TradeRecordDto;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;

import java.util.List;

public interface ITradeRecord {

   
	/**
	 * 查询交易记录
	 * @param tradrecord
	 * @return
	 */
	public List<FundTradeEntity> getTradeRecord(TradeRecordDto tradrecord) throws FssException;
	
	/**
	 * 查询资金流水
	 * @param tradrecord
	 * @return
	 */
	public List<FundSequenceEntity> getTradFlow(TradeRecordDto tradrecord) throws FssException;
	
	/**
	 * 费用收取、费用退回
	 * @param cost
	 * @return
	 * @throws FssException
	 */
	public boolean saveCostTrade(CostDto cost) throws FssException;
		
	/**
	 * 银行列表
	 * @param bank
	 * @return
	 * @throws FssException
	 */
	public boolean getBankList(BankDto bank)  throws FssException;
}
