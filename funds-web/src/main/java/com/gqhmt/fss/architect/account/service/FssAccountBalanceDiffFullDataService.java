package com.gqhmt.fss.architect.account.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountBalanceDiffEntity;
import com.gqhmt.fss.architect.account.entity.FssAccountBalanceDiffFullDataEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountBalanceDiffFullDataReadMapper;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountBalanceDiffReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssAccountBalanceDiffFullDataWriteMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssAccountBalanceDiffWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.util.DateUtil;

@Service
public class FssAccountBalanceDiffFullDataService {

	@Resource
    private FssAccountBalanceDiffReadMapper fssAccountBalanceDiffReadMapper;
	@Resource
    private FssAccountBalanceDiffWriteMapper fssAccountBalanceDiffWriteMapper;
	@Resource
    private FssAccountBalanceDiffFullDataReadMapper fssAccountBalanceDiffFullDataReadMapper;
	@Resource
    private FssAccountBalanceDiffFullDataWriteMapper fssAccountBalanceDiffFullDataWriteMapper;
	@Resource
    private PaySuperByFuiou paySuperByFuiou;

	/**
	 * 账户余额校验全量数据
	 * @throws FssException
	 */
    public void validateBalanceFullData() throws FssException{
    	
    	//查询本批次待余额校验的custId
    	List<String> custIdList = fssAccountBalanceDiffFullDataReadMapper.queryCurrentBusinessDealAccount();
    	if(CollectionUtils.isEmpty(custIdList)){
    		LogUtil.info(getClass(),"没有需要校验余额的全量数据");
    		return;
    	}
    	
    	//存储已处理的custId集合
    	List<String> processedList = Lists.newArrayList(); 
    	//存储余额有差异的记录
    	List<FssAccountBalanceDiffEntity> diffList = Lists.newArrayList(); 
    	
    	//根据custId查询待校验的账户信息
    	List<FssAccountBalanceDiffEntity> pendingList = fssAccountBalanceDiffReadMapper.queryBusinessDealAccountByCustIds(custIdList); 
    	
    	Date bizDate = DateUtil.stringToDate(DateUtil.dateToString(new Date()));
    	for(FssAccountBalanceDiffEntity account:pendingList){
    		FundAccountEntity primaryAccount = this.getFundAccountEntity(account);
    		CommandResponse resp = paySuperByFuiou.banlance(primaryAccount);
    		if(StringUtils.equals(resp.getCode(), "0000")){
    			Map<String,Object> mapResults = (Map<String, Object>) resp.getMap().get("results");
    			Map<String,String> mapResult = (Map<String, String>) mapResults.get("result");
    			String ctBalance = mapResult.get("ct_balance");
    			if(StringUtils.isBlank(ctBalance)){
    				ctBalance = "0";
    			}
    			BigDecimal fuiouBalance = new BigDecimal(ctBalance);
    			if(fuiouBalance.compareTo(account.getPlatAmount()) != 0){
    				account.setBizDate(bizDate);
    				account.setFuiouAmount(fuiouBalance);
    				account.setCreateTime(new Date());
    				account.setModifyTime(account.getCreateTime());
    				diffList.add(account);
    			}
    			processedList.add(account.getCustId().toString());
    		}else{
    			LogUtil.error(getClass(),"查询富友余额失败：accountId="+account.getAccountId());
    		}
    	}
    	
    	//插入存在差异的记录
    	if(CollectionUtils.isNotEmpty(diffList)){
        	LogUtil.info(getClass(),"本批次全量校验账户余额不匹配的记录数为："+diffList.size());
        	fssAccountBalanceDiffWriteMapper.batchInsertOrUpdate(diffList);
    	}
    	
    	//更新已完成校验的数据
    	if(CollectionUtils.isNotEmpty(processedList)){
    		LogUtil.info(getClass(),"本批次全量校验账户余额已处理的账号数为："+processedList.size());
    		fssAccountBalanceDiffFullDataWriteMapper.batchSetValidate(processedList);
    	}
    	
    }
    
    private FundAccountEntity getFundAccountEntity(FssAccountBalanceDiffEntity account){
    	FundAccountEntity primaryAccount = new FundAccountEntity();
		primaryAccount.setId(account.getAccountId().longValue());
		primaryAccount.setCustId(account.getCustId().longValue());
		primaryAccount.setUserName(account.getUserName());
		return primaryAccount;
    }
    
    /**
     * 初始化待余额校验的全量账户数据表
     */
    public void initFullData(){
    	List<FssAccountBalanceDiffFullDataEntity> list = fssAccountBalanceDiffFullDataReadMapper.selectAll();
    	if(CollectionUtils.isEmpty(list)){
    		fssAccountBalanceDiffFullDataWriteMapper.initFullData();
    	}
    }
    
}
