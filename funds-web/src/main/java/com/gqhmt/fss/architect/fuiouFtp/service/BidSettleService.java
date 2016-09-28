package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.fetchService.FetchDataService;
import com.gqhmt.fss.architect.account.entity.FssMappingEntity;
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
import com.gqhmt.funds.architect.trade.service.FuiouPreauthService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Filename:    com.gqhmt.fss.architect.fuiouFtp.service.SettleService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/15 17:49
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/15  于泳      1.0     1.0 Version
 */
@Service
public class BidSettleService extends BidSupper{

    @Resource
    private FetchDataService fetchDataService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private FuiouPreauthService fuiouPreauthService;

    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    @Resource
    private FssLoanService fssLoanService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private FundOrderService fundOrderService;

    @Resource
    private FundSequenceService fundSequenceService;

    @Resource
    private FssBackplateService fssBackplateService;

    @Resource
    private FssMappingService fssMappingService;

    public void settle(FssLoanEntity loanEntity) throws FssException {
        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11090004".equals(loanEntity.getTradeType())||"11090006".equals(loanEntity.getTradeType())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }

        String contractId = loanEntity.getContractId();
        Bid bid = null;
        List<Tender> list  = null;
        try {
            bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
            list = fetchDataService.featchData(Tender.class,"tenderList",paramMap);
        } catch (FssException e) {
            LogUtil.error(getClass(),e);
            return;
        }
        // List<FundOrderEntity> listFundOrder = fundOrderService.queryFundOrder(GlobalConstants.ORDER_SETTLE, GlobalConstants.BUSINESS_SETTLE, bid);


        //验证标的金额与满标金额是否相等  todo

        //抵押标抵押权人判断
        Integer cusId = bid.getCustomerId();
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }

        FundAccountEntity toEntity = fundAccountService.getFundAccount(cusId.longValue(), GlobalConstants.ACCOUNT_TYPE_LOAN);

        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(toEntity, loanEntity.getPayAmt(), GlobalConstants.ORDER_SETTLE_NEW, loanEntity.getId(), GlobalConstants.BUSINESS_SETTLE, "1109",loanEntity.getTradeType());


        Map<Long, String> map = fuiouPreauthService.getContractNo(bid.getId().longValue());
        BigDecimal bonusAmount = BigDecimal.ZERO;

        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
        for (Tender tender : list) {
            FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(tender.getCustomerId()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, tender.getRealAmount(), 2, "", map.get(tender.getId()),tender.getId(),fromEntity.getCustId().longValue(),tender.getContractNo(),bid.getCustomerId().longValue(),bid.getContractNo()));
            if (tender.getBonusAmount() != null) {
                bonusAmount = bonusAmount.add(tender.getBonusAmount());
            }
        }
        if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
//            FundAccountEntity fromEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_FREEZE);
            FundAccountEntity fromEntity=null;
            //获取所有运营商的红包账户，（通过custId关联红包账户表查询）
//            List<FundAccountEntity> redAccountList=fundAccountService.getRedAccountList();
            List<FssMappingEntity> mappinglist=fssMappingService.getMappingListByType("10010006");
            List list3=list=new ArrayList();
            for(FssMappingEntity  mappingEntity:mappinglist){
                list3.add(mappingEntity.getCustId());
            }
            List<FundAccountEntity> redAccountList=fundAccountService.getRedAccountList(list3);
            Map<String,Object> map2=new HashMap<String,Object>();
            if(redAccountList!=null && redAccountList.size()>0){
                for(FundAccountEntity entity:redAccountList){
                    if (entity.getAmount().compareTo(bonusAmount)>=0){//账户余额大于红包金额，则从该账户扣除红包金额
                        map2.put("account",entity);
                        break;
                    }
                }
                FundAccountEntity redAccountEntity=(FundAccountEntity)map2.get("account");//获取到金额大于红包金额的红包账户
                LogUtil.info(this.getClass(),"红包账户信息:"+redAccountEntity.getCustId()+":"+redAccountEntity.getAccountNo()+":"+redAccountEntity.getAmount()+":"+redAccountEntity.getCustName()+":"+redAccountEntity.getAccountType());
                if(redAccountEntity==null){//如果运营红包中的金额都比红包金额小，则从冠群红包账户 custId=4 账户中出钱
                    fromEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_PRIMARY);//冠群红包账户 custId=4
                }else{
                    fromEntity=redAccountEntity;
                }
            }else{
                fromEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_PRIMARY);//冠群红包账户 custId=4
            }
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, bonusAmount, 2, "", "",-1l,null,null,bid.getCustomerId().longValue(),bid.getContractNo()));
        }
        fuiouFtpColomFieldService.insertList(fuiouFtpColomFields);
        fuiouFtpOrderService.addOrder(fundOrderEntity, 1);
        paySuperByFuiou.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
        loanEntity.setStatus("10050008");
        loanEntity.setModifyTime(new Date());
        loanEntity.setOrderNo(fundOrderEntity.getOrderNo());
        fssLoanService.update(loanEntity);
    }


    public void settleCallback(FundOrderEntity fundOrderEntity) throws FssException {

//        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
        if (fundOrderEntity.getOrderState() != 6 && fundOrderEntity.getOrderState() != 1001) {
            return;
        }

        FssLoanEntity loanEntity = fssLoanService.getFssLoanEntityById(fundOrderEntity.getOrderFrormId());
        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11090004".equals(loanEntity.getTradeType())||"11090006".equals(loanEntity.getTradeType())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }

        String contractId = loanEntity.getContractId();
        Bid bid = null;
        List<Tender> list  = null;
        String title = "cc";
        try {
            bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
            list = fetchDataService.featchData(Tender.class,"tenderList",paramMap);
            //产品名称，如果产品名称为空，则去标的title
            title  = UrlConnectUtil.sendDataReturnString("findProductName",paramMap);

        } catch (FssException e) {
            LogUtil.error(getClass(),e);
            return;
        }

        //抵押标抵押权人判断   todo
        Integer cusId = bid.getCustomerId();
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }

        FundAccountEntity toEntity = fundAccountService.getFundAccount(Long.valueOf(cusId), GlobalConstants.ACCOUNT_TYPE_LOAN);
        try {
            fundOrderService.updateOrder(fundOrderEntity, 1001, "0000", "第三方已完成，本地账务流水处理");
        } catch (FssException e) {
            LogUtil.error(this.getClass(), e);
        }
        fundSequenceService.selletSequence(list,toEntity,fundOrderEntity,title,bid);
        //修改订单信息
        fundOrderService.updateOrder(fundOrderEntity, 2, "0000", "订单完成");

        //回盘处理 如果冠e通满标\借款 抵押权人提现 直接回盘,借款信用标满标,修改状态  todo

        if(!("11090002".equals(loanEntity.getTradeType())) && !("11090004".equals(loanEntity.getTradeType()))) {
            fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(),loanEntity.getMchnChild(),loanEntity.getTradeType());
        }
        loanEntity.setStatus("10050009");
        loanEntity.setModifyTime(new Date());
        fssLoanService.update(loanEntity);
    }

//
//    public void newSettleCalback(String  orderNo,String newOrderNo,String tenderId) throws FssException {
//        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
//        FssLoanEntity loanEntity = fssLoanService.getFssLoanEntityById(fundOrderEntity.getOrderFrormId());
//
//        String type = "1";
//        if("11090004".equals(loanEntity.getTradeType())||"11090006".equals(loanEntity.getTradeType())){
//            // paramMap.put("type","2");
//            type = "2";
//        }
//        String contractId = loanEntity.getContractId();
//        super.initTender(contractId,type);
//
//        Bid bid = super.getBid(contractId);
////        List<Tender> list = super.getTenderList(contractId);
//
//        //抵押标抵押权人判断
//        Integer cusId = bid.getCustomerId();
//        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
//            cusId = bid.getHypothecarius();
//        }
//
//        FundAccountEntity toEntity = fundAccountService.getFundAccount(cusId.longValue(), GlobalConstants.ACCOUNT_TYPE_LOAN);
//
//        Tender tender  = super.getTender(contractId,tenderId);
//        String title = super.getTitle(contractId);
//
//        fundSequenceService.selletSequence(tender,toEntity,bid,orderNo,newOrderNo,title);
//    }
//
//    public void newSettleBonusCalback(FundAccountEntity fromAccount,String  orderNo,String newOrderNo,BigDecimal amount) throws FssException {
//        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
//        FssLoanEntity loanEntity = fssLoanService.getFssLoanEntityById(fundOrderEntity.getOrderFrormId());
//
//        String type = "1";
//        if("11090004".equals(loanEntity.getTradeType())||"11090006".equals(loanEntity.getTradeType())){
//            // paramMap.put("type","2");
//            type = "2";
//        }
//        String contractId = loanEntity.getContractId();
//        super.initTender(contractId,type);
//
//        Bid bid = super.getBid(contractId);
////        List<Tender> list = super.getTenderList(contractId);
//
//        //抵押标抵押权人判断
//        Integer cusId = bid.getCustomerId();
//        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
//            cusId = bid.getHypothecarius();
//        }
//
//        FundAccountEntity toEntity = fundAccountService.getFundAccount(cusId.longValue(), GlobalConstants.ACCOUNT_TYPE_LOAN);
//
//        String title = super.getTitle(contractId);
//
//        fundSequenceService.transfer(fromAccount,toEntity,6,2006,amount,null,orderNo,newOrderNo,"1105",null,null,null,null,bid.getCustomerId().longValue(),bid.getContractNo());
//
//    }
//
//    public void newSettleComplete(String orderNo) throws FssException {
//        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
//        if (fundOrderEntity.getOrderState() != 6 && fundOrderEntity.getOrderState() != 1001) {
//            return;
//        }
//
//        FssLoanEntity loanEntity = fssLoanService.getFssLoanEntityById(fundOrderEntity.getOrderFrormId());
//
//        //修改订单信息
//        fundOrderService.updateOrder(fundOrderEntity, 2, "0000", "订单完成");
//
//        //回盘处理 如果冠e通满标\借款 抵押权人提现 直接回盘,借款信用标满标,修改状态  todo
//
//        if(!("11090002".equals(loanEntity.getTradeType())) && !("11090004".equals(loanEntity.getTradeType()))) {
//            fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(),loanEntity.getMchnChild(),loanEntity.getTradeType());
//        }
//        loanEntity.setStatus("10050009");
//        loanEntity.setModifyTime(new Date());
//        fssLoanService.update(loanEntity);
//
//    }


}
