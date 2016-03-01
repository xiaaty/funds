package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.TradeFlowResponse;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.extServInter.service.asset.IAccountTradFlow;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import com.gqhmt.pay.service.ITradeFlowRecord;
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
	private ITradeFlowRecord tradeFlowRecordImpl;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	TradeFlowResponse response = new TradeFlowResponse();
    	try {
    		List<FundFlowBean> fundFlowlist=tradeFlowRecordImpl.getTradFlow((TradflowDto) dto);
    		response.setList(fundFlowlist);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
