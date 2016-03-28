package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.*;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.bean.FssFuiouAccountBean;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.entity.FssFuiouAccountEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper;
import com.gqhmt.fss.architect.account.mapper.read.FssFuiouAccountReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssAccountWriteMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssFuiouAccountWriteMapper;
import com.gqhmt.fss.architect.customer.entity.FssCustBankCardEntity;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustBankCardService;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.mapper.write.CustomerInfoWriteMapper;
import com.gqhmt.funds.architect.customer.mapper.write.GqUserWriteMapper;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.account.service.FssAccountService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:34
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
@Service
public class FssAccountService {

    @Resource
    private FssCustomerService fssCustomerService;
	@Resource
    private FssAccountReadMapper accountReadMapper;
    @Resource
    private FssAccountWriteMapper fssAccountWriteMapper;
    @Resource
    private FssFuiouAccountWriteMapper fssFuiouAccountWriteMapper;
    @Resource
    private FssFuiouAccountReadMapper fssFuiouAccountReadMapper;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
   	private CustomerInfoWriteMapper customerInfoWriteMapper;
    @Resource
	private GqUserWriteMapper gqUserWriteMapper;
    @Resource
	private FundAccountService fundAccountService;
    @Resource
    private FssCustBankCardService fssCustBankCardService;
    
    
    public List<FssAccountEntity> findCustomerAccountByParams(Map map){
        return this.accountReadMapper.findCustomerAccountByParams(map);
    }

    /**
     * 查询账户信息
     * @return
     * @throws FssException
     */
    public List<BussAndAccountBean> queryAccountList(BussAndAccountBean bussaccount,String accType)throws FssException {
    	List<BussAndAccountBean> bussaccountlist=null;
    	
    	Map map=new HashMap();
    	/*if(busiNo!=null && !"".equals(busiNo)){
    		if(busiNo==10000001){//客户账户（互联网账户，委托出借账户，借款账户）
    			accType="10010001,10010002,10010003";
    		}
    		if(busiNo==10000002){//中间人账户
    			accType="10010004,10010005,10010006,10010007";
    		}
    		if(busiNo==10011000){//公司账户
    			accType="10011000";
    		}
    	}*/
    	if(StringUtils.isNotEmptyString(bussaccount.getAccNo())){//业务编号
    		map.put("accNo",bussaccount.getAccNo());
    	}
    	if(StringUtils.isNotEmptyString(bussaccount.getCustName())){
    		map.put("custName",bussaccount.getCustName());
    	}
    	if(StringUtils.isNotEmptyString(bussaccount.getCertNo())){
    		map.put("certNo",bussaccount.getCertNo());
    	}
    	List list=new ArrayList();
 	   	if(StringUtils.isNotEmptyString(accType)){
 		   String str[]=accType.split(",");
 		   for (int i = 0; i < str.length; i++){
 			   list.add(str[i]);
 		   }
 	   }
	    if(list!=null && list.size()>0){
    		map.put("list", list);
    	}
	    bussaccountlist=this.accountReadMapper.getBussinessAccountList(map);
        return bussaccountlist;
    }

    /**
     * 创建本地账户信息
     * @param dto
     * @throws FssException
     */
    public FssCustomerEntity createAccount(CreateLoanAccountDto dto,Long userId) throws FssException {
    	FssCustomerEntity fssCustomerinfo=null;
    	
    	FssFuiouAccountBean fssFuiouAccountBean=fssFuiouAccountReadMapper.getAccountByCentNo(dto.getCert_no());
    	if(fssFuiouAccountBean==null){//不存在
			fssCustomerinfo = fssCustomerService.create(dto,String.valueOf(userId));//生成客户信息
	        FssCustBankCardEntity fssCustBankCardEntity=fssCustBankCardService.createFssBankCardInfo(dto,fssCustomerinfo);//生成银行卡信息
	        if(!dto.getTrade_type().equals("11020009")){ //线下开户不走富友
	        	//生成第三方开户账户信息,纯线下,次开户,不开,线上需要开户.
	        	this.createFuiouAccount(dto,fssCustomerinfo,fssCustBankCardEntity);
	        }
    	}else{
    		fssCustomerinfo=fssCustomerService.getFssCustomerEntityByCertNo(dto.getCert_no());
    	}
        return fssCustomerinfo;
    }

    private FssFuiouAccountEntity createFuiouAccount(CreateLoanAccountDto dto,FssCustomerEntity fssCustomerEntity,FssCustBankCardEntity fssCustBankCardEntity) throws FssException {
        try {
            FssFuiouAccountEntity fssFuiouAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssFuiouAccountEntity.class,dto);
            fssFuiouAccountEntity.setCusNo(String.valueOf(fssCustomerEntity.getCustNo()));
            fssFuiouAccountEntity.setUserNo(fssCustomerEntity.getUserId());
            fssFuiouAccountEntity.setAccNo(fssCustomerEntity.getMobile());
            fssFuiouAccountEntity.setAccUserName(fssCustomerEntity.getName());
            fssFuiouAccountEntity.setBankCardNo(fssCustBankCardEntity.getBankCardNo());
            fssFuiouAccountEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
            fssFuiouAccountEntity.setHasOpenAccFuiou(1);
            fssFuiouAccountWriteMapper.insertSelective(fssFuiouAccountEntity);
            return fssFuiouAccountEntity;
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
            throw  new FssException("91009804");
        }
    }



    /**
     * 创建资金账户
     * @param dto
     * @param customerEntity
     * @return
     * @throws FssException
     */
    public FssAccountEntity createFssAccountEntity(CreateLoanAccountDto dto,CustomerInfoEntity customerEntity) throws FssException {
            FssCustomerEntity fssCustomerEntity=this.createAccount(dto,customerEntity.getId());
    	try {
            FssAccountEntity fssAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssAccountEntity.class,dto);

            fssAccountEntity.setCustNo(String.valueOf(fssCustomerEntity.getId()));
            fssAccountEntity.setAccBalance(BigDecimal.ZERO);
            fssAccountEntity.setAccFreeze(BigDecimal.ZERO);
            fssAccountEntity.setAccAvai(BigDecimal.ZERO);
            fssAccountEntity.setAccNotran(BigDecimal.ZERO);
            fssAccountEntity.setCustNo(fssCustomerEntity.getCustNo());
            fssAccountEntity.setUserNo(fssCustomerEntity.getUserId().toString());
            String accType= GlobalConstants.TRADE_ACCOUNT_TYPE_MAPPING.get(dto.getTrade_type());//设置账户类型
            fssAccountEntity.setAccNo(CommonUtil.getAccountNo(accType));
            String channelNo=GlobalConstants.TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.get(dto.getTrade_type());//渠道编号
            fssAccountEntity.setAccType(Integer.parseInt(accType));
            fssAccountEntity.setState(10020001);//默认为有效账户
            fssAccountEntity.setBusiNo(dto.getContract_id());
            fssAccountEntity.setCustId(customerEntity.getId());
            //设置开户来源
            //设置渠道id
            fssAccountEntity.setChannelNo(Integer.parseInt(channelNo));//根据tradeType匹配
            fssAccountEntity.setMchnChild(dto.getMchn());
            fssAccountEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
            fssAccountWriteMapper.insertSelective(fssAccountEntity);
            return fssAccountEntity;
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
            if(e != null && e.getMessage().contains("busi_no_uk")){
                throw new FssException("90002017");
            }else{
                throw new FssException("90099005");
            }

        }
    }
    

    /**
     * 
     * author:jhz
     * time:2016年3月17日
     * function：根据acc_no查询账户
     */
    public FssAccountEntity getFssAccountByAccNo(String accNo){
    	return this.accountReadMapper.findAccountByAccNo(accNo);
    }
    



}
