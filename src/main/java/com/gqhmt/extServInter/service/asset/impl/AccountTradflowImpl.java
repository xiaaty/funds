package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.extServInter.service.asset.IAccountTradFlow;
import com.gqhmt.pay.service.ITradingRecord;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 账户资产接口---账户资金流水查询
 * @author 柯禹来
 */
@Service
public class AccountTradflowImpl implements IAccountTradFlow{
	@Resource
	private ITradingRecord tradeRecordImpl;//交易记录接口
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		tradeRecordImpl.getTradFlow((TradflowDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
