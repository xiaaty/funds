package com.gqhmt.pay.service.tender.impl;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.business.architect.loan.service.BonusService;
import com.gqhmt.business.architect.loan.service.TenderService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.tender.FullBidDto;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
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
import com.gqhmt.pay.service.tender.IFundFullTender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Filename:    com.gqhmt.pay.service.impl.FundsTenderImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/29 13:01
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/29  于泳      1.0     1.0 Version
 */
@Service
public class FundFullTenderImpl  implements IFundFullTender {
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
	 * 满标清算
	 */
    public void settle(FullBidDto fullbidto) throws FssException {
		/*Bid bid=bidService.findById(fullbidto.getBusi_id());
		//通过标的ID得到资金交易订单列表
		List<FundOrderEntity> listFundOrder = fundOrderService.queryFundOrder(GlobalConstants.ORDER_SETTLE, GlobalConstants.BUSINESS_SETTLE, bid.getId());
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
		
		List<Tender> list = tenderService.queryTenderByBidId(fullbidto.getBusi_id());
		FundAccountEntity toEntity = fundAccountService.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_LOAN);
		
		FundOrderEntity fundOrderEntity=null;
		try {
			fundOrderEntity = this.createOrder(toEntity, bid.getBidAmount(), GlobalConstants.ORDER_SETTLE, bid.getId(), GlobalConstants.BUSINESS_SETTLE, thirdPartyType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer, String> map = fuiouPreauthService.getContractNo(bid.getId().longValue());
		BigDecimal bonusAmount = BigDecimal.ZERO;
		
		List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
		for (Tender tender : list) {
			FundAccountEntity fromEntity = fundAccountService.getFundAccount(tender.getCustomerId(), GlobalConstants.ACCOUNT_TYPE_FREEZE);
			fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, tender.getRealAmount(), 2, title, map.get(tender.getId())));
			if (tender.getBonusAmount() != null) {
				bonusAmount = bonusAmount.add(tender.getBonusAmount());
			}
		}
		fuiouFtpColomFieldService.saveOrUpdateAll(fuiouFtpColomFields);

		if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
			FundAccountEntity fromEntity = fundAccountService.getFundAccount(4, GlobalConstants.ACCOUNT_TYPE_FREEZE);
			fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, bonusAmount, 2, title, null);
		}
		fuiouFtpOrderService.addOrder(fundOrderEntity, 1);
		this.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
		throw new FssException("异步处理，等待回调通知");*/
	}
    
    /**
     * 修改订单信息
     * @param fundOrderEntity
     * @param status
     * @param code
     * @param msg
     * @throws FssException
     */
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
	
 // 满标清算回调
 	private void settleTranserCallback(String orderNo)  throws FssException{

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
	 * 满标后，处理返现金额
	 * @param tenders
	 * @param bid
	 */
	private void pointDecode(List<Tender> tenders,Bid bid ){
		if(tenders.size()<=0){
			return;
		}
		///产品名称，如果产品名称为空，则去标的title
		String title  = bidService.getProductName(bid.getId());
		FundAccountEntity fromEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_FREEZE);
		FundOrderEntity fundOrderEntity=null;
		try {
			fundOrderEntity = this.createOrder(fromEntity, bid.getBidAmount(), GlobalConstants.ORDER_POINT_GQ_RETURN_FEE, bid.getId(), GlobalConstants.BUSINESS_SETTLE,thirdPartyType);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e.getMessage());
		}
		List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
		for (Tender tender : tenders) {
			FundAccountEntity toEntity = fundAccountService.getFundAccount(Long.valueOf(tender.getCustomerId()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
			fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, tender.getGuanqianAmount(), 5, title, null));
		}

		fuiouFtpColomFieldService.saveOrUpdateAll(fuiouFtpColomFields);

		fuiouFtpOrderService.addOrder(fundOrderEntity, 4);
	}
    
}
