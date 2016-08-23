package com.gqhmt.fss.architect.account.service;

import com.gqhmt.fss.architect.account.entity.FssAccountBalanceDiffEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountBalanceDiffReadMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.mapper.write.FssAccountBalanceDiffWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.util.DateUtil;

@Service
public class FssAccountBalanceDiffService {

    @Resource
    private FssAccountBalanceDiffReadMapper fssAccountBalanceDiffReadMapper;
	@Resource
    private FssAccountBalanceDiffWriteMapper fssAccountBalanceDiffWriteMapper;
	@Resource
    private PaySuperByFuiou paySuperByFuiou;

	/**
	 * 查询账户余额差异表信息集合
	 * @author xdw
	 * @return
	 */
	public List<FssAccountBalanceDiffEntity> queryAccBalanceDiffList(Map<String,String> map) {
		return fssAccountBalanceDiffReadMapper.queryAccBalanceDiffList(map);
	}

	/**
	 * 账户余额校验
	 * @throws FssException
	 */
    public void validateBalance() throws FssException{
    	//查询T-1日与富友产生交易的账户信息
    	List<FssAccountBalanceDiffEntity> accountList = fssAccountBalanceDiffReadMapper.queryLastDayBusinessDealAccount();
    	if(CollectionUtils.isEmpty(accountList)){
    		LogUtil.info(getClass(),"不存在T-1日与富友产生交易的账户");
			return;
    	}
    	//存储余额有差异的记录
    	List<FssAccountBalanceDiffEntity> diffList = Lists.newArrayList(); 
    	
    	Date bizDate = DateUtil.stringToDate(DateUtil.dateToString(new Date()));
    	for(FssAccountBalanceDiffEntity account:accountList){
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
    		}else{
    			LogUtil.error(getClass(),"查询富友余额失败：accountId="+account.getAccountId());
    		}
    	}
    	
    	//插入存在差异的记录
    	if(CollectionUtils.isNotEmpty(diffList)){
        	LogUtil.info(getClass(),"T-1日与富友产生交易的账户余额不匹配的记录数为："+diffList.size());
        	fssAccountBalanceDiffWriteMapper.batchInsertOrUpdate(diffList);
    	}
    	
    }
    
    private FundAccountEntity getFundAccountEntity(FssAccountBalanceDiffEntity account){
    	FundAccountEntity primaryAccount = new FundAccountEntity();
		primaryAccount.setId(account.getAccountId().longValue());
		primaryAccount.setCustId(account.getCustId().longValue());
		primaryAccount.setUserName(account.getUserName());
		return primaryAccount;
    }
    
}
