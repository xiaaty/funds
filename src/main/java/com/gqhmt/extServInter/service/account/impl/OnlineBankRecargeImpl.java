package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.service.account.IOnlineBankRecarge;
import com.gqhmt.pay.service.ITradingRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 网银充值
 * @author 柯禹来
 */
@Service
public class OnlineBankRecargeImpl  implements IOnlineBankRecarge{
	@Resource
	private ITradingRecord tradeRecordImpl;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	/*try {
    		traderecord.getTradingRecord((TradingRecordDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code("90003");
		}*/
    	return response;
    }

}
