package com.gqhmt.pay.service.tender.impl;

import com.gqhmt.business.architect.loan.bean.RepaymentBean;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.BidRepayment;
import com.gqhmt.business.architect.loan.service.BidRepaymentService;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
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
import com.gqhmt.util.ThirdPartyType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
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
    
    @Resource
    private BidRepaymentService bidRepaymentService;
    
    @Resource
    private FundsRecorderService fundsRecordService;

    private final String thirdPartyType = PayCommondConstants.PAY_CHANNEL_FUIOU;
    /**
     * 还款
     */
	@Override
	public void repay(RepayDto repayDto) throws FssException {
		
		Bid bid = bidService.findById(repayDto.getBusi_id());
		BidRepayment bidRepayment = bidRepaymentService.getRepayMentByBid(bid.getId());
		List<FundOrderEntity> listFundOrder = fundOrderService.queryFundOrder(GlobalConstants.ORDER_REPAYMENT, GlobalConstants.BUSINESS_REPAYMENT, bidRepayment.getId());
		if (listFundOrder != null && listFundOrder.size() > 0) {
			FundOrderEntity fundOrderEntity = listFundOrder.get(0);
			if (fundOrderEntity.getOrderState() == 2) {
				throw new FssException("系统检测交易已成功，请核查");
			} else {
				throw new FssException("请勿重复提交");
			}
		}
		Integer cusId = bid.getCustomerId();
		if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
			cusId = bid.getHypothecarius();
		}

		//产品名称，如果产品名称为空，则去标的title
		String title  = bidService.getProductName(bid.getId());
		/***********************根据id获取订单的还款计划****************************************/
	    String orderNo="";//参数传递暂定
		FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
		Long id = fundOrderEntity.getOrderFrormId();
		List<RepaymentBean> list = bidRepaymentService.queryReceivedPaymentByRepaymentIdAndPeriod(Integer.valueOf(id.intValue()));
		
		BigDecimal sumRepay = bidRepayment.getBidPreAmount().add(bidRepayment.getPreDiffAmount());
		if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
			sumRepay = bidRepayment.getBidPreAmount();
		}
		// 获取主账户信息
		// 实际出账账户
		FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(cusId), GlobalConstants.ACCOUNT_TYPE_LOAN);
		sequenceService.hasEnoughBanlance(fromEntity, sumRepay);

		FundOrderEntity fundOrderEntity2=null;
		try {
			fundOrderEntity2 = this.createOrder(fromEntity, sumRepay, GlobalConstants.ORDER_REPAYMENT, bidRepayment.getId(), GlobalConstants.BUSINESS_REPAYMENT, thirdPartyType);
		} catch (Exception e) {
			throw new FssException(e.getMessage());
		}
		for (RepaymentBean bean : list) {
			FundAccountEntity toEntity = fundAccountService.getFundAccount(Long.valueOf(bean.getCustomerId()), bean.getInvestType());
			if (bean.getRepaymentAmount().multiply(new BigDecimal("100")).longValue() <= 0) {
				continue;
			}
			fundsRecordService.add(fromEntity, toEntity, fundOrderEntity2, bid.getId().longValue(), null, 2, "产品" + title + "，还款本金" + bean.getRepaymentPrincipal() + "元，还款利息" + bean.getRepaymentInterest() + "元,合计：" + bean.getRepaymentAmount() + "元");
			fuiouFtpColomFieldService.addColomField(fromEntity, toEntity, fundOrderEntity2, bean.getRepaymentAmount(), 3, title, "");
		}
		fuiouFtpOrderService.addOrder(fundOrderEntity2, 2);
		this.updateOrder(fundOrderEntity2, 6, "0002", "ftp异步处理");
		throw new FssException("异步处理，等待回调通知");

	}
	
	// 还款回调
	private void repayCallback(String orderNo) {
		FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
		Long id = fundOrderEntity.getOrderFrormId();
		BidRepayment bidRepayment=null;
		try {
			bidRepayment = bidRepaymentService.getRepayMentById(id);
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
		}
		BigDecimal sumRepay = bidRepayment.getBidPreAmount().add(bidRepayment.getPreDiffAmount());
		Bid bid = bidService.findById(Long.valueOf(bidRepayment.getBidId()));
		Integer cusId = bid.getCustomerId();
		if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
			cusId = bid.getHypothecarius();
		}
		List<RepaymentBean> list = bidRepaymentService.queryReceivedPaymentByRepaymentIdAndPeriod(Integer.valueOf(id.intValue()));
		// 批量冻结
		FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(cusId), GlobalConstants.ACCOUNT_TYPE_LOAN);
		this.repaySeq(bid, list, fromEntity, fundOrderEntity, bidRepayment, sumRepay);
	}
	/**
	 * 还款回调
	 * @param bid
	 * @param list
	 * @param fromEntity
	 * @param fundOrderEntity
	 * @param bidRepayment
	 * @param sumRepay
	 */
	private void repaySeq(Bid bid, List<RepaymentBean> list, FundAccountEntity fromEntity, FundOrderEntity fundOrderEntity, BidRepayment bidRepayment, BigDecimal sumRepay) {
		//产品名称，如果产品名称为空，则去标的title
		String title  = bidService.getProductName(bid.getId());
		ThirdPartyType thirdPartyType = ThirdPartyType.FUIOU;
		for (RepaymentBean bean : list) {
			FundAccountEntity toEntity = fundAccountService.getFundAccount(Long.valueOf(bean.getCustomerId()), bean.getInvestType() == 0 ? GlobalConstants.ACCOUNT_TYPE_PRIMARY : bean.getInvestType() == 1 ? 3 : 2);
			Map<Integer, Long> mapping = new HashMap<>();
			mapping.put(GlobalConstants.BUSINESS_MAPPINF_CUSTOMER, Long.valueOf(bid.getId()));
			mapping.put(GlobalConstants.BUSINESS_MAPPINF_BID, Long.valueOf(bid.getId()));
			mapping.put(GlobalConstants.BUSINESS_MAPPINF_REPAYMENT, Long.valueOf(bidRepayment.getId()));
			if (bean.getCustomerId() < GlobalConstants.RESERVED_CUSTOMERID_LIMIT) {
				if (bean.getRepaymentAmount().compareTo(BigDecimal.ZERO) > 0) {
					try {
						sequenceService.transfer(fromEntity, toEntity, bean.getRepaymentAmount(), 7, 4001,null,thirdPartyType.FUIOU, fundOrderEntity);
					} catch (FssException e) {
						LogUtil.error(this.getClass(), e);
					}
				}
			} else {
				if (bean.getRepaymentPrincipal().compareTo(BigDecimal.ZERO) > 0) {
					try {
						sequenceService.transfer(fromEntity, toEntity, bean.getRepaymentPrincipal(), 7, 3003,null,thirdPartyType, fundOrderEntity);
					} catch (FssException e) {
						LogUtil.error(this.getClass(), e);
					}
					try {
						this.createFundTrade(toEntity, bean.getRepaymentPrincipal(), BigDecimal.ZERO, 3005, title + " 收到还款本金 " + bean.getRepaymentPrincipal() + "元");
					} catch (FssException e) {
						LogUtil.error(this.getClass(), e);
					}
				}
				if (bean.getRepaymentInterest().compareTo(BigDecimal.ZERO) > 0) {
					try {
						sequenceService.transfer(fromEntity, toEntity, bean.getRepaymentInterest(), 7, 3004,null,thirdPartyType, fundOrderEntity);
					} catch (FssException e) {
						LogUtil.error(this.getClass(), e);
					}
					try {
						this.createFundTrade(toEntity, bean.getRepaymentInterest(), BigDecimal.ZERO, 3006, title + " 收到还款利息 " + bean.getRepaymentInterest() + "元");
					} catch (FssException e) {
						LogUtil.error(this.getClass(), e);
					}
				}
			}

			// 转到应付账户
			BigDecimal amount = bean.getPayableAmount();
			if (amount.compareTo(BigDecimal.ZERO) > 0) {
				FundAccountEntity toA0Account = fundAccountService.getFundAccount(Long.valueOf(bean.getCustomerId()), GlobalConstants.ACCOUNT_TYPE_PAYMENT);
				try {
					sequenceService.transfer(toEntity, toA0Account, amount, 7, 1005,null,thirdPartyType, fundOrderEntity);
				} catch (FssException e) {
					LogUtil.error(this.getClass(), e);
				}
			}
		}
		try {
			this.createFundTrade(fromEntity, BigDecimal.ZERO, sumRepay, 3003, title + " 还款成功，扣除还款金额 " + sumRepay + "元");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
		}
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
