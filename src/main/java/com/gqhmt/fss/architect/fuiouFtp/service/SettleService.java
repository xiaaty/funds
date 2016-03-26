package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.fetchService.FetchDataService;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
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
    private FundOrderService fundOrderService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;


    public void settle(FssLoanEntity loanEntity) throws FssException {
        Integer bid = fetchDataService.featchDataSingle(Integer.class,"tenderList","offlineId",loanEntity.getContractId());
       // List<FundOrderEntity> listFundOrder = fundOrderService.queryFundOrder(GlobalConstants.ORDER_SETTLE, GlobalConstants.BUSINESS_SETTLE, bid);


        FssAccountEntity fssAccountEntity = null;
        Long cusId = fssAccountEntity.getCustId();


        //产品名称，如果产品名称为空，则去标的title
//        String title  = bidService.getProductName(bid.getId());
        Map<String,String > map = new HashMap<>();
        map.put("id","46");
        map.put("type","1");
        List<Tender> list  = null;
        try {
            list = fetchDataService.featchData(Tender.class,"tenderList",map);
        } catch (FssException e) {
            e.printStackTrace();
        }
        FundAccountEntity toEntity = fundAccountService.getFundAccount(cusId, GlobalConstants.ACCOUNT_TYPE_LOAN);

        FundOrderEntity fundOrderEntity = paySuperByFuiou.createOrder(toEntity, loanEntity.getPayAmt(), GlobalConstants.ORDER_SETTLE, bid, GlobalConstants.BUSINESS_SETTLE, "2");

//        Map<Integer, String> map = fuiouPreauthService.getContractNo(bid.getId().longValue());
        BigDecimal bonusAmount = BigDecimal.ZERO;

        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
        for (Tender tender : list) {
            FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(tender.getCustomerId()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
            fuiouFtpColomFields.add(fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, tender.getRealAmount(), 2, "", map.get(tender.getId())));
            if (tender.getBonusAmount() != null) {
                bonusAmount = bonusAmount.add(tender.getBonusAmount());
            }
        }
        fuiouFtpColomFieldService.saveOrUpdateAll(fuiouFtpColomFields);

        if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
            FundAccountEntity fromEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_FREEZE);
            fuiouFtpColomFieldService.addColomFieldByNotInsert(fromEntity, toEntity, fundOrderEntity, bonusAmount, 2, "", null);
        }
        fuiouFtpOrderService.addOrder(fundOrderEntity, 1);
        paySuperByFuiou.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
        throw new FssException("异步处理，等待回调通知");


    }
}
