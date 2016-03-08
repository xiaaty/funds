package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.asset.FundTradeDto;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;
import java.util.List;

public interface IFundTrade {

   
	/**
	 * 查询交易记录
	 * @param tradrecord
	 * @return
	 */
	public List<FundTradeEntity> getTradeRecord(FundTradeDto tradrecord) throws FssException;
	
		
}
