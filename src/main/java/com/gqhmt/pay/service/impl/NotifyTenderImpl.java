package com.gqhmt.pay.service.impl;

import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.core.factory.ThirdpartyFactory;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.tender.BidNotifyDto;
import com.gqhmt.extServInter.dto.tender.TransferDto;
import com.gqhmt.pay.service.INotifyTender;
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
 * Description:标的通知
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/29  柯禹来     1.0     1.0 Version
 */
@Service
public class NotifyTenderImpl  implements INotifyTender {

    @Resource
    private TradeRecordService tradeRecordService;
    
    @Resource
    private BidService bidService;
    
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
     * 标的通知
     */
	@Override
	public boolean bignotify(BidNotifyDto notifyDto) throws FssException {
		Bid bid = bidService.findById(notifyDto.getBusi_id());
		Integer cusId = bid.getCustomerId();

		if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
			cusId = bid.getHypothecarius();
		}
//		NotifyNewBidDTO.OperatingMode operatingMode = (NotifyNewBidDTO.OperatingMode) objects[1];
//		NotifyNewBidDTO.BidType bidType = (NotifyNewBidDTO.BidType) objects[2];
//		NotifyNewBidDTO.BidState bidState = (NotifyNewBidDTO.BidState) objects[3];

		BigDecimal repayAmount = BigDecimal.ZERO;
//		if (objects.length > 4) {
//			repayAmount = (BigDecimal) objects[4];
//		}
		FundAccountEntity entity = fundaccountService.getFundAccount(bid.getCustomerId(),3);
		FundOrderEntity fundOrderEntity = fundOrderService.createOrder(entity, null,BigDecimal.ZERO, BigDecimal.ZERO,GlobalConstants.ORDER_BID_NOTIFY, 0l, 0, thirdPartyType);
		CommandResponse response = ThirdpartyFactory.command(thirdPartyType, "1", fundOrderEntity, entity, bid, entity.getCustomerInfoEntity(),"", "", notifyDto.getState(), repayAmount);
		if (response != null && !response.getCode().equals("0000")) {
			fundOrderService.updateOrder(fundOrderEntity, 3, response.getCode(), response.getMsg());
			throw new FssException(response.getMsg());
		}
		fundOrderService.updateOrder(fundOrderEntity, 2, response.getCode(), response.getMsg());
		return true;
	}
    
	
	
	
}
