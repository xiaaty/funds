package com.gqhmt.extServInter.service.cost.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.extServInter.service.cost.ICost;
import com.gqhmt.pay.service.asset.ITradeRecord;
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
public class CostImpl implements ICost{
	@Resource
	private ITradeRecord tradeRecordImpl;//交易记录接口
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
    		tradeRecordImpl.saveCostTrade((CostDto)dto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
