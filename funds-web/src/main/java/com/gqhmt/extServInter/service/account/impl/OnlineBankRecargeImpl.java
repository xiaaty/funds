package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.service.account.IOnlineBankRecarge;
import com.gqhmt.pay.service.asset.ITradeRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 网银充值
 * @author 柯禹来
 */
@Service
public class OnlineBankRecargeImpl  implements IOnlineBankRecarge{
	@Resource
	private ITradeRecord tradeRecordImpl;
	
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	/*try {
    		traderecord.getTradingRecord((TradeRecordDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code("90003");
		}*/
    	return response;
    }

}
