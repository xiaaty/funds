package com.gqhmt.pay.service.tender.impl;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.business.architect.loan.service.BonusService;
import com.gqhmt.business.architect.loan.service.TenderService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.tender.FailureBidDto;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.service.FuiouPreauthService;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.tender.IFundFailure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.pay.service.impl.FundsTenderImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/29 13:01
 * Description:流标
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/29  柯禹来     1.0     1.0 Version
 */
@Service
public class FundFailureImpl  implements IFundFailure{
    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private TenderService tenderService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private BidService bidService;

    @Resource
    private FundOrderService fundOrderService;
    
    @Resource
    private FuiouPreauthService fuiouPreauthService;
    
    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;
    
    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;
    
    @Resource
    private FundSequenceService sequenceService;
    
    @Resource
    private FundTradeService fundTradeService;
    
    @Resource
    private BonusService bonusService;
    
    private final String thirdPartyType = PayCommondConstants.PAY_CHANNEL_FUIOU;
    /**
	 * 流标
	 * @param failurebid
	 */
	@Override
	public void abort(FailureBidDto failurebid) throws FssException {
		Bid bid = bidService.findById(failurebid.getBusi_id());
		List<Tender> list = tenderService.queryTenderByBidId(Long.valueOf(bid.getId()));
		int cusId = bid.getCustomerId();
		FundAccountEntity toSFEntity = fundAccountService.getFundAccount(Long.valueOf(cusId), GlobalConstants.ACCOUNT_TYPE_LOAN);
		Map<Integer, String> map = fuiouPreauthService.getContractNo(bid.getId().longValue());
		FundOrderEntity fundOrderEntity=null;
		try {
			fundOrderEntity = this.createOrder(toSFEntity, bid.getBidAmount(), GlobalConstants.ORDER_ABORT_BID, bid.getId(), GlobalConstants.BUSINESS_BID, thirdPartyType);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
		}
		fuiouFtpOrderService.addOrder(fundOrderEntity, 3);
		this.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
		throw new FssException("异步处理，等待回调通知");
	}

	 /**
     * 创建订单
     * @param primaryAccount
     * @param amount
     * @param orderType
     * @param sourceID
     * @param sourceType
     * @param thirdPartyType
     * @return
     * @throws Exception
     */
    public FundOrderEntity createOrder(FundAccountEntity primaryAccount, BigDecimal amount, int orderType, long sourceID, Integer sourceType, String thirdPartyType) throws Exception{
    	return this.createOrder(primaryAccount, null, amount, orderType, sourceID, sourceType, thirdPartyType);
	}

    public FundOrderEntity createOrder(FundAccountEntity primaryAccount, FundAccountEntity toAccountEntity,BigDecimal amount, Integer orderType, Long sourceID, Integer sourceType, String thirdPartyType) throws FssException {
        return fundOrderService.createOrder(primaryAccount,toAccountEntity,amount,BigDecimal.ZERO,orderType,sourceID,sourceType,thirdPartyType);
    }

    /**
     * 修改订单信息
     * @param fundOrderEntity
     * @param status
     * @param code
     * @param msg
     * @throws FssException
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, noRollbackFor = { FssException.class }, readOnly = false)
	public final void updateOrder(FundOrderEntity fundOrderEntity, int status, String code, String msg) throws FssException {
		fundOrderEntity.setOrderState(status);
		fundOrderEntity.setRetCode(code);
		fundOrderEntity.setRetMessage(msg);
		try {
			fundOrderService.update(fundOrderEntity);
		} catch (Exception e) {
			throw new FssException(e.getMessage());
		}
	}








    
    
    
  
}
