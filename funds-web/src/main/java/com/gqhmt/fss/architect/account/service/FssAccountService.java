package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.*;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
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
    public List<BussAndAccountBean> queryAccountList(Map<String,String> map)throws FssException {
    	List<BussAndAccountBean> bussaccountlist=null;
    	Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("accType",map.get("type"));
			map2.put("custNo", map.get("custNo"));
			map2.put("accNo", map.get("accNo"));
			map2.put("custName", map.get("custName"));
			map2.put("certNo", map.get("certNo"));
			map2.put("busiNo", map.get("busiNo"));
			map2.put("mobile", map.get("mobile"));
		}
	    bussaccountlist=this.accountReadMapper.getBussinessAccountList(map2);
        return bussaccountlist;
    }


    public FssAccountEntity createAccount(String tradeType,String mchn,String mobile,String certNo,String name,String bankType,String bankNo,String area,String busiNo,Long custId) throws FssException {
        return createAccount(tradeType,mchn,mobile,certNo,name,bankType,bankNo,area,busiNo,custId,null);
    }

    public FssAccountEntity createAccount(String tradeType,String mchn,String mobile,String certNo,String name,String bankType,String bankNo,String area,String busiNo,Long custId,Date createTime) throws FssException {
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
                fssFuiouAccountEntity = this.createFuiouAccount(mchn,fssCustomerinfo);
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
                fssFuiouAccountEntity = this.createFuiouAccount(mchn,fssCustomerinfo);
            }else{
                //验证银行信息.....    todo
            }


        }

        try{
        	if(!"11020012".equals(tradeType) && !"11020011".equals(tradeType) && !"11020006".equals(tradeType) && !"11020007".equals(tradeType)){
        		busiNo=fssCustomerinfo.getCustNo();
        	}
            fssAccountEntity = this.createNewFssAccountEntity(fssCustomerinfo,tradeType,busiNo,mchn,fssFuiouAccountEntity == null ?"":fssFuiouAccountEntity.getAccNo(),createTime);
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

    /**
     *
     * author:jhz
     * time:2016年6月6日
     * function：根据cust_id查询账户
     */
    public FssAccountEntity getFssAccountByCustId(Long custId)throws FssException{
    	return this.accountReadMapper.findAccountByCustId(custId);
    }

    public FssAccountEntity createNewFssAccountEntity(FssCustomerEntity fssCustomerEntity,String tradeType,String busiNo,String mchn,String  thirdAccNo,Date createTime)  throws FssException{
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
        if(createTime != null){
            fssAccountEntity.setCreateTime(createTime);
        }
    	fssAccountWriteMapper.insert(fssAccountEntity);
    	return fssAccountEntity;
    }


	/**
	 * 创建富友账户
	 * @param mchn
	 * @param fssCustomerEntity
	 * @return
	 * @throws FssException
	 */
	public FssFuiouAccountEntity createFuiouAccount(String mchn,FssCustomerEntity fssCustomerEntity) throws FssException {
		try {
			FssFuiouAccountEntity fssFuiouAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssFuiouAccountEntity.class);
			fssFuiouAccountEntity.setCusNo(String.valueOf(fssCustomerEntity.getCustNo()));
			fssFuiouAccountEntity.setUserNo(fssCustomerEntity.getUserId());
			fssFuiouAccountEntity.setAccNo(fssCustomerEntity.getMobile());
            if(fssCustomerEntity.getCustId()<100){
                fssFuiouAccountEntity.setAccUserName(GlobalConstants.COMPANY_ACCOUNT_REAL_NAME.get(fssCustomerEntity.getCustId()));
            }else {
                fssFuiouAccountEntity.setAccUserName(fssCustomerEntity.getName());
            }
//			fssFuiouAccountEntity.setBankCardNo(fssCustBankCardEntity.getBankCardNo());
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
