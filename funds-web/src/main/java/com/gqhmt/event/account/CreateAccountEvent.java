package com.gqhmt.event.account;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.customer.entity.FssCustBankCardEntity;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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


    public FssAccountEntity createAccount(String tradeType,String  name,String  mobile,String certNo,Long orgcustId,String mchn,String bankType,String bankNo,String area,String busiNo,Date createTime) throws FssException {
        FssCustomerEntity fssCustomerEntity = fssCustomerService.getFssCustomerEntityByCertNo(certNo);
        if(area.length()==6){
            area= Application.getInstance().getFourCode(area);
        }
        if(bankType.length()==3){
            bankType="0"+bankType;
        }
        Long custId = orgcustId;

        if(fssCustomerEntity == null){
            //首次开户,验证银行卡信息
            if(bankType == null || bankNo == null || area == null){
                throw new FssException("90002031");
            }
            //验证手机号是否存在
        }


        //判断是否走旧版账户，11020011 为纯线下开户，不需在冠e通开户
        boolean isOldAccount = true;
        if("11020011".equals(tradeType)){
            isOldAccount = false;
        }
        FundAccountEntity primaryAccount = null;
        CustomerInfoEntity customerInfoEntity = null;
        //生成旧版账户
        if(isOldAccount){
            Integer userId = null;
            try {
                if(orgcustId == null){//此处不校验冠e通是否存在此客户，只要id不为空，就默认存在。
                    //临时设置为查询冠e通客户表，后期需要改为冠e通提供接口，调用接口后，如果管e通不存在，则冠e通开户，并返回客户id
                    customerInfoEntity= customerInfoService.searchCustomerInfoByCertNo(certNo);//旧版客户信息
                    if(customerInfoEntity == null){
                        customerInfoEntity=customerInfoService.createCustomer(certNo,name,mobile);
                    }
                }else{
                    //获取冠e通客户信息，用生成冠e通旧版账户体系，后期账户体系全部移到新版后，则不再提供此功能
                    customerInfoEntity =  customerInfoService.getCustomerById(custId);
                }
                custId = customerInfoEntity.getId();
                userId = customerInfoEntity.getUserId();
                //设置值
                customerInfoEntity.setParentBankCode(bankType);
                customerInfoEntity.setBankNo(bankNo);
                customerInfoEntity.setCityCode(area);
                customerInfoEntity.setCertNo(certNo);
                customerInfoEntity.setMobilePhone(mobile);
                customerInfoEntity.setCustomerName(name);
                //生成旧版账户
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
        }
        FssAccountEntity fssAccountEntity = null;
        try {
            //生成新版客户信息记录
            if (fssCustomerEntity == null){
                fssCustomerEntity = fssCustomerService.createFssCustomerEntity(name,mobile,certNo,custId,mchn);//生成客户信息
            }else{
                if(fssCustomerEntity.getCustId()  == null){
                    fssCustomerService.updateCustId(fssCustomerEntity,custId);
                }
                //校验客户姓名和手机号，如果不同，需要修改，客户信息添加锁定字段，如果实名验证完成，则不允许修改客户姓名。手机号允许修改
                //更新新版账户的客户名及其他信息
                fssCustomerEntity.setCertNo(customerInfoEntity.getCertNo());
                fssCustomerEntity.setName(customerInfoEntity.getCustomerName());
                fssCustomerEntity.setMobile(customerInfoEntity.getMobilePhone());
                fssCustomerEntity.setModifyTime(new Date());
                fssCustomerService.updateCustId(fssCustomerEntity,customerInfoEntity.getId());
            }
            //生成富有账户
            if(isOldAccount){
                if (primaryAccount.getHasThirdAccount() ==1){//富友
                    primaryAccount.setCustomerInfoEntity(customerInfoEntity);
                    FundOrderEntity fundOrderEntity=paySuperByFuiou.createAccountByPersonal(primaryAccount,"","",tradeType);
                    primaryAccount.setHasThirdAccount(2);
                    //富友开户成功,更新开户时间和开户订单号
                    primaryAccount.setAccountOrderNo(fundOrderEntity!=null ? fundOrderEntity.getOrderNo():null);
                    primaryAccount.setAccountTime(fundOrderEntity!=null ? fundOrderEntity.getCreateTime():null);
                    fundAccountService.update(primaryAccount);
                    fssAccountService.createFuiouAccount(mchn,fssCustomerEntity,bankNo);
                }
                //跟新所有与该cust_id相同的账户名称
                fundAccountService.updateAccountCustomerName(custId,customerInfoEntity.getCustomerName(),customerInfoEntity.getCityCode(),customerInfoEntity.getParentBankCode(),customerInfoEntity.getBankNo());
            }
            //生成新版账户
            fssAccountEntity = fssAccountService.createNewFssAccountEntity(fssCustomerEntity,tradeType,busiNo,mchn,null,createTime);
        } catch (FssException e) {
            if(!e.getMessage().contains("busi_no_uk")) {
                throw new FssException(e.getMessage(), e);
            }
        }

        //银行卡信息生成
        //旧版银行卡信息生成
        //创建银行卡信息
//       List<BankCardInfoEntity> listbankcard=bankCardInfoService.getBankCardByCustId(custId.intValue());
        if(isOldAccount) {
            BankCardInfoEntity bankCardInfoEntity = bankCardInfoService.getBankCardByCustNo(custId);
            if (bankCardInfoEntity == null) {
                bankCardInfoEntity = bankCardInfoService.createBankCardInfo(customerInfoEntity, tradeType);
            }
            fssAccountEntity.setBankId(bankCardInfoEntity.getId().longValue());
            //判断是否为借款系统开户，如果是借款系统开户，则更新customerInfo中的bankId;
            if(orgcustId==null){
                customerInfoEntity.setBankId(bankCardInfoEntity.getId());
                customerInfoService.update(customerInfoEntity);
            }
        }
        //新版银行卡信息生成  增加判断，是否存在
        FssCustBankCardEntity fssCustBankCardEntity = fssCustBankCardService.getFssCustBankCardByCustNo(fssCustomerEntity.getCustNo());
        if(fssCustBankCardEntity==null){
            fssCustBankCardEntity = fssCustBankCardService.createFssBankCardEntity(bankType,bankNo,area,mchn,fssCustomerEntity);
        }
        return  fssAccountEntity;
    }


}