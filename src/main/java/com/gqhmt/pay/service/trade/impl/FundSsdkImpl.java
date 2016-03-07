package com.gqhmt.pay.service.trade.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.trade.SsdkDto;
import com.gqhmt.pay.service.trade.IFundSsdk;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 实时代扣
 * @author 柯禹来
 */
@Service
public class FundSsdkImpl  implements IFundSsdk {
	@Resource
    private FundsTradeImpl fundsTradeImpl;
	
	@Override
	public boolean ssdkBusiness(SsdkDto ssdk) throws FssException {
		fundsTradeImpl.withholdingApply(ssdk.getCust_no().intValue(), ssdk.getBusi_type().intValue(),"", ssdk.getAmt(), ssdk.getBusi_id());
		return true;
	}

   
}
