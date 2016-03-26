package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.FundSequenceDto;
import com.gqhmt.extServInter.dto.asset.FundSequenceResponse;
import com.gqhmt.extServInter.service.asset.IFundSeqence;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import com.gqhmt.pay.service.asset.IFundSequence;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户资产接口---账户资金流水查询
 * @author 柯禹来
 */
@Service
public class FundSeqenceImpl implements IFundSeqence{
	@Resource
	private IFundSequence fundSequenceImpl;
	
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	FundSequenceResponse response = new FundSequenceResponse();
    	try {
    		List<FundFlowBean> fundFlowlist=fundSequenceImpl.queryFundSequence((FundSequenceDto) dto);
    		response.setList(fundFlowlist);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
