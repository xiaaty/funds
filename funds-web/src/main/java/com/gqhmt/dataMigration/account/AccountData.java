package com.gqhmt.dataMigration.account;

import com.gqhmt.business.architect.invest.service.InvestmentService;
import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.TyzfTradeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    private CustomerInfoService customerInfoService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private InvestmentService investmentService;

    @Resource
    private TyzfTradeService tyzfTradeService;

    @Resource
    private BidService bidService;

    public void accountData(String createDate) throws FssException {
        //获取客户信息
        List<CustomerInfoEntity> customerInfoBeanList = customerInfoService.queryCustomerInfoByDate(createDate);
        for(CustomerInfoEntity customerInfoEntity : customerInfoBeanList){
            //处理对公账户
            if(customerInfoEntity.getId()<99){
                this.createBusiAccount(customerInfoEntity);
                continue;
            }

            FundAccountEntity priEntity  = fundAccountService.getFundAccount(customerInfoEntity.getId(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
            if(priEntity == null ){
                continue;
            }

            if(priEntity.getHasThirdAccount() == 2){
                //List<FundAccountEntity> fundAccountEntities = fundAccountService.getFundsAccountsByCustId(customerInfoEntity.getId());
                this.createInternetAccount(customerInfoEntity);  //开通互联网账户

                //验证是否存在线下出借合同，如有，开通线下出借账户
                int count = investmentService.queryByCustId(customerInfoEntity.getId().intValue());
                if(count>0) {
                    this.createInvestmentAccount(customerInfoEntity);  //开通线下出借账户
                }
                //验证是否存在借款合同
                int res = bidService.queryBidByCustId(customerInfoEntity.getId().intValue());
                if ( res>0) {
                    this.createLoanAccount(customerInfoEntity);// 开通借款人信贷账户
                }
            }

        }

        //处理标的账户
        List<Bid> bids = bidService.queryBidByDate(createDate);

        for(Bid bid : bids){
            CustomerInfoEntity customerInfoEntity = customerInfoService.getCustomerById(bid.getCustomerId().longValue());
            this.createLoanBidAccount(bid.getId().longValue(),bid.getContractNo(),customerInfoEntity);
        }


    }

    /**
     * 开通互联网账户
     * @param customerInfoEntity
     */
    public void createInternetAccount(CustomerInfoEntity customerInfoEntity) throws FssException{
        String seq_no=this.createSeqNo();
        tyzfTradeService.createInternetAccount("11029100",customerInfoEntity.getId(),customerInfoEntity.getCustomerName(),GlobalConstants.TYZF_PERSONCUST,customerInfoEntity.getCertNo(),customerInfoEntity.getCertType().toString(),seq_no,customerInfoEntity.getMobilePhone());
    }


    /**
     * 开通线下出借账户
     * @param customerInfoEntity
     */
    public void createInvestmentAccount(CustomerInfoEntity customerInfoEntity) throws FssException{
        String seq_no=this.createSeqNo();
        tyzfTradeService.createInvstmentAccount("11020006",customerInfoEntity.getId(), customerInfoEntity.getCustomerName(),GlobalConstants.TYZF_PERSONCUST,customerInfoEntity.getCertNo(),customerInfoEntity.getCertType().toString(), null, seq_no, customerInfoEntity.getMobilePhone());
    }

    /**
     * 开通借款人信贷账户
     * @param customerInfoEntity
     */
    public void createLoanAccount(CustomerInfoEntity customerInfoEntity) throws FssException{
        String seq_no=this.createSeqNo();
        tyzfTradeService.createLoanAccount("11020007",customerInfoEntity.getId(), customerInfoEntity.getCustomerName(),GlobalConstants.TYZF_PERSONCUST,customerInfoEntity.getCertNo(),customerInfoEntity.getCertType().toString(), null, seq_no, customerInfoEntity.getMobilePhone());
    }

    /**
     * 开通借款人标的账户
     * @param customerInfoEntity
     */
    public void createLoanBidAccount(Long bid_id,String contract_no,CustomerInfoEntity customerInfoEntity) throws FssException{
        String seq_no=this.createSeqNo();
        tyzfTradeService.createBidAcocunt("11020019",customerInfoEntity.getId(), customerInfoEntity.getCustomerName(),customerInfoEntity.getCertNo(),customerInfoEntity.getCertType().toString(), contract_no, seq_no, customerInfoEntity.getMobilePhone(),bid_id);
    }

    /**
     * 创建对公账户
      * @param customerInfoEntity
     * @throws FssException
     */
    public void createBusiAccount(CustomerInfoEntity customerInfoEntity) throws FssException{
       String seq_no=this.createSeqNo();
       tyzfTradeService.createBusiAccount("11020022",customerInfoEntity.getId(),customerInfoEntity.getCustomerName(),customerInfoEntity.getCertNo()+customerInfoEntity.getId(),customerInfoEntity.getCertType().toString(),seq_no, customerInfoEntity.getMobilePhone());
    }

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
