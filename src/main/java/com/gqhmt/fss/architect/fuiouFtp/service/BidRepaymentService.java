package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.business.architect.loan.bean.RepaymentBean;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.core.FssException;
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
import com.gqhmt.pay.service.PaySuperByFuiou;
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
public class BidRepaymentService {


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

    public void BidRepayment(FssLoanEntity loanEntity) throws FssException {


        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11090004".equals(loanEntity.getTradeType())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }
        Map<String,String > repParamMap = new HashMap<>();
        repParamMap.put("id",loanEntity.getBusiNo());
        Bid bid = null;
        List<RepaymentBean> list  = null;
        try {
            bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
            list =fetchDataService.featchData(RepaymentBean.class,"revicePayment",repParamMap);
        } catch (FssException e) {
            LogUtil.error(getClass(),e);
            LogUtil.error(getClass(),e);
            loanEntity.setStatus("10050014");
            loanEntity.setModifyTime(new Date());
            fssLoanService.update(loanEntity);
            fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(),loanEntity.getMchnChild(),loanEntity.getTradeType());

            return;
        }


        Integer cusId = bid.getCustomerId();
        //抵押标, 处理custId为抵押权人id
        if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }
        //还款总额获取
        BigDecimal sumRepay  = BigDecimal.ZERO;

        //账户资金余额验证   todo

        //利差补偿  todo

        //抵押标 还款资金转账


        // 实际出账账户
        FundAccountEntity fromEntity = fundAccountService.getFundAccount(cusId.longValue(), GlobalConstants.ACCOUNT_TYPE_LOAN);


        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(fromEntity, sumRepay, GlobalConstants.ORDER_REPAYMENT_NEW, loanEntity.getId(), GlobalConstants.BUSINESS_REPAYMENT,"2");
        for (RepaymentBean bean : list) {
            FundAccountEntity toEntity = fundAccountService.getFundAccount((long)bean.getCustomerId(), bean.getInvestType());
            if (bean.getRepaymentAmount().multiply(new BigDecimal("100")).longValue() <= 0) {
                continue;
            }
//            super.fundsRecordService.add(fromEntity, toEntity, fundOrderEntity, bid.getId().longValue(), null, 2, "产品" + title + "，还款本金" + bean.getRepaymentPrincipal() + "元，还款利息" + bean.getRepaymentInterest() + "元,合计：" + bean.getRepaymentAmount() + "元");
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, bean.getRepaymentAmount(), 3, "", ""));
        }

        fuiouFtpColomFieldService.saveOrUpdateAll(fuiouFtpColomFields);
        fuiouFtpOrderService.addOrder(fundOrderEntity, 2);
        loanEntity.setStatus("10050012");
        loanEntity.setModifyTime(new Date());
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
        if("11090004".equals(loanEntity.getTradeType())){
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
            title  = fetchDataService.featchDataSingle(String.class,"findProductName",paramMap);
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
        /*if (bid.getIsHypothecarius() != null && bid.getIsHypothecarius() == 1 && bid.getHypothecarius() > 0) {
            cusId = bid.getHypothecarius();
        }*/
        // 批量冻结
        FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(cusId), GlobalConstants.ACCOUNT_TYPE_LOAN);
        this.fundSequenceService.repaymentSequence(list,title,fromEntity,fundOrderEntity,sumRepay);


        //回盘处理
        fssBackplateService.createFssBackplateEntity(loanEntity.getSeqNo(),loanEntity.getMchnChild(),loanEntity.getTradeType());
        loanEntity.setStatus("10050013");
        loanEntity.setModifyTime(new Date());
        fssLoanService.update(loanEntity);

    }


}
