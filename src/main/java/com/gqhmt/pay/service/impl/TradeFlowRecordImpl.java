package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import com.gqhmt.pay.service.ITradeFlowRecord;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.funds.architect.account.service.FundSequenceService;

/**
 * 账户资金流水查询
 * @author 柯禹来
 */
@Service
public class TradeFlowRecordImpl  implements ITradeFlowRecord {
	
    @Resource
    private FundSequenceService fundSequenceService;
	
	/**
	 * 查询资金流水
	 * @param tradrecord
	 * @return
	 */
	public List<FundFlowBean> getTradFlow(TradflowDto tradflowDto) throws FssException{
		List<FundFlowBean> fundFlowBeanlist = fundSequenceService.getFundFlow(tradflowDto.getUser_no(),tradflowDto.getFundType());
		 if(fundFlowBeanlist==null){
			 throw new FssException("90002001");//账户信息不存在
		 }
		return fundFlowBeanlist;
	}
	
}
