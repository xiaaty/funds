package com.gqhmt.extServInter.service.fund.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.fund.TradingRecordDto;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.pay.service.ITradingRecord;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 账户资产接口---交易记录查询
 * @author 57627
 */
@Service
public class TransactionImpl implements ICreateAccount{
	@Resource
	private ITradingRecord traderecord;//交易记录接口
	
	/**
	 * 交易记录查询
	 */
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		traderecord.getTradingRecord((TradingRecordDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code("90003");
		}
    	return response;
    }
}
