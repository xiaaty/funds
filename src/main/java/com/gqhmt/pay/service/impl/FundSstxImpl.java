package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.trade.SstxDto;
import com.gqhmt.pay.service.IFundSstx;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 实时提现
 * @author 柯禹来
 *
 */
@Service
public class FundSstxImpl  implements IFundSstx {

    @Resource
    private FundsTradeImpl fundsTradeImpl;
    
    /**
     * 实时提现
     */
	@Override
	public boolean sstxBusiness(SstxDto sstx) throws FssException {
		fundsTradeImpl.withdrawApply(sstx.getCust_no().intValue(), sstx.getBusi_type().intValue(), "",sstx.getAmt(), sstx.getBusi_id());
		return true;
	}
   
  
    
}
