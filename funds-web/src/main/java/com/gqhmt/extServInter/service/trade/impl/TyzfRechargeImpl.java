package com.gqhmt.extServInter.service.trade.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.trade.TyzfRechargeDto;
import com.gqhmt.extServInter.service.trade.ITyzfRecharge;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


@Service
public class TyzfRechargeImpl implements ITyzfRecharge {
	@Resource
	private IFundsTrade iFundsTrade;

	@APITradeTypeValid(value = "11030012")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			TyzfRechargeDto cDto=(TyzfRechargeDto)dto;
			iFundsTrade.tyzfRecharge(cDto.getMchn(),cDto.getSeq_no(),cDto.getTrade_type(),cDto.getAccountId(),cDto.getBusi_type(),cDto.getContract_no(),cDto.getAmt());
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
