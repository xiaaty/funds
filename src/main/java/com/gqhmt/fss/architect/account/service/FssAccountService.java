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
import com.gqhmt.funds.architect.customer.mapper.write.CustomerInfoWriteMapper;
import com.gqhmt.funds.architect.customer.mapper.write.GqUserWriteMapper;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    
    public List<FssAccountEntity> findCustomerAccountByParams(FssAccountEntity fssAccountEntity)throws FssException{
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
/*
    *//**
     * 创建本地账户信息
     * @param dto
     * @throws FssException
     *//*
    public FssCustomerEntity createAccount(CreateLoanAccountDto dto,String accNo) throws FssException {
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
	        if(!dto.getTrade_type().equals()){//线下开户不走富友
	        	//生成第三方开户账户信息,纯线下,次开户,不开,线上需要开户.
	        	this.createFuiouAccount(dto,fssCustomerinfo,fssCustBankCardEntity,accNo);
	        }
    	}else{
    		fssCustomerinfo=fssCustomerService.getFssCustomerEntityByCertNo(dto.getCert_no());

    	}
        return fssCustomerinfo;
    }*/

    public FssAccountEntity createAccount(CreateLoanAccountDto dto,Long custId) throws FssException {
        return this.createAccount(dto.getTrade_type(),dto.getMchn(),dto.getMobile(),dto.getCert_no(),dto.getName(),dto.getBank_id(),dto.getBank_card(),dto.getCity_id(),dto.getContract_no(),custId);
    }

	public FssAccountEntity createAccount(String tradeType,String mchn,String mobile,String certNo,String name,String bankType,String bankNo,String area,String busiNo,Long custId) throws FssException {
		FssCustomerEntity fssCustomerinfo= fssCustomerService.getFssCustomerEntityByCertNo(certNo);
		FssCustBankCardEntity  fssCustBankCardEntity = null;
        FssFuiouAccountEntity fssFuiouAccountEntity = null;
        FssAccountEntity fssAccountEntity = null;
		if(fssCustomerinfo == null){
            //首次开户,验证银行卡信息
            if(bankType == null || bankNo == null || area == null){
                throw new FssException("90002031");
            }

            //验证手机号是否存在
			//生成客户信息
			fssCustomerinfo = fssCustomerService.createFssCustomerEntity(name,mobile,certNo,custId,mchn);//生成客户信息
			//生成银行卡信息
			fssCustBankCardEntity = fssCustBankCardService.createFssBankCardEntity(bankType,bankNo,area,mchn,fssCustomerinfo);
            //生成富友庄户
            if(!"11020011".equals(tradeType)){
                fssFuiouAccountEntity = this.createFuiouAccount(mchn,fssCustomerinfo,fssCustBankCardEntity);
            }

		}else{

            if(fssCustomerinfo.getCustId() == null){
                fssCustomerService.updateCustId(fssCustomerinfo,custId);
            }

//            FssFuiouAccountBean fssFuiouAccountBean=fssFuiouAccountReadMapper.getAccountByCentNo(certNo);
            fssFuiouAccountEntity=fssFuiouAccountReadMapper.getByAccNo(fssCustomerinfo.getMobile());
            if(fssFuiouAccountEntity == null && !"11020011".equals(tradeType)){
                //获取银行信息
                //验证银行卡信息
                //首次富友开户,需要银行信息
                if(bankType == null || bankNo == null || area == null){
                    throw new FssException("90002031");
                }
                //生成银行卡信息
                fssCustBankCardEntity = fssCustBankCardService.createFssBankCardEntity(bankType,bankNo,area,mchn,fssCustomerinfo);
                fssFuiouAccountEntity = this.createFuiouAccount(mchn,fssCustomerinfo,fssCustBankCardEntity);
            }else{
                //验证银行信息.....    todo
            }


        }

        try{
            fssAccountEntity = this.createNewFssAccountEntity(fssCustomerinfo,tradeType,busiNo,mchn,fssFuiouAccountEntity.getAccNo());
        }catch (Exception e){
            LogUtil.error(this.getClass(),e);
            if(e != null && e.getMessage().contains("busi_no_uk")){
                throw new FssException("90002017");
            }else{
                throw new FssException("90099005");
            }
        }

        return fssAccountEntity;

	}


    /**
     * 
     * author:jhz
     * time:2016年3月17日
     * function：根据acc_no查询账户
     */
    public FssAccountEntity getFssAccountByAccNo(String accNo)throws FssException{
    	return this.accountReadMapper.findAccountByAccNo(accNo);
    }
    


    public FssAccountEntity createNewFssAccountEntity(FssCustomerEntity fssCustomerEntity,String tradeType,String busiNo,String mchn,String  thirdAccNo)  throws FssException{
    	FssAccountEntity fssAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssAccountEntity.class);
    	fssAccountEntity.setAccBalance(BigDecimal.ZERO);
    	fssAccountEntity.setAccFreeze(BigDecimal.ZERO);
    	fssAccountEntity.setAccAvai(BigDecimal.ZERO);
    	fssAccountEntity.setAccNotran(BigDecimal.ZERO);
    	fssAccountEntity.setCustNo(fssCustomerEntity.getCustNo());
    	fssAccountEntity.setUserNo(fssCustomerEntity.getUserId().toString());
        String accType= GlobalConstants.TRADE_ACCOUNT_TYPE_MAPPING.get(tradeType);//设置账户类型
        String accNo=CommonUtil.getAccountNo(accType);
    	fssAccountEntity.setAccNo(accNo);
    	String channelNo=GlobalConstants.TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.get(tradeType);//渠道编号
    	fssAccountEntity.setAccType(Integer.parseInt(accType));
    	fssAccountEntity.setState(10020001);//默认为有效账户
        fssAccountEntity.setBusiNo(busiNo);
    	fssAccountEntity.setCustId(fssCustomerEntity.getCustId());
    	fssAccountEntity.setChannelNo(Integer.parseInt(channelNo));//根据tradeType匹配
        fssAccountEntity.setThirdAccNo(thirdAccNo);
        fssAccountEntity.setTradeType(tradeType);
    	fssAccountEntity.setMchnChild(mchn);
    	fssAccountEntity.setMchnParent(Application.getInstance().getParentMchn(mchn));
    	fssAccountWriteMapper.insert(fssAccountEntity);
    	return fssAccountEntity;
    }


	/**
	 * 创建富友账户
	 * @param mchn
	 * @param fssCustomerEntity
	 * @param fssCustBankCardEntity
	 * @return
	 * @throws FssException
	 */
	private FssFuiouAccountEntity createFuiouAccount(String mchn,FssCustomerEntity fssCustomerEntity,FssCustBankCardEntity fssCustBankCardEntity) throws FssException {
		try {
			FssFuiouAccountEntity fssFuiouAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssFuiouAccountEntity.class);
			fssFuiouAccountEntity.setCusNo(String.valueOf(fssCustomerEntity.getCustNo()));
			fssFuiouAccountEntity.setUserNo(fssCustomerEntity.getUserId());
			fssFuiouAccountEntity.setAccNo(fssCustomerEntity.getMobile());
			fssFuiouAccountEntity.setAccUserName(fssCustomerEntity.getName());
			fssFuiouAccountEntity.setBankCardNo(fssCustBankCardEntity.getBankCardNo());
            fssFuiouAccountEntity.setMchnChild(mchn);
			fssFuiouAccountEntity.setMchnParent(Application.getInstance().getParentMchn(mchn));
			fssFuiouAccountEntity.setHasOpenAccFuiou(2);
			fssFuiouAccountWriteMapper.insertSelective(fssFuiouAccountEntity);
			return fssFuiouAccountEntity;
		} catch (Exception e) {
			LogUtil.info(this.getClass(), e.getMessage());
			throw new FssException("90002029");
		}
	}
    
    
}
