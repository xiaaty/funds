package com.gqhmt.event.account;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.customer.service.FssCustBankCardService;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TyzfTradeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Filename:    com.gqhmt.event.account.CreateAccountEvent
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/13 22:20
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/13  于泳      1.0     1.0 Version
 */
@Service
public class CreateAccountEvent {


    @Resource
    private FssCustomerService fssCustomerService;

    @Resource
    private FssAccountService fssAccountService;

    @Resource
    private FssCustBankCardService fssCustBankCardService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    //旧版客户信息表，临时调用
    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private BankCardInfoService bankCardInfoService;

    @Resource
    private TyzfTradeService tyzfTradeService;

    /**
     * 开户接口
     * @param tradeType
     * @param name
     * @param mobile
     * @param certNo
     * @param custId
     * @param mchn
     * @param bankType
     * @param bankNo
     * @param area
     * @param busiNo
     * @param createTime
     * @return
     * @throws FssException
     */
    public Integer createAccount(String tradeType,String name,String mobile,String certNo,Long custId,String mchn,String bankType,String bankNo,String area,String busiNo,Date createTime,String seq_no) throws FssException {
        CustomerInfoEntity customerInfoEntity = null;
        if(area.length()==6){
            area= Application.getInstance().getFourCode(area);
        }
        if(bankType.length()==3){
            bankType="0"+bankType;
        }
        FundAccountEntity primaryAccount = null;
            Integer userId = null;
            try {
                customerInfoEntity = customerInfoService.getCustomerById(custId);//旧版客户信息
                if(customerInfoEntity == null){
                    customerInfoEntity=customerInfoService.createCustomer(certNo,name,mobile);
                }
                if(customerInfoEntity==null) throw  new FssException("90002007");
                custId = customerInfoEntity.getId();
                userId = customerInfoEntity.getUserId();
                //设置值
                customerInfoEntity.setParentBankCode(bankType);
                customerInfoEntity.setBankNo(bankNo);
                customerInfoEntity.setCityCode(area);
                customerInfoEntity.setCertNo(certNo);
                customerInfoEntity.setCertType(1);
                customerInfoEntity.setMobilePhone(mobile);
                customerInfoEntity.setCustomerName(name);
                customerInfoService.update(customerInfoEntity);
                primaryAccount = fundAccountService.getFundAccount(custId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
                if(primaryAccount == null){
                    primaryAccount = fundAccountService.createAccount(customerInfoEntity, userId);
                }
            } catch (FssException e) {
                if(e.getMessage() != null && "90002003".equals(e.getMessage()) ) {
                    primaryAccount = fundAccountService.createAccount(customerInfoEntity, userId);
                }else{
                    throw e;
                }
            }
        try {
                if (primaryAccount.getHasThirdAccount() ==1){//生成富有账户
                    primaryAccount.setCustomerInfoEntity(customerInfoEntity);
                    FundOrderEntity fundOrderEntity=paySuperByFuiou.createAccountByPersonal(primaryAccount,"","",tradeType);
                    primaryAccount.setHasThirdAccount(2);
                    //富友开户成功,更新开户时间和开户订单号
                    primaryAccount.setAccountOrderNo(fundOrderEntity!=null ? fundOrderEntity.getOrderNo():null);
                    primaryAccount.setAccountTime(fundOrderEntity!=null ? fundOrderEntity.getCreateTime():null);
                    fundAccountService.update(primaryAccount);
                }
                //更新新所有与该cust_id相同的账户名称
                fundAccountService.updateAccountCustomerName(custId,customerInfoEntity.getCustomerName(),customerInfoEntity.getCityCode(),customerInfoEntity.getParentBankCode(),customerInfoEntity.getBankNo());
        } catch (FssException e) {
            if(!e.getMessage().contains("busi_no_uk")) {
                throw new FssException(e.getMessage(), e);
            }
        }
        //创建银行卡信息
        BankCardInfoEntity bankCardInfoEntity=null;
        List<BankCardInfoEntity> bankCardInfoList = bankCardInfoService.findBankCardByCustNo(custId.toString());
        if(CollectionUtils.isEmpty(bankCardInfoList)){
            bankCardInfoEntity = bankCardInfoService.createBankCardInfo(customerInfoEntity, tradeType);
        }else{
            bankCardInfoEntity=bankCardInfoList.get(0);
        }
        customerInfoEntity.setBankId(bankCardInfoEntity.getId());
        customerInfoService.update(customerInfoEntity);
        //调用统一支付开户
        tyzfTradeService.createTyzfAccount(tradeType,customerInfoEntity.getId(),customerInfoEntity.getCustomerName(),certNo,String.valueOf(customerInfoEntity.getCertType()),busiNo,seq_no,customerInfoEntity.getMobilePhone());
        return bankCardInfoEntity.getId();
    }

    /**
     * 实名认证开户
     * @param trade_type
     * @param cert_no
     * @param cust_name
     * @param seq_no
     * @throws FssException
     */
    public void createVerifiedAccount(String trade_type,String cust_no,String cust_name,String cert_no,String mobile_phone,String busi_no,String seq_no) throws FssException {
        tyzfTradeService.createTyzfAccount(trade_type,Long.valueOf(cust_no),cust_name,cert_no,"01",busi_no,seq_no,mobile_phone);
    }

    /**
     * 标的开户
     * @param trade_type
     * @param cust_no
     * @param cust_name
     * @param cert_no
     * @param mobile_phone
     * @param contract_no
     * @param seq_no
     * @throws FssException
     */
    public void createBidAcocunt(String trade_type,String cust_no,String cust_name,String cert_no,String mobile_phone,String contract_no,String seq_no,String bid_id) throws FssException {
        tyzfTradeService.createBidAcocunt(trade_type,Long.valueOf(cust_no),cust_name,cert_no,"1",contract_no,seq_no,mobile_phone,Long.valueOf(bid_id));
    }

    /**
     * 账户注销
     * @param trade_type
     * @param cust_no
     * @param cust_name
     * @param cert_no
     * @param mobile_phone
     * @param seq_no
     * @throws FssException
     */
    public void LogOutAccount(String trade_type,String cust_no,String cust_name,String cert_no,String mobile_phone,String seq_no) throws FssException {
        tyzfTradeService.logOutAccount(trade_type,cust_no,cust_name,cert_no,seq_no,mobile_phone);
    }


}
