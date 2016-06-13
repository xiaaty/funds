package com.gqhmt.fss.architect.accounting.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoandebtDetail;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoandebt;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingLoandebtDetailWriteMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingLoandebtWriteMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingLoandebtDetailReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingLoandebtReadMapper;

@Service
public class FssAccountingLoandebtService {
	
	@Resource
	private FssAccountingLoandebtReadMapper fssAccountingLoandebtReadMapper;
	@Resource
	private FssAccountingLoandebtWriteMapper fssAccountingLoandebtWriteMapper;
	@Resource
	private FssAccountingLoandebtDetailReadMapper fssAccountingLoandebtDetailReadMapper;
	@Resource
	private FssAccountingLoandebtDetailWriteMapper fssAccountingLoandebtDetailWriteMapper;
	
	/**
	 * 借款负债列表
	 * @param map
	 * @return
	 */
	public List<FssAccountingLoandebt> queryFssAccountingLoandebtList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("accountingNo",map.get("accountingNo"));
			map2.put("accNo", map.get("accNo"));
			map2.put("loanNo", map.get("loanNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		
		return fssAccountingLoandebtReadMapper.queryFssAccountingLoandebt(map2);
	}
	
	/**
	 * 借款负债详细列表
	 * @param map
	 * @return
	 */
	public List<FssAccountingLoandebtDetail> queryFssAccountingLoandebtDetail(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("accountingNo",map.get("accountingNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		return fssAccountingLoandebtDetailReadMapper.queryFssAccountingLoandebtDetail(map2);
	}
	
	/**
	 * 创建借款负债主表实体
	 * @param custNo
	 * @param custName
	 * @param accNo
	 * @param accountingNo
	 * @param loanNo
	 * @param debtAmount
	 * @param paidTotle
	 * @param summary
	 * @param tradeDate
	 * @param tradeTime
	 * @return
	 */
	public FssAccountingLoandebt createFssAccountingLoandebt(String custNo,String  custName,String accNo,String accountingNo,String loanNo,Long debtAmount,Long paidTotle,String summary,String tradeDate,String tradeTime){
		FssAccountingLoandebt entity=new FssAccountingLoandebt();
		entity.setCustNo(custNo);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setAccountingNo(accountingNo);
		entity.setLoanNo(loanNo);
		entity.setDebtAmount(debtAmount);
		entity.setPaidTotle(paidTotle);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 借款负债主表数据插入
	 * @param entity
	 * @throws FssException
	 */
	public void insert(FssAccountingLoandebt entity) throws FssException{
		
		try {
			fssAccountingLoandebtWriteMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("借款负债主表数据插入出错");
		}
	}
	
	/**
	 * 创建借款负债子表实体
	 * @param custNo
	 * @param custName
	 * @param accNo
	 * @param accountingNo
	 * @param loanNo
	 * @param fundType
	 * @param amountReceived
	 * @param amountActually
	 * @param amountDebt
	 * @param repaymentPeriods
	 * @param summary
	 * @param tradeDate
	 * @param tradeTime
	 * @return
	 */
	public FssAccountingLoandebtDetail createFssAccountingLoandebtDetail(String custNo,String  custName,String accNo,String accountingNo,String loanNo,String  fundType,Long amountReceived,Long amountActually,Long amountDebt,Long repaymentPeriods,String summary,String tradeDate,String tradeTime){
		FssAccountingLoandebtDetail entity=new FssAccountingLoandebtDetail();
		entity.setCustNo(custNo);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setAccountingNo(accountingNo);
		entity.setLoanNo(loanNo);
		entity.setFundType(fundType);
		entity.setAmountReceived(amountReceived);
		entity.setAmountActually(amountActually);
		entity.setAmountDebt(amountDebt);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 借款负债子表数据插入
	 * @param entity
	 * @throws FssException
	 */
	public void insert(FssAccountingLoandebtDetail entity) throws FssException{
		
		try {
			fssAccountingLoandebtDetailWriteMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("借款负债子表数据插入出错");
		}
	}
	
	
	
	
}
