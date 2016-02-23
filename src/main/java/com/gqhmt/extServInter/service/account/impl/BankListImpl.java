package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.service.account.IBankList;
import com.gqhmt.pay.service.ITradingRecord;
import com.gqhmt.core.APIExcuteErrorException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 银行列表接口
 * @author 柯禹来
 */
@Service
public class BankListImpl implements IBankList{
	@Resource
	private ITradingRecord tradeRecordImpl;//交易记录接口
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    /*	try {
    		traderecord.getTradingRecord((TradingRecordDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code("90003");
		}*/
    	return response;
    }
}
