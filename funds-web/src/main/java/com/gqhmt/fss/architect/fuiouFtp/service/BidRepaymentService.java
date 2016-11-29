package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.business.architect.loan.bean.RepaymentBean;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.fetchService.FetchDataService;
import com.gqhmt.fss.architect.account.bean.FssMappingBean;
import com.gqhmt.fss.architect.account.service.FssMappingService;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.trade.impl.FundsTradeImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Filename:    com.gqhmt.fss.architect.fuiouFtp.service.BidRepaymentService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/29 17:16
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/29  于泳      1.0     1.0 Version
 */
@Service
public class BidRepaymentService extends BidSupper{


    @Resource
    private FundOrderService fundOrderService;


    @Resource
    private FetchDataService fetchDataService;

    @Resource
    private FundAccountService fundAccountService;


    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    @Resource
    private FssLoanService fssLoanService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;


    @Resource
    private FundSequenceService fundSequenceService;

    @Resource
    private FssBackplateService fssBackplateService;

    @Resource
    private FundsTradeImpl fundsTrade;
    @Resource
    private FssMappingService fssMappingService;

    public void BidRepayment(FssLoanEntity loanEntity) throws FssException {
        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11101002".equals(loanEntity.getTradeTypeParent())||"11101001".equals(loanEntity.getTradeTypeParent())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }
        Map<String,String > repParamMap = new HashMap<>();
        repParamMap.put("id",loanEntity.getBusiNo());

        String contractId = loanEntity.getContractId();
        Bid bid = null;
        List<RepaymentBean> list  = null;
        try {
            bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
            list =fetchDataService.featchData(RepaymentBean.class,"revicePayment",repParamMap);
        } catch (FssException e) {
            LogUtil.error(getClass(),e);
            throw  e;
        }


        Integer cusId = bid.getCustomerId();
        //抵押标, 处理custId为抵押权人id
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }
        //还款总额获取
        BigDecimal sumRepay  = BigDecimal.ZERO;





        //账户资金余额验证   todo
        if (sumRepay.multiply(new BigDecimal("100")).longValue() < 0) {
            try {
                fundsTrade.transfer(3,0,cusId,1,sumRepay,GlobalConstants.ORDER_TRANSFER,0,0l, "1119", null,null,null,bid.getCustomerId().longValue(), bid.getContractNo(),null);
            }catch (FssException e){
                LogUtil.error(this.getClass(),e);
            }

        }
        //利差补偿  todo

        //抵押标 还款资金转账


        // 实际出账账户
        FundAccountEntity fromEntity = fundAccountService.getFundAccount(cusId.longValue(), GlobalConstants.ACCOUNT_TYPE_LOAN);


        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(fromEntity, sumRepay, GlobalConstants.ORDER_REPAYMENT_NEW, loanEntity.getId(), GlobalConstants.BUSINESS_REPAYMENT,"1110",loanEntity.getTradeType());
        for (RepaymentBean bean : list) {
            FundAccountEntity toEntity = fundAccountService.getFundAccount((long)bean.getCustomerId(), bean.getInvestType());
            if (bean.getRepaymentAmount().multiply(new BigDecimal("100")).longValue() <= 0) {
                continue;
            }
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, bean.getRepaymentAmount(), 3, "", "",bean.getId(),bean.getCustomerId(),bean.getContractNo(),bid.getCustomerId().longValue(),bid.getContractNo()));
            //额外利息
            if(bean.getRepaymentExtrinterest().multiply(new BigDecimal("100")).longValue()<= 0){//额外利息不为0
                continue;
            }
            //获取红包账户
            FundAccountEntity  BondAccountEntity=null;
            List<FssMappingBean> mappinglist=fssMappingService.getMappingListByType("10010006");//获取所有运营商的红包账户
            if(mappinglist.size()>0){
                for(FssMappingBean entity:mappinglist){
                    if (entity.getAmount().compareTo(bean.getRepaymentExtrinterest())>=0){//账户余额大于红包金额，则从该账户扣除红包金额
                        BondAccountEntity=fundAccountService.getFundAccountById(entity.getAccountId());
                        break;
                    }
                }
            }else{
                throw new FssException("90004007");
            }
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(BondAccountEntity, toEntity, fundOrderEntity, bean.getRepaymentExtrinterest(), 3, "", "",-bean.getId(),bean.getCustomerId(),bean.getContractNo(),bid.getCustomerId().longValue(),bid.getContractNo()));
//          super.fundsRecordService.add(fromEntity, toEntity, fundOrderEntity, bid.getId().longValue(), null, 2, "产品" + title + "，还款本金" + bean.getRepaymentPrincipal() + "元，还款利息" + bean.getRepaymentInterest() + "元,合计：" + bean.getRepaymentAmount() + "元");
        }

        fuiouFtpColomFieldService.insertList(fuiouFtpColomFields);

        fuiouFtpOrderService.addOrder(fundOrderEntity, 2);
        loanEntity.setStatus("10050012");
        loanEntity.setModifyTime(new Date());
        loanEntity.setOrderNo(fundOrderEntity.getOrderNo());
        fssLoanService.update(loanEntity);
        paySuperByFuiou.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");


    }


    public void  BidRepaymentCallback(FundOrderEntity fundOrderEntity) throws FssException {
        if (fundOrderEntity.getOrderState() != 6 && fundOrderEntity.getOrderState() != 1001) {
            return;
        }

        FssLoanEntity loanEntity = fssLoanService.getFssLoanEntityById(fundOrderEntity.getOrderFrormId());

        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11101002".equals(loanEntity.getTradeTypeParent())||"11101001".equals(loanEntity.getTradeTypeParent())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }
        Map<String,String > repParamMap = new HashMap<>();
        repParamMap.put("id",loanEntity.getBusiNo());
        Bid bid = null;
        List<RepaymentBean> list  = null;
        String title = "";
        try {
            bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
            list =fetchDataService.featchData(RepaymentBean.class,"revicePayment",repParamMap);
            //产品名称，如果产品名称为空，则去标的title
            title  = UrlConnectUtil.sendDataReturnString("findProductName",paramMap);
        } catch (FssException e) {
            LogUtil.error(getClass(),e);
            throw  e;
        }


        Integer cusId = bid.getCustomerId();
        //抵押标, 处理custId为抵押权人id
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }
        //还款总额获取   todo
        BigDecimal sumRepay  = BigDecimal.ZERO;
        //出借人还款本息和
        BigDecimal sumAmount =BigDecimal.ZERO;
        for (RepaymentBean bean:list) {
            sumAmount.add(bean.getPayableAmount());
        }
        // 批量冻结
        FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(cusId), GlobalConstants.ACCOUNT_TYPE_LOAN);
        this.fundSequenceService.repaymentSequence(list,title,fromEntity,fundOrderEntity,sumRepay,bid,sumAmount,loanEntity.getTradeTypeParent(),loanEntity.getSeqNo());

        //修改订单信息
        paySuperByFuiou.updateOrder(fundOrderEntity, 2, "0000", "成功");

        //回盘处理
        fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(),loanEntity.getMchnChild(),loanEntity.getTradeType());
        loanEntity.setStatus("10050013");
        loanEntity.setModifyTime(new Date());
        fssLoanService.update(loanEntity);
        this.refunds(loanEntity,list);

    }



    public void complete(String orderNo) throws FssException {

        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
        if (fundOrderEntity.getOrderState() != 6 && fundOrderEntity.getOrderState() != 1001) {
            return;
        }

        FssLoanEntity loanEntity = fssLoanService.getFssLoanEntityById(fundOrderEntity.getOrderFrormId());

        String type = "1";
        if("11101002".equals(loanEntity.getTradeTypeParent())||"11101001".equals(loanEntity.getTradeTypeParent())){
            // paramMap.put("type","2");
            type = "2";
        }

        String contractId = loanEntity.getContractId();
        String busiNo = loanEntity.getBusiNo();
        super.initRepay(contractId,type,busiNo);

        List<RepaymentBean> list = super.getBidRepayment(contractId);


        //修改订单信息
        fundOrderService.updateOrder(fundOrderEntity, 2, "0000", "订单完成");

        //回盘处理 如果冠e通满标\借款 抵押权人提现 直接回盘,借款信用标满标,修改状态  todo

        fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(),loanEntity.getMchnChild(),loanEntity.getTradeType());
        loanEntity.setStatus("10050013");
        loanEntity.setModifyTime(new Date());
        fssLoanService.update(loanEntity);
        this.refunds(loanEntity,list);
    }


    public void refunds(FssLoanEntity loanEntity,List<RepaymentBean> list) throws FssException {
        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();


        BigDecimal sumRepay = BigDecimal.ZERO;
        for (RepaymentBean bean : list) {
            if (bean.getToPublicAmount().multiply(new BigDecimal("100")).longValue() <= 0) {
                continue;
            }
            sumRepay = sumRepay.add(bean.getToPublicAmount());
        }

        if(sumRepay.compareTo(BigDecimal.ZERO)<=0){
            return;
        }

        FundAccountEntity toAxAccountEntity = fundAccountService.getFundAccount(3l, GlobalConstants.ACCOUNT_TYPE_PRIMARY);

        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(toAxAccountEntity, sumRepay, GlobalConstants.ORDER_REPAYMENT_REFUND, loanEntity.getId(), GlobalConstants.BUSINESS_REPAYMENT,"1110","");
        for (RepaymentBean bean : list) {
            FundAccountEntity toEntity = fundAccountService.getFundAccount((long)bean.getCustomerId(), bean.getInvestType());
            if (bean.getToPublicAmount().multiply(new BigDecimal("100")).longValue() <= 0) {
                continue;
            }
//            super.fundsRecordService.add(fromEntity, toEntity, fundOrderEntity, bid.getId().longValue(), null, 2, "产品" + title + "，还款本金" + bean.getRepaymentPrincipal() + "元，还款利息" + bean.getRepaymentInterest() + "元,合计：" + bean.getRepaymentAmount() + "元");
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(toEntity,toAxAccountEntity, fundOrderEntity, bean.getToPublicAmount(), 3, "", "",bean.getId(),bean.getCustomerId(),bean.getContractNo(),null,null));
        }

        fuiouFtpColomFieldService.insertList(fuiouFtpColomFields);
        fuiouFtpOrderService.addOrder(fundOrderEntity, 8);
        paySuperByFuiou.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");

    }

    public void refundsCallBack(FundOrderEntity fundOrderEntity) throws FssException {
        if (fundOrderEntity.getOrderState() != 6 && fundOrderEntity.getOrderState() != 1001) {
            return;
        }
        FssLoanEntity loanEntity = fssLoanService.getFssLoanEntityById(fundOrderEntity.getOrderFrormId());

        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11101002".equals(loanEntity.getTradeTypeParent())||"11101001".equals(loanEntity.getTradeTypeParent())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }
        Map<String,String > repParamMap = new HashMap<>();
        repParamMap.put("id",loanEntity.getBusiNo());
        Bid bid = null;
        List<RepaymentBean> list  = null;
        String title = "";
        try {
            bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
            list =fetchDataService.featchData(RepaymentBean.class,"revicePayment",repParamMap);
            //产品名称，如果产品名称为空，则去标的title
            title  = UrlConnectUtil.sendDataReturnString("findProductName",paramMap);
        } catch (FssException e) {
            LogUtil.error(getClass(),e);
            throw  e;
        }
        try {
            list =fetchDataService.featchData(RepaymentBean.class,"revicePayment",repParamMap);
            FundAccountEntity toAxAccountEntity = fundAccountService.getFundAccount(3l, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
            this.fundSequenceService.repaymentSequenceRefund(list,toAxAccountEntity,fundOrderEntity,bid);
            //修改订单信息
            paySuperByFuiou.updateOrder(fundOrderEntity, 2, "0000", "成功");
        } catch (FssException e) {
            LogUtil.error(getClass(),e);
            throw e;
        }
    }


}
