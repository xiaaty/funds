package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.fetchService.FetchDataService;
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
public class BidSettleService {

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


    public void settle(FssLoanEntity loanEntity) throws FssException {

        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11090004".equals(loanEntity.getTradeType())||"11090006".equals(loanEntity.getTradeType())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }

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
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, tender.getRealAmount(), 2, "", map.get(tender.getId()),tender.getId()));
            if (tender.getBonusAmount() != null) {
                bonusAmount = bonusAmount.add(tender.getBonusAmount());
            }
        }
        if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
            FundAccountEntity fromEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_FREEZE);
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, bonusAmount, 2, "", null,null));
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
           throw  e;
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
        fundSequenceService.selletSequence(list,toEntity,fundOrderEntity,title);
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
}
