package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.fund.TradingRecordDto;
import com.gqhmt.extServInter.service.asset.ITransaction;
import com.gqhmt.pay.service.ITradingRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 账户资产接口---交易记录查询
 * @author 柯禹来
 */
@Service
public class TransactionImpl implements ITransaction{
	@Resource
	private ITradingRecord tradeRecordImpl;//交易记录接口
	
	/**
	 * 交易记录查询
	 */
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		tradeRecordImpl.getTradingRecord((TradingRecordDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
