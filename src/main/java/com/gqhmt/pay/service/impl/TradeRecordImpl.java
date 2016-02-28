package com.gqhmt.pay.service.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.asset.TradeRecordDto;
import com.gqhmt.extServInter.dto.fund.BankDto;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.pay.service.ITradeRecord;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.util.ThirdPartyType;
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
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;

/**
 * 交易记录查询
 * @author 柯禹来
 *
 */
@Service
public class TradeRecordImpl  implements ITradeRecord {

    @Resource
    private TradeRecordService tradeRecordService;
    
    @Resource
    private FundAccountService fundaccountService;
    
    @Resource
    private FundSequenceService fundSequenceService;
    
    @Resource
    private BankCardInfoService bankService;

	/**
	 * 查询交易记录
	 * @param tradrecord
	 * @return
	 */
	public List<FundTradeEntity> getTradeRecord(TradeRecordDto tradrecord) throws FssException{
		FundAccountEntity primaryAccount = fundaccountService.getFundAccount(tradrecord.getCust_no(), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
		 if(primaryAccount==null){
			 throw new FssException("90002001");//账户信息不存在
		 }
		 List<FundTradeEntity> tradelist= tradeRecordService.getTradeRecordByParams(tradrecord.getCust_no(),tradrecord.getUser_no(),tradrecord.getBusi_no(),tradrecord.getTrade_type());
		 if(tradelist.size()==0){
			throw new FssException("90003");
		 }
		 return tradelist;
	}
	
	/**
	 * 查询资金流水
	 * @param tradrecord
	 * @return
	 */
	public List<FundSequenceEntity> getTradFlow(TradeRecordDto tradrecord) throws FssException{
		FundAccountEntity primaryAccount = fundaccountService.getFundAccount(tradrecord.getCust_no(), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
		 if(primaryAccount==null){
			 throw new FssException("90002001");//账户信息不存在
		 }
		/*FundAccountSequenceBean accountsequence= tradeRecordService.getTradFlowByParams(tradrecord.getCust_no(),tradrecord.getUser_no(),tradrecord.getBusi_no());
		if(accountsequence==null){
			throw new FssException("90002005");
		}else{
			if(accountsequence.getAmount().equals(BigDecimal.ZERO)){
				throw new FssException("90004007");
			}
		}*/

		return null;
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
			BigDecimal totalAmaount=new BigDecimal(0);
			totalAmaount=fundaccount.getAmount().add(cost.getAmt());
			try {
				fundaccountService.savetoAccount(fundaccount.getId(),totalAmaount);//修改该账户的金额
			} catch (FssException e) {
				throw new FssException("90004012");
			}
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
			fundsequence.setThirdPartyType(ThirdPartyType.FUIOU);
			fundsequence.setoAccountId(Long.valueOf(cost.getCust_no()));
			fundsequence.setToken("");
			try {
				fundSequenceService.insertFundSequence(fundsequence);
			} catch (Exception e) {
				throw new FssException("90004012");
			}
			
		}else{
			throw new FssException("90004006");
		}
		return true;
	}
	/**
	 * 银行列表
	 */
	public boolean getBankList(BankDto bank)  throws FssException{
		BankEntity bankentity=bankService.getBankById(bank.getId());
		if(bankentity==null){
			throw new FssException("没有查到该银行信息");
		}
		return true;
	}
	
}
