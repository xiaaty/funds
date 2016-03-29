package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.fetchService.FetchDataService;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.service.FuiouPreauthService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class SettleService {

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


    public void settle(FssLoanEntity loanEntity) throws FssException {

        Map<String,String > paramMap = new HashMap<>();
        paramMap.put("id",loanEntity.getContractId());
        if("11090004".equals(loanEntity.getTradeType())){
            paramMap.put("type","2");
        }else{
            paramMap.put("type","1");
        }
        Bid bid = fetchDataService.featchDataSingle(Bid.class,"findBid",paramMap);
       // List<FundOrderEntity> listFundOrder = fundOrderService.queryFundOrder(GlobalConstants.ORDER_SETTLE, GlobalConstants.BUSINESS_SETTLE, bid);


        FssAccountEntity fssAccountEntity = null;
        Integer cusId = bid.getCustomerId();


        //产品名称，如果产品名称为空，则去标的title
        String title  = fetchDataService.featchDataSingle(String.class,"findProductName",paramMap);

        List<Tender> list  = null;
        try {
            list = fetchDataService.featchData(Tender.class,"tenderList",paramMap);
        } catch (FssException e) {
            e.printStackTrace();
        }
        FundAccountEntity toEntity = fundAccountService.getFundAccount(cusId.longValue(), GlobalConstants.ACCOUNT_TYPE_LOAN);

        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(toEntity, loanEntity.getPayAmt(), GlobalConstants.ORDER_SETTLE_NEW, loanEntity.getId(), GlobalConstants.BUSINESS_SETTLE, "2");

        Map<Integer, String> map = fuiouPreauthService.getContractNo(bid.getId().longValue());
        BigDecimal bonusAmount = BigDecimal.ZERO;

        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
        for (Tender tender : list) {
            FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(tender.getCustomerId()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, tender.getRealAmount(), 2, "", map.get(tender.getId())));
            if (tender.getBonusAmount() != null) {
                bonusAmount = bonusAmount.add(tender.getBonusAmount());
            }
        }
        if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
            FundAccountEntity fromEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_FREEZE);
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, bonusAmount, 2, "", null));
        }
        fuiouFtpColomFieldService.saveOrUpdateAll(fuiouFtpColomFields);
        fuiouFtpOrderService.addOrder(fundOrderEntity, 1);
        paySuperByFuiou.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
        loanEntity.setStatus("10050008");
    }


    public void settleCallback(String orderNo){


    }
}
