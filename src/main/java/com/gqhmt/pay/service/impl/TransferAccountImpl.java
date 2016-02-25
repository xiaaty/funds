package com.gqhmt.pay.service.impl;

import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.core.factory.ThirdpartyFactory;
import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.tender.TransferDto;
import com.gqhmt.pay.service.ITransferAccount;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.util.ThirdPartyType;
import java.math.BigDecimal;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;

/**
 * Filename:    com.gqhmt.pay.service.impl.FundsTenderImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/29 13:01
 * Description:转账
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/29  柯禹来     1.0     1.0 Version
 */
@Service
public class TransferAccountImpl  implements ITransferAccount {

    @Resource
    private TradeRecordService tradeRecordService;
    
    @Resource
    private FundOrderService fundOrderService;
    
    @Resource
    private FundAccountService fundaccountService;
    
    @Resource
    private FundSequenceService fundSequenceService;
    
    @Resource
    private BankCardInfoService bankService;
    
    @Resource
    private FundTradeService fundTradeService;

    private final String thirdPartyType = PayCommondConstants.PAY_CHANNEL_FUIOU;
    /**
     * 转账
     */
	@Override
	public boolean transfer(TransferDto transferDto) throws FssException {
      FundAccountEntity fromEntity = fundaccountService.getFundAccount(transferDto.getFromCusID(), transferDto.getFromTyp());
      fundSequenceService.hasEnoughBanlance(fromEntity,transferDto.getAmount());
      FundAccountEntity toEntity = fundaccountService.getFundAccount(transferDto.getFromCusID(),transferDto.getToType());
      //订单号
      //BaseResponse response  = daQianService.transfer(primaryAccount,amount,transactionUsers,fundOrderEntity.getOrderNo(),"转账", EncryptUtil.daqianRsa(primaryAccount.getTradePassword()));
      //去掉账户余额查询和解冻操作20150829 于泳 直接将提现金额发给富友
      FundOrderEntity fundOrderEntity = fundOrderService.createOrder(fromEntity,toEntity,transferDto.getAmount(),BigDecimal.ZERO,transferDto.getSourceType().intValue()==0?GlobalConstants.ORDER_TRANSFER:transferDto.getSourceType(),Long.valueOf(transferDto.getSourceId()),Integer.valueOf(GlobalConstants.BUSINESS_TRANSFER),thirdPartyType);
      CommandResponse response = ThirdpartyFactory.command("2", "1", fundOrderEntity, fromEntity,toEntity,transferDto.getAmount());
      if(response.getCode().equals("0009")){
    	  fundOrderService.updateOrder(fundOrderEntity,GlobalConstants.ORDER_STATUS_THIRDERROR,response.getThirdReturnCode(),response.getMsg());
          throw new FssException("修改订单失败");
      }else if(!"0000".equals(response.getCode())){
    	  fundOrderService.updateOrder(fundOrderEntity,3,response.getCode(),response.getMsg());
    	  throw new FssException("修改订单失败");
      }
      try {
    	  fundSequenceService.transfer(fromEntity, toEntity, transferDto.getAmount(), 8, transferDto.getAccountType() == null ? 1005 : transferDto.getAccountType(),null,ThirdPartyType.FUIOU, fundOrderEntity);
    	  fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO, transferDto.getAmount(), transferDto.getAccountType() == null ? 1005 : transferDto.getAccountType(), "资金转出");
    	  fundTradeService.addFundTrade(toEntity, transferDto.getAmount(), BigDecimal.ZERO, transferDto.getAccountType() == null ? 1005 : transferDto.getAccountType(), "资金转入");
    	  fundOrderService.updateOrder(fundOrderEntity, 2, response.getCode(), response.getMsg());
//        super.callback(fundOrderEntity,1,"成功");
      }catch (RuntimeException e){
          throw new FssException("第三方成功，本地处理失败");
      }
      return true;
	}
	
	
}
