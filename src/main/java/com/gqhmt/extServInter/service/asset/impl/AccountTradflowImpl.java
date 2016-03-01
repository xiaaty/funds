package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.TradeFlowResponse;
import com.gqhmt.extServInter.dto.asset.TradeRecordDto;
import com.gqhmt.extServInter.service.asset.IAccountTradFlow;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.pay.service.ITradeRecord;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 账户资产接口---账户资金流水查询
 * @author 柯禹来
 */
@Service
public class AccountTradflowImpl implements IAccountTradFlow{
	@Resource
	private ITradeRecord tradeRecordImpl;//交易记录接口
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	TradeFlowResponse response = new TradeFlowResponse();
    	try {
    		List<FundSequenceEntity> list=tradeRecordImpl.getTradFlow((TradeRecordDto) dto);
    		response.setList(list);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
