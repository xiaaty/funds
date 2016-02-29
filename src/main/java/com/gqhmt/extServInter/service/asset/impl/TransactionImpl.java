package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.AssetResponse;
import com.gqhmt.extServInter.dto.asset.TradeRecordDto;
import com.gqhmt.extServInter.service.asset.ITransaction;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;
import com.gqhmt.pay.service.ITradeRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户资产接口---交易记录查询
 * @author 柯禹来
 */
@Service
public class TransactionImpl implements ITransaction{
	@Resource
	private ITradeRecord tradeRecordImpl;//交易记录接口
	
	/**
	 * 交易记录查询
	 */
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
		AssetResponse response = new AssetResponse();
    	try {
    		List list = tradeRecordImpl.getTradeRecord((TradeRecordDto)dto);
			response.setPlain(list);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
