package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.QueryListResponse;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.FundTradeDto;
import com.gqhmt.extServInter.service.asset.IFundTrade;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
/**
 * 账户资产接口---交易记录查询
 * @author 柯禹来
 */
@Service
public class FundTradeImpl implements IFundTrade{
	
	@Resource
	private IFundsTrade fundsTradeImpl;//交易记录接口
	
	/**
	 * 交易记录查询
	 */

	@AutoPage
	@Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
		QueryListResponse response = new QueryListResponse();
    	try {
    		List list = fundsTradeImpl.queryFundTrade((FundTradeDto)dto);
			response.setPlain(list);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
