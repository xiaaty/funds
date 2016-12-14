package com.gqhmt.dataMigration.account;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.entity.FssAccountBindHisEntity;
import com.gqhmt.fss.architect.account.service.FssAccountBindService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.TyzfTradeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Filename:    com.gqhmt.dataMigration.account.AccountData
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/7 15:43
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/7  于泳      1.0     1.0 Version
 */
@Component
public class AccountData {

    @Resource
    private FssAccountBindService fssAccountBindService ;

    @Resource
    private CustomerInfoService customerInfoService;


    @Resource
    private TyzfTradeService tyzfTradeService;

    @Resource
    private BidService bidService;

    public void accountData() throws FssException {
        //获取客户信息
        List<FssAccountBindHisEntity> fssAccountBindEntityList = fssAccountBindService.queryBindAccountLImit();
        for(FssAccountBindHisEntity fssAccountBindHisEntity : fssAccountBindEntityList) {



            if("0".equals(fssAccountBindHisEntity.getStatus())){
                Long custId = fssAccountBindHisEntity.getBusiId();
                int busiType = fssAccountBindHisEntity.getBusiType();
                if (busiType == 90) {
                    Bid bid = bidService.findById(fssAccountBindHisEntity.getBusiId().intValue());
                    custId = bid.getCustomerId().longValue();
                }
                CustomerInfoEntity customerInfoEntity = customerInfoService.getCustomerById(custId);

                switch (busiType){
                    case 1:
                        this.createLoanAccount(customerInfoEntity);
                        break;
                    case 2:
                        this.createInvestmentAccount(customerInfoEntity);
                        break;
                    case 3:
                        this.createInternetAccount(customerInfoEntity);
                        break;
                    case 90:
                        this.createLoanBidAccount(fssAccountBindHisEntity.getBusiId(),fssAccountBindHisEntity.getContractNo(),customerInfoEntity);
                        break;
                    case 96:
                        this.createInternetAccount(customerInfoEntity);
                        break;
                }
                fssAccountBindHisEntity.setStatus("2");
                fssAccountBindService.updateBindHis(fssAccountBindHisEntity);
            }else if("1".equals(fssAccountBindHisEntity.getStatus())){
                FssAccountBindEntity fssAccountBindEntity = fssAccountBindService.getBindAccountByParam(fssAccountBindHisEntity.getBusiId(),fssAccountBindHisEntity.getBusiType());

                if(fssAccountBindEntity != null && "1".equals(fssAccountBindEntity.getStatus())){
                    if(fssAccountBindEntity.getBusiType() == 90){
                        fssAccountBindHisEntity.setAccNo(fssAccountBindEntity.getAccNo());
                        fssAccountBindHisEntity.setStatus("4");
                        fssAccountBindService.updateBindHis(fssAccountBindHisEntity);
                    }else{
                        fssAccountBindHisEntity.setStatus("2");
                        fssAccountBindHisEntity.setAccNo(fssAccountBindEntity.getAccNo());
                        fssAccountBindService.updateBindHis(fssAccountBindHisEntity);
                    }
                }
            }else if("2".equals(fssAccountBindHisEntity.getStatus())){
                if(fssAccountBindHisEntity.getBalanceAmount().compareTo(BigDecimal.ZERO)>0){
                    tyzfTradeService.tyzfRecharge(fssAccountBindHisEntity.getBusiId(),fssAccountBindHisEntity.getBusiType(),
                            fssAccountBindHisEntity.getBalanceAmount().add(fssAccountBindHisEntity.getFreezeAmount()),"1001","11039999",createSeqNo());
                }
                fssAccountBindHisEntity.setStatus("3");
                fssAccountBindService.updateBindHis(fssAccountBindHisEntity);
            }else if("3".equals(fssAccountBindHisEntity.getStatus())){
                if(fssAccountBindHisEntity.getFreezeAmount() != null && fssAccountBindHisEntity.getFreezeAmount().compareTo(BigDecimal.ZERO)>0) {
                    tyzfTradeService.tyzfRecharge(fssAccountBindHisEntity.getBusiId(), fssAccountBindHisEntity.getBusiType(),
                            fssAccountBindHisEntity.getFreezeAmount(), "1001", "11039998", createSeqNo());
                }
                fssAccountBindHisEntity.setStatus("4");
                fssAccountBindService.updateBindHis(fssAccountBindHisEntity);
            }



        }




    }

    /**
     * 开通互联网账户
     * @param customerInfoEntity
     */
    public void createInternetAccount(CustomerInfoEntity customerInfoEntity) throws FssException{
        String seq_no=this.createSeqNo();
        tyzfTradeService.createInternetAccount("11029100",customerInfoEntity.getId(),customerInfoEntity.getCustomerName(),GlobalConstants.TYZF_PERSONCUST,customerInfoEntity.getCertNo(),String.valueOf(customerInfoEntity.getCertType()),seq_no,customerInfoEntity.getMobilePhone());
    }


    /**
     * 开通线下出借账户
     * @param customerInfoEntity
     */
    public void createInvestmentAccount(CustomerInfoEntity customerInfoEntity) throws FssException{
        String seq_no=this.createSeqNo();
        tyzfTradeService.createInvstmentAccount("11020006",customerInfoEntity.getId(), customerInfoEntity.getCustomerName(),GlobalConstants.TYZF_PERSONCUST,customerInfoEntity.getCertNo(),String.valueOf(customerInfoEntity.getCertType()), null, seq_no, customerInfoEntity.getMobilePhone());
    }

    /**
     * 开通借款人信贷账户
     * @param customerInfoEntity
     */
    public void createLoanAccount(CustomerInfoEntity customerInfoEntity) throws FssException{
        String seq_no=this.createSeqNo();
        tyzfTradeService.createLoanAccount("11020007",customerInfoEntity.getId(), customerInfoEntity.getCustomerName(),GlobalConstants.TYZF_PERSONCUST,customerInfoEntity.getCertNo(),String.valueOf(customerInfoEntity.getCertType()), null, seq_no, customerInfoEntity.getMobilePhone());
    }

    /**
     * 开通借款人标的账户
     * @param customerInfoEntity
     */
    public void createLoanBidAccount(Long bid_id,String contract_no,CustomerInfoEntity customerInfoEntity) throws FssException{
        String seq_no=this.createSeqNo();
        tyzfTradeService.createBidAcocunt("11020019",customerInfoEntity.getId(), customerInfoEntity.getCustomerName(),customerInfoEntity.getCertNo(),customerInfoEntity.getCertType().toString(), contract_no, seq_no, customerInfoEntity.getMobilePhone(),bid_id);
    }

//    /**
//     * 创建对公账户
//      * @param customerInfoEntity
//     * @throws FssException
//     */
//    public void createBusiAccount(CustomerInfoEntity customerInfoEntity) throws FssException{
//       String seq_no=this.createSeqNo();
//       tyzfTradeService.createBusiAccount("11020022",customerInfoEntity.getId(),customerInfoEntity.getCustomerName(),customerInfoEntity.getCertNo()+customerInfoEntity.getId(),customerInfoEntity.getCertType().toString(),seq_no, customerInfoEntity.getMobilePhone());
//    }

    /**
     * 生成一个流水号
     * @return
     */
    public String createSeqNo(){
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String seq_no=CommonUtil.getRandomString(8);
        return date+seq_no;
    }




}
