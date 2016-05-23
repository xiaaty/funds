package com.gqhmt.fss.architect.accounting.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingMargin;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingMarginDetail;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingMarginReadMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingMarginDetailWritreMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingMarginWritreMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingMarginDetailReadMapper;

@Service
public class FssAccountingMarginService {
	
	@Resource
	private FssAccountingMarginReadMapper FssAccountingMarginReadMapper;
	@Resource
	private FssAccountingMarginWritreMapper fssAccountingMarginWritreMapper;
	@Resource
	private FssAccountingMarginDetailReadMapper fssAccountingMarginDetailReadMapper;
	@Resource
	private FssAccountingMarginDetailWritreMapper fssAccountingMarginDetailWriteMapper;
	
	/**
	 * 保证金列表
	 * @param map
	 * @return
	 */
	public List<FssAccountingMargin> queryFssAccountingMarginList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("accountingNo",map.get("accountingNo"));
			map2.put("accNo", map.get("accNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		return FssAccountingMarginReadMapper.queryFssAccountingMargin(map);
	}
	
	/**
	 * 保证金详细
	 * @param map
	 * @return
	 */
	public List<FssAccountingMarginDetail> queryFssAccountingMarginDetail(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("accountingNo",map.get("accountingNo"));
			map2.put("loanNo",map.get("loanNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		return fssAccountingMarginDetailReadMapper.queryFssAccountingMarginDetail(map2);
	}
	
	/**
	 * 创建保证金主表实体
	 * @param custNo
	 * @param custName
	 * @param accNo
	 * @param accountingNo
	 * @param balance
	 * @param receiveMargin
	 * @param taxMargin
	 * @param deductMargin
	 * @param summary
	 * @param tradeDate
	 * @param tradeTime
	 * @return
	 */
	public FssAccountingMargin createFssAccountingMargin(String custNo,String  custName,String accNo,String accountingNo,Long balance,Long receiveMargin,Long taxMargin,Long deductMargin,String summary,String tradeDate,String tradeTime){
		FssAccountingMargin entity=new FssAccountingMargin();
		entity.setCustNo(custNo);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setAccountingNo(accountingNo);
		entity.setBalance(balance);
		entity.setReceiveMargin(receiveMargin);
		entity.setTaxMargin(taxMargin);
		entity.setDeductMargin(deductMargin);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 保证金主表数据插入
	 * @param entity
	 * @throws FssException
	 */
	public void insert(FssAccountingMargin entity) throws FssException{
		try {
			fssAccountingMarginWritreMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("保证金主表数据插入出错");
		}
	}
	
	/**
	 * 创建保证金子表实体
	 * @param custNo
	 * @param custName
	 * @param accNo
	 * @param accountingNo
	 * @param loanNo
	 * @param fundType
	 * @param receiveMargin
	 * @param taxMargin
	 * @param balance
	 * @param summary
	 * @param tradeDate
	 * @param tradeTime
	 * @return
	 */
	public FssAccountingMarginDetail createFssAccountingMarginDetail(String custNo,String  custName,String accNo,String accountingNo,String loanNo,String  fundType,Long receiveMargin,Long taxMargin,Long balance,String summary,String tradeDate,String tradeTime){
		FssAccountingMarginDetail entity=new FssAccountingMarginDetail();
		entity.setCustNo(custNo);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setAccountingNo(accountingNo);
		entity.setLoanNo(loanNo);
		entity.setFundType(fundType);
		entity.setReceiveMargin(receiveMargin);
		entity.setTaxMargin(taxMargin);
		entity.setBalance(balance);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 保证金子表数据插入
	 * @param entity
	 * @throws FssException
	 */
	public void insert(FssAccountingMarginDetail entity) throws FssException{
		try {
			fssAccountingMarginDetailWriteMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("保证金子表数据插入出错");
		}
	}
	
}
