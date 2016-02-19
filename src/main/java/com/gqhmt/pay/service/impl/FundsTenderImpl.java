package com.gqhmt.pay.service.impl;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.BidRepayment;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.business.architect.loan.service.TenderService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.IFundsTender;
import com.gqhmt.pay.service.PaySuperByFuiouTest;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
public class FundsTenderImpl  implements IFundsTender {

    @Resource
    private PaySuperByFuiouTest paySuperByFuiou;

    @Resource
    private TenderService tenderService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private BidService bidService;

    @Resource
    private FundOrderService fundOrderService;


    @Override
    public boolean bid(String thirdPartyType, Long tenderId) throws FssException {
        Tender tender = this.tenderService.findById(tenderId);
        FundAccountEntity fromEntity = this.getFundAccount(tender.getCustomerId(), tender.getInvestType() == 1 ? 3 : 2);
        this.hasEnoughBanlance(fromEntity,tender.getRealAmount());

        Bid bid = this.bidService.findById(tender.getBidId());
        int cusId = bid.getCustomerId();
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }

        // 入账账户
        FundAccountEntity toSFEntity = this.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_LOAN);
        // 冻结账户
        FundAccountEntity toEntity = this.getFundAccount(tender.getCustomerId(), GlobalConstants.ACCOUNT_TYPE_FREEZE);
        BigDecimal amount = tender.getRealAmount();
        BigDecimal boundsAmount = tender.getBonusAmount();
        paySuperByFuiou.preAuth(fromEntity,toSFEntity,amount,GlobalConstants.ORDER_BID,tenderId,GlobalConstants.BUSINESS_BID);
        //后续处理
        return true;
    }

    @Override
    public boolean sellte(String thirdPartyType, long bidId) throws FssException {
        Bid bid = this.bidService.findById(bidId);
        List<FundOrderEntity> listFundOrder = this.fundOrderService.queryFundOrder(GlobalConstants.ORDER_SETTLE, GlobalConstants.BUSINESS_SETTLE, bid.getId());

        checkRepat(listFundOrder);

        Integer cusId = bid.getCustomerId();
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }
        //产品名称，如果产品名称为空，则去标的title
//        String title = getProductName(bid);

        FundAccountEntity toEntity = this.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_LOAN);// .getFundAccount(cusId,
        List<Tender> list = null;//tenderService.queryTenderByBidId(bid.getId());
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(toEntity, bid.getBidAmount(), GlobalConstants.ORDER_SETTLE, bid.getId(), GlobalConstants.BUSINESS_SETTLE, thirdPartyType);
//        Map<Integer, String> map = fuiouPreauthService.getContractNo(bid.getId().longValue());
//        BigDecimal bonusAmount = BigDecimal.ZERO;
//        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
//        for (Tender tender : list) {
//            FundAccountEntity fromEntity = super.getFundAccount(tender.getCustomerId(), GlobalConstants.ACCOUNT_TYPE_FREEZE, true);
//            // super.fundsRecordService.add(fromEntity,toEntity,fundOrderEntity,bid.getId().longValue(),null,1,
//            // "投标" + bid.getBidTitle() + " 成功， 金额 " +tender.getInvestAmount() +
//            // "元");
//            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, new BigDecimal(Double.toString(tender.getRealAmount())), 2, title, map.get(tender.getId())));
//            if (tender.getBonusAmount() != null) {
//                bonusAmount = bonusAmount.add(new BigDecimal(Double.toString(tender.getBonusAmount())));
//            }
//        }
//
//        fuiouFtpColomFieldService.saveOrUpdateAll(fuiouFtpColomFields);
//
//        if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
//            FundAccountEntity fromEntity = super.getFundAccount(4, GlobalConstants.ACCOUNT_TYPE_FREEZE, true);
//            fuiouFtpColomFieldService.addColomField(fromEntity, toEntity, fundOrderEntity, bonusAmount, 2, title, null);
//        }

//        fuiouFtpOrderService.addOrder(fundOrderEntity, 1);
//        super.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
//        throw new NeedSMSValidException("异步处理，等待回调通知");
        return true;
    }

    @Override
    public boolean repayment(String thirdPartyType, long bidId, long bidRepaymentId) throws FssException {
        Bid bid = this.bidService.findById(bidId);
        BidRepayment bidRepayment = null;
        List<FundOrderEntity> listFundOrder = this.fundOrderService.queryFundOrder(GlobalConstants.ORDER_REPAYMENT, GlobalConstants.BUSINESS_REPAYMENT, bidRepayment.getId());
        checkRepat(listFundOrder);

        Integer cusId = bid.getCustomerId();
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }

//        //产品名称，如果产品名称为空，则去标的title
//        String title = getProductName(bid);
//        List<RepaymentBean> list = (List<RepaymentBean>) objects[2];
//
//        BigDecimal sumRepay = bidRepayment.getBidPreAmount().add(bidRepayment.getPreDiffAmount());
//        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
//            sumRepay = bidRepayment.getBidPreAmount();
//        }
//        // 获取主账户信息
//        // 实际出账账户
//        FundAccountEntity fromEntity = super.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_LOAN, true);
//        super.hasEnoughBanlance(fromEntity, sumRepay);
//
//
//        FundOrderEntity fundOrderEntity = super.createOrder(fromEntity, sumRepay, GlobalConstants.ORDER_REPAYMENT, bidRepayment.getId(), GlobalConstants.BUSINESS_REPAYMENT, thirdPartyType);
//        for (RepaymentBean bean : list) {
//            FundAccountEntity toEntity = super.getFundAccount(bean.getCustomerId(), bean.getInvestType(), true);
//            if (bean.getRepaymentAmount().multiply(new BigDecimal("100")).longValue() <= 0) {
//                continue;
//            }
//            super.fundsRecordService.add(fromEntity, toEntity, fundOrderEntity, bid.getId().longValue(), null, 2, "产品" + title + "，还款本金" + bean.getRepaymentPrincipal() + "元，还款利息" + bean.getRepaymentInterest() + "元,合计：" + bean.getRepaymentAmount() + "元");
//            fuiouFtpColomFieldService.addColomField(fromEntity, toEntity, fundOrderEntity, bean.getRepaymentAmount(), 3, title, "");
//        }
//        fuiouFtpOrderService.addOrder(fundOrderEntity, 2);
//        super.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
//        throw new NeedSMSValidException("异步处理，等待回调通知");
        return true;
    }

    @Override
    public boolean abortByTneder(String thirdPartyType, long tenderId) throws FssException {
        return true;
    }

    @Override
    public boolean abortByBid(String thirdPartyType, long bidId) throws FssException {
        Bid bid = this.bidService.findById(bidId);
//        List<Tender> list = tenderService.queryTenderByBidId(bid.getId());
//        FundAccountEntity toSFEntity = super.getFundAccount(bid.getCustomerId(), GlobalConstants.ACCOUNT_TYPE_LOAN);
//        Map<Integer, String> map = fuiouPreauthService.getContractNo(bid.getId().longValue());
//        FundOrderEntity fundOrderEntity = super.createOrder(toSFEntity, bid.getBidAmount(), GlobalConstants.ORDER_ABORT_BID, bid.getId(), GlobalConstants.BUSINESS_BID, thirdPartyType);
//        fuiouFtpOrderService.addOrder(fundOrderEntity, 3);
//        super.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
//        throw new NeedSMSValidException("异步处理，等待回调通知");
        return true;
    }


    private void checkRepat(List<FundOrderEntity> listFundOrder) throws FssException {
        if (listFundOrder != null && listFundOrder.size() > 0) {
            FundOrderEntity fundOrderEntity = listFundOrder.get(0);
            if (fundOrderEntity.getOrderState() == 2) {
                throw new FssException("系统检测交易已成功，请核查");
            } else {
                throw new FssException("请勿重复提交");
            }
        }
    }

    private FundAccountEntity getFundAccount(int cusID, int type) throws CommandParmException {
        FundAccountEntity entity = null;
        if (cusID < 100) {
            entity = fundAccountService.getFundAccount(cusID, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        } else {
            entity = fundAccountService.getFundAccount(cusID, type);
        }

        if (entity == null) {
            throw new CommandParmException("账户不存在");
        }
        return entity;
    }

    private void hasEnoughBanlance(FundAccountEntity entity, BigDecimal amount) throws CommandParmException {
        BigDecimal bigDecimal = entity.getAmount();
        if (bigDecimal.compareTo(amount) < 0) {
            throw new CommandParmException("账户余额不足");
        }
    }
}
