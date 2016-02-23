package com.gqhmt.extServInter.service.account.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.fund.CostDto;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.pay.service.ITradingRecord;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 费用接口---费用收取
 * @author 柯禹来
 */
@Service
public class CostImpl implements ICreateAccount{
	@Resource
	private ITradingRecord tradeRecordImpl;//交易记录接口
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		tradeRecordImpl.saveCostTrade((CostDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code("-1001003");
		}
    	return response;
    }
}
