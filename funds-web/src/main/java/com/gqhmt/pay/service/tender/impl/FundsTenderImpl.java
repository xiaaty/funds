package com.gqhmt.pay.service.tender.impl;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.fetchService.FetchDataService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.service.FuiouPreauthService;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.tender.IFundsTender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
public class FundsTenderImpl  implements IFundsTender {

    @Resource
    private FundSequenceService sequenceService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private BidService bidService;

    @Resource
    private FundOrderService fundOrderService;

    @Resource
    private FuiouPreauthService fuiouPreauthService;


    @Resource
    private TradeRecordService tradeRecordService;

    @Resource
    private FetchDataService fetchDataService;

	/**
	 * 投标
	 */
    @Override
    public boolean bid(String  tradeType ,String bid_id,String tender_no,String product_title,String cust_no,int invest_type,BigDecimal real_Amount,String  loan_cust_id,String  moto_cust_id,BigDecimal bonus_Amount) throws FssException {
       /* Tender tender = this.tenderService.findById(Integer.parseInt(bidDto.getTender_no()));
        tender.setBonusAmount(bidDto.getBonus_Amount());
        tender.setRealAmount(bidDto.getReal_Amount());
        tender.setInvestAmount(bidDto.getInvest_Amount());
        tender.setUserId(Integer.parseInt(bidDto.getUser_no()));
        tender.setCustomerId(Integer.parseInt(bidDto.getCust_no()));
        tender.setBidId(Long.parseLong(bidDto.getBusi_bid_no()));*/


        FundAccountEntity fromEntity = this.getFundAccount(Long.valueOf(cust_no), invest_type == 1 ? 3 : 2);
        this.hasEnoughBanlance(fromEntity,real_Amount);
        //判断抵押权人
        Long cusId = Long.valueOf(loan_cust_id);
        if (moto_cust_id!= null && !"".equals(moto_cust_id)){
            cusId =  Long.valueOf(moto_cust_id);
        }
//        Bid bid = this.bidService.findById(Long.parseLong(bidDto.getBusi_bid_no()));
//        int cusId = bid.getCustomerId();
//        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
//            cusId = bid.getHypothecarius();
//        }

        //处理抵押标

        // 入账账户
        FundAccountEntity toSFEntity = this.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_LOAN);
        // 冻结账户
        FundAccountEntity toEntity = this.getFundAccount(Long.valueOf(cust_no), GlobalConstants.ACCOUNT_TYPE_FREEZE);
        BigDecimal amount = real_Amount;//  bid.getRealAmount();
        BigDecimal boundsAmount = bonus_Amount;// tender.getBonusAmount();
        CommandResponse response = paySuperByFuiou.preAuth(fromEntity,toSFEntity,amount,GlobalConstants.ORDER_BID,Long.parseLong(bid_id),GlobalConstants.BUSINESS_BID,tradeType);
        //后续处理
        fuiouPreauthService.addFuiouPreauth(fromEntity, toSFEntity, real_Amount,Integer.parseInt(bid_id),Integer.parseInt(tender_no), response.getMap() != null ? (String) response.getMap().get("contract_no") : "", response.getFundOrderEntity());
        tradeRecordService.frozen(fromEntity,toEntity,amount,3001,response.getFundOrderEntity(),"出借" + product_title + " 资金 " + amount + "元" + (boundsAmount !=null ? ",红包抵扣资金 " + boundsAmount + "元" : ""), (boundsAmount != null? boundsAmount : BigDecimal.ZERO));
        return true;
    }




    @Override
    public boolean abortByTneder(String thirdPartyType, long tenderId) throws FssException {
        return true;
    }

    @Override
    public boolean abortByBid(String thirdPartyType, long bidId) throws FssException {
//        Bid bid = this.bidService.findById(bidId);
//        List<Tender> list = tenderService.queryTenderByBidId(bid.getId());
//        FundAccountEntity toSFEntity = super.getFundAccount(bid.getCustomerId(), GlobalConstants.ACCOUNT_TYPE_LOAN);
//        Map<Integer, String> map = fuiouPreauthService.getContractNo(bid.getId().longValue());
//        FundOrderEntity fundOrderEntity = super.createOrder(toSFEntity, bid.getBidAmount(), GlobalConstants.ORDER_ABORT_BID, bid.getId(), GlobalConstants.BUSINESS_BID, thirdPartyType);
//        fuiouFtpOrderService.addOrder(fundOrderEntity, 3);
//        super.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
//        throw new NeedSMSValidException("异步处理，等待回调通知");
        return true;
    }

    /**
     * 退款
     */
    public void abortLoop(Bid bid,Tender tender, String contactNo) throws FssException {
        // {
        // 实际出账账户
        FundAccountEntity fromEntity = fundAccountService.getFundAccount(tender.getCustomerId().longValue(), tender.getInvestType() == 1 ? 3 : 2);
        // super.hasEnoughBanlance(fromEntity,new
        // BigDecimal(tender.getInvestAmount()));

        Integer cusId = bid.getCustomerId();
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }

        //产品名称，如果产品名称为空，则去标的title
        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",tender.getBidId().toString());
        paramMap.put("type","2");
        String title = ""; // fetchDataService.featchDataSingle(String.class,"findProductName",paramMap);
//        if (title == null) {
//            title = bid.getBidTitle();
//        }
        // 入账账户
        FundAccountEntity toSFEntity = fundAccountService.getFundAccount(cusId.longValue(), GlobalConstants.ACCOUNT_TYPE_LOAN);
        // FundAccountThirdEntity toSFEntity =
        // super.getFundAccountThird(bid.getCustomerId(),thirdPartyType);
        // 冻结账户
        FundAccountEntity toEntity = fundAccountService.getFundAccount(tender.getCustomerId().longValue(), GlobalConstants.ACCOUNT_TYPE_FREEZE);
        FundOrderEntity orderEntity = paySuperByFuiou.canclePreAuth(fromEntity,toSFEntity,tender.getRealAmount(),3011,tender.getId(),0,contactNo);
        tradeRecordService.unFrozen(toEntity, fromEntity,tender.getRealAmount(), 3011, orderEntity,title + " 流标退款 " + new BigDecimal(tender.getRealAmount().toString()) + "元",BigDecimal.ZERO);
    }




    private FundAccountEntity getFundAccount(Long cusID, int type) throws CommandParmException {
        FundAccountEntity entity = null;
        if (cusID < 100) {
            entity = fundAccountService.getFundAccount(cusID, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        } else {
            entity = fundAccountService.getFundAccount(cusID, type);
        }

        if (entity == null) {
            throw new CommandParmException("90004006");
        }
        return entity;
    }

    private void hasEnoughBanlance(FundAccountEntity entity, BigDecimal amount) throws CommandParmException {
        BigDecimal bigDecimal = entity.getAmount();
        if (bigDecimal.compareTo(amount) < 0) {
            throw new CommandParmException("9004007");
        }
    }
}
