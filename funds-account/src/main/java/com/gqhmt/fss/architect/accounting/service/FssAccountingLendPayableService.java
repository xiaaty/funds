package com.gqhmt.fss.architect.accounting.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLendPayable;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLendPayableDetail;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingLendPayableDetailReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingLendPayableDetailWriteMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingLendPayableWriteMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingLendPayableReadMapper;

@Service
public class FssAccountingLendPayableService {
	@Resource
    private FssAccountingLendPayableReadMapper fssAccountingLendPayableReadMapper;
	@Resource
	private FssAccountingLendPayableWriteMapper fssAccountingLendPayableWriteMapper;
	@Resource
	private FssAccountingLendPayableDetailReadMapper fssAccountingLendPayableDetailReadMapper;
	@Resource
	private FssAccountingLendPayableDetailWriteMapper fssAccountingLendPayableDetailWriteMapper;
	
	/**
     * 出借应付款列表
	 * @param map
	 * @return
	 */
	public List<FssAccountingLendPayable> queryFssAccountingLendPayableList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("accountingNo",map.get("accountingNo"));
			map2.put("accNo", map.get("accNo"));
			map2.put("lendNo", map.get("lendNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		return fssAccountingLendPayableReadMapper.queryFssAccountingLendPayable(map2);
	}
	
	/**
	 * 出借应付款详细
	 * @param map
	 * @return
	 */
	public List<FssAccountingLendPayableDetail> queryFssAccountingLendPayableDetailList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("accountingNo",map.get("accountingNo"));
			map2.put("lendNo", map.get("lendNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		return fssAccountingLendPayableDetailReadMapper.queryFssAccountingLendPayableDetail(map2);
	}
	
	/**
	 * 创建出借应付款主表实体
	 * @param custNo
	 * @param accNo
	 * @param accountingNo
	 * @param lendNo
	 * @param balance
	 * @param payTotal
	 * @param paidTotle
	 * @param summary
	 * @param tradeDate
	 * @param tradeTime
	 * @return
	 */
	public FssAccountingLendPayable createFssAccountingLendPayable(String custNo,String accNo,String accountingNo,String lendNo,Long balance,Long payTotal,Long paidTotle,String summary,String tradeDate,String tradeTime){
		FssAccountingLendPayable entity=new FssAccountingLendPayable();
		entity.setCustNo(custNo);
		entity.setAccNo(accNo);
		entity.setAccountingNo(accountingNo);
		entity.setLendNo(lendNo);
		entity.setBalance(balance);
		entity.setPayTotal(payTotal);
		entity.setPaidTotle(paidTotle);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 出借应付款主表数据插入
	 */
	public void insert(FssAccountingLendPayable entity) throws FssException{
		try {
			fssAccountingLendPayableWriteMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("出借应付款主表数据插入失败");
		}
	}
	
	
	/**
	 * 创建出借应付款字表表实体
	 * @param custNo
	 * @param custName
	 * @param accNo
	 * @param accountingNo
	 * @param lendNo
	 * @param fundType
	 * @param payableAmount
	 * @param paidAmount
	 * @param balanceAmount
	 * @param summary
	 * @param tradeDate
	 * @param tradeTime
	 * @return entity
	 */
	public FssAccountingLendPayableDetail createFssAccountingLendPayableDetail(String custNo,String  custName,String accNo,String accountingNo,String lendNo,String  fundType,
			Long payableAmount,Long paidAmount,Long balanceAmount,String summary,String tradeDate,String tradeTime){
		FssAccountingLendPayableDetail entity=new FssAccountingLendPayableDetail();
		entity.setCustNo(custNo);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setAccountingNo(accountingNo);
		entity.setLendNo(lendNo);
		entity.setFundType(fundType);
		entity.setPayableAmount(payableAmount);
		entity.setPaidAmount(paidAmount);
		entity.setBalanceAmount(balanceAmount);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 出借应付款子表数据插入
	 */
	public void insert(FssAccountingLendPayableDetail entity) throws FssException{
		try {
			fssAccountingLendPayableDetailWriteMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("出借应付款子表数据插入失败");
		}
	}
	
}
