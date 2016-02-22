package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.fund.TradingRecordDto;
import com.gqhmt.pay.service.ITradingRecord;
import com.gqhmt.pay.service.TradeRecordService;
import javax.annotation.Resource;

/**
 * 交易记录查询
 * @author keyulai
 *
 */
public class TradeRecordImpl  implements ITradingRecord {

    @Resource
    private TradeRecordService tradeRecordService;


	/**
	 * 查询交易记录
	 * @param tradrecord
	 * @return
	 */
	public boolean getTradingRecord(TradingRecordDto tradrecord) throws FssException{
		tradeRecordService.getTradeRecordByParams(tradrecord.getMchn(),tradrecord.getSeq_no(),tradrecord.getTrade_type(),tradrecord.getCust_no(),tradrecord.getUser_no(),tradrecord.getBusi_no(),tradrecord.getSignature());
		return true;
	}
}
