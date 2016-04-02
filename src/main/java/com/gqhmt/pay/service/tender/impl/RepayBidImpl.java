package com.gqhmt.pay.service.tender.impl;

import com.gqhmt.business.architect.loan.service.BidRepaymentService;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.tender.RepayDto;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.account.service.FundsRecorderService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.service.tender.IRepayBid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.pay.service.impl.FundsTenderImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/29 13:01
 * Description:还款
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/29  柯禹来     1.0     1.0 Version
 */
@Service
public class RepayBidImpl  implements IRepayBid{
    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private BidService bidService;

    @Resource
    private FundOrderService fundOrderService;
    
    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;
    
    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;
    
    @Resource
    private FundSequenceService sequenceService;
    
    @Resource
    private FundTradeService fundTradeService;
    
    @Resource(name = "oldBidRepaymentService")
    private BidRepaymentService bidRepaymentService;
    
    @Resource
    private FundsRecorderService fundsRecordService;

    private final String thirdPartyType = PayCommondConstants.PAY_CHANNEL_FUIOU;
    /**
     * 还款
     */
	@Override
	public void repay(RepayDto repayDto) throws FssException {
		


	}
	
	// 还款回调
	private void repayCallback(String orderNo) {

	}

	
	 //添加到交易记录信息 
		public void createFundTrade(FundAccountEntity entity, BigDecimal income, BigDecimal payment, int tradeType, String memo) throws FssException {
			try {
				fundTradeService.addFundTrade(entity,income, payment, tradeType, memo);
			} catch (Exception e) {
				LogUtil.error(this.getClass(), e.getMessage());
			}
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
