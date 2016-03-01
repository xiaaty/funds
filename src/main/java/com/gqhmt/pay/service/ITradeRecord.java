package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import com.gqhmt.extServInter.dto.asset.TradeRecordDto;
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
	public List<FundFlowBean> getTradFlow(TradflowDto tradflowDto) throws FssException;
	
	/**
	 * 费用收取、费用退回
	 * @param cost
	 * @return
	 * @throws FssException
	 */
	public boolean saveCostTrade(CostDto cost) throws FssException;
		
}
