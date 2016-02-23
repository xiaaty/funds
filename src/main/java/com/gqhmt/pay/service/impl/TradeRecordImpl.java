package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.fund.CostDto;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.extServInter.dto.fund.TradingRecordDto;
import com.gqhmt.pay.service.ITradingRecord;
import com.gqhmt.pay.service.TradeRecordService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.funds.architect.account.bean.FundAccountSequenceBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;
import org.springframework.stereotype.Service;

/**
 * 交易记录查询
 * @author 柯禹来
 *
 */
@Service
public class TradeRecordImpl  implements ITradingRecord {

    @Resource
    private TradeRecordService tradeRecordService;
    
    @Resource
    private FundAccountService fundaccountService;
    
    @Resource
    private FundSequenceService fundSequenceService;

	/**
	 * 查询交易记录
	 * @param tradrecord
	 * @return
	 */
	public boolean getTradingRecord(TradingRecordDto tradrecord) throws FssException{
		boolean b=true;
		List<FundTradeEntity> tradelist= tradeRecordService.getTradeRecordByParams(tradrecord.getCust_no(),tradrecord.getUser_no(),tradrecord.getBusi_no(),tradrecord.getTrade_type());
		if(tradelist==null){
			b= false;
		}else{
			b=true;
		}
		return b;
	}
	
	/**
	 * 查询资金流水
	 * @param tradrecord
	 * @return
	 */
	public boolean getTradFlow(TradflowDto tradflow) throws FssException{
		boolean b=true;
		List<FundAccountSequenceBean> tradelist= tradeRecordService.getTradFlowByParams(tradflow.getCust_no(),tradflow.getUser_no(),tradflow.getBusi_no());
		if(tradelist==null){
			b=false;
		}else{
			b=true;
		}
		return b;
	}
	
	/**
	 * 费用接口
	 */
	public boolean saveCostTrade(CostDto cost) throws FssException{
		/*
		  cost.getPlatform();//所属平台
		  cost.getRegion();//所属大区
		  cost.getFiliale();//所属分公司
	 	*/	
		//根据账户账号查询该账户信息
		FundAccountEntity fundaccount=fundaccountService.getFundAccount(cost.getCust_no(), 3);
		if(null!=fundaccount){
			fundaccount.getAmount();//得到账户余额
		}
		BigDecimal totalAmaount=new BigDecimal(0);
		totalAmaount=fundaccount.getAmount().add(cost.getAmt());
		fundaccountService.savetoAccount(fundaccount.getId(),totalAmaount);//修改该账户的金额
		
		//保存到交易流水
		FundSequenceEntity fundsequence=new FundSequenceEntity();
		fundsequence.setActionType(3);
		fundsequence.setAccountId(Long.valueOf(fundaccount.getCustId()));
		fundsequence.setFundType(1006);
		fundsequence.setAmount(cost.getAmt());
		fundsequence.setCurrency("人民币");
		fundsequence.setCreateTime(new Date());
		fundsequence.setModifyTime(new Date());
		fundsequence.setSumary("");
		fundsequence.setOrderNo("");
		fundsequence.setThirdPartyType(1);
		fundsequence.setoAccountId(Long.valueOf(cost.getCust_no()));
		fundsequence.setToken("");
		fundSequenceService.insertFundSequence(fundsequence);
		return true;
	}
	
	
	
}
