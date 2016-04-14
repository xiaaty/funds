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
import com.gqhmt.fss.architect.customer.mapper.write.FssBankCardInfoWriteMapper;
import com.gqhmt.fss.architect.customer.mapper.write.FssCustomerWriteMapper;
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
    @Resource
    private FssCustomerWriteMapper customerWriteMapper;
    @Resource
	private FssBankCardInfoWriteMapper fssBankCardInfoWriteMapper;
    
    public List<FssAccountEntity> findCustomerAccountByParams(FssAccountEntity fssAccountEntity){
        return this.accountReadMapper.findCustomerAccountByParams(fssAccountEntity);
    }

    /**
     * 查询账户信息
     * @return
     * @throws FssException
     */
    public List<BussAndAccountBean> queryAccountList(BussAndAccountBean bussaccount,String accType)throws FssException {
    	List<BussAndAccountBean> bussaccountlist=null;
    	
    	Map map=new HashMap();
    	if(StringUtils.isNotEmptyString(accType)){
    		map.put("accType",accType);
    	}
    	if(StringUtils.isNotEmptyString(bussaccount.getAccNo())){//业务编号
    		map.put("accNo",bussaccount.getAccNo());
    	}
    	if(StringUtils.isNotEmptyString(bussaccount.getCustName())){
    		map.put("custName",bussaccount.getCustName());
    	}
    	if(StringUtils.isNotEmptyString(bussaccount.getCertNo())){
    		map.put("certNo",bussaccount.getCertNo());
    	}
	    bussaccountlist=this.accountReadMapper.getBussinessAccountList(map);
        return bussaccountlist;
    }

    /**
     * 创建本地账户信息
     * @param dto
     * @throws FssException
     */
    public FssCustomerEntity createAccount(CreateLoanAccountDto dto,Long userId,String accNo) throws FssException {
    	FssCustomerEntity fssCustomerinfo=null;
    	FssCustBankCardEntity fssCustBankCardEntity=null;
    	FssFuiouAccountBean fssFuiouAccountBean=fssFuiouAccountReadMapper.getAccountByCentNo(dto.getCert_no());
    	if(fssFuiouAccountBean==null){//不存在
			try {
				fssCustomerinfo = fssCustomerService.createFssCustomerEntity(dto);//生成客户信息
				customerWriteMapper.insertSelective(fssCustomerinfo);
			} catch (Exception e) {
				LogUtil.info(this.getClass(), e.getMessage());
				throw new FssException("90002027");
			}
			try {
				fssCustBankCardEntity = fssCustBankCardService.createFssBankCardEntity(dto,fssCustomerinfo);
				fssBankCardInfoWriteMapper.insertSelective(fssCustBankCardEntity);
			} catch (Exception e) {
				LogUtil.info(this.getClass(), e.getMessage());
				throw new FssException("90002028");
			}
	        if(!dto.getTrade_type().equals("11020011")){//线下开户不走富友
	        	//生成第三方开户账户信息,纯线下,次开户,不开,线上需要开户.
	        	this.createFuiouAccount(dto,fssCustomerinfo,fssCustBankCardEntity,accNo);
	        }
    	}else{
    		fssCustomerinfo=fssCustomerService.getFssCustomerEntityByCertNo(dto.getCert_no());
    	}
        return fssCustomerinfo;
    }
    /**
     * 创建富友账户
     * @param dto
     * @param fssCustomerEntity
     * @param fssCustBankCardEntity
     * @param accNo
     * @return
     * @throws FssException
     */
    private FssFuiouAccountEntity createFuiouAccount(CreateLoanAccountDto dto,FssCustomerEntity fssCustomerEntity,FssCustBankCardEntity fssCustBankCardEntity,String accNo) throws FssException {
        try {
            FssFuiouAccountEntity fssFuiouAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssFuiouAccountEntity.class,dto);
            fssFuiouAccountEntity.setCusNo(String.valueOf(fssCustomerEntity.getCustNo()));
            fssFuiouAccountEntity.setUserNo(fssCustomerEntity.getUserId());
            fssFuiouAccountEntity.setAccNo(accNo);
            fssFuiouAccountEntity.setAccUserName(fssCustomerEntity.getName());
            fssFuiouAccountEntity.setBankCardNo(fssCustBankCardEntity.getBankCardNo());
            fssFuiouAccountEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
            fssFuiouAccountEntity.setHasOpenAccFuiou(1);
            fssFuiouAccountWriteMapper.insertSelective(fssFuiouAccountEntity);
            return fssFuiouAccountEntity;
        } catch (Exception e) {
        	LogUtil.info(this.getClass(), e.getMessage());
			throw new FssException("90002029");
        }
    }



    /**
     * 创建资金账户
     * @param dto
     * @param customerEntity
     * @return
     * @throws FssException
     */
    public FssAccountEntity createFssAccountEntity(CreateLoanAccountDto dto,Long custId) throws FssException {
    		String accType= GlobalConstants.TRADE_ACCOUNT_TYPE_MAPPING.get(dto.getTrade_type());//设置账户类型
    		String accNo=CommonUtil.getAccountNo(accType);
            FssCustomerEntity fssCustomerEntity=this.createAccount(dto,custId,accNo);
    	try {
            FssAccountEntity fssAccountEntity=this.getFssAccountEntityByCustId(custId);
            if(fssAccountEntity==null){
            	fssAccountEntity=this.createNewFssAccountEntity(fssCustomerEntity,dto,custId,accNo,accType);
            	fssAccountWriteMapper.insertSelective(fssAccountEntity);
            }
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
    

  /*  public FssAccountEntity getAccountEntityByCustid(Long custId){
    	return this.accountReadMapper.findAccountByCustId(custId);
    }*/
    
    public FssAccountEntity getFssAccountEntityByCustId(Long custId){
    	FssAccountEntity fssAccountEntity=new FssAccountEntity();
    	fssAccountEntity.setCustId(custId);
    	return accountReadMapper.selectOne(fssAccountEntity);
    }
    
    public FssAccountEntity createNewFssAccountEntity(FssCustomerEntity fssCustomerEntity,CreateLoanAccountDto dto,Long custId,String accNo,String accType)  throws Exception{
    	FssAccountEntity fssAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssAccountEntity.class,dto);
    	fssAccountEntity.setAccBalance(BigDecimal.ZERO);
    	fssAccountEntity.setAccFreeze(BigDecimal.ZERO);
    	fssAccountEntity.setAccAvai(BigDecimal.ZERO);
    	fssAccountEntity.setAccNotran(BigDecimal.ZERO);
    	fssAccountEntity.setCustNo(fssCustomerEntity.getCustNo());
    	fssAccountEntity.setUserNo(fssCustomerEntity.getUserId().toString());
    	fssAccountEntity.setAccNo(accNo);
    	String channelNo=GlobalConstants.TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.get(dto.getTrade_type());//渠道编号
    	fssAccountEntity.setAccType(Integer.parseInt(accType));
    	fssAccountEntity.setState(10020001);//默认为有效账户
//      fssAccountEntity.setBusiNo(dto.getContract_id());
    	fssAccountEntity.setCustId(custId);
    	fssAccountEntity.setChannelNo(Integer.parseInt(channelNo));//根据tradeType匹配
    	fssAccountEntity.setMchnChild(dto.getMchn());
    	fssAccountEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
    	return fssAccountEntity;
    }
    
    
    
    
}
