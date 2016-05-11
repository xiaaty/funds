package com.gqhmt.fss.architect.accounting.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingCompanyIncome;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoanCompensatory;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingServiceCharge;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingCompanyIncomeWritreMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingCompensatoryWritreMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingCompanyIncomeReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingCompensatoryReadMapper;
/**
 * 公司收入service
 * @author 柯禹来
 */
@Service
public class FssAccountingCompanyIncomeService {
	
	@Resource
	private FssAccountingCompanyIncomeReadMapper fssAccountingCompanyIncomeReadMapper;
	@Resource
	private FssAccountingCompanyIncomeWritreMapper fssAccountingCompanyIncomeWritreMapper;
	
	/**
	 * 公司收入列表
	 * @param map
	 * @return
	 */
	public List<FssAccountingCompanyIncome> queryFssAccountingCompanyIncomeList(Map<String,String> map){
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
		return fssAccountingCompanyIncomeReadMapper.queryFssAccountingCompanyIncome(map2);
	}
	
	/**
	 * 公司收入实体类
	 */
	public FssAccountingCompanyIncome createFssAccountingLoanCompensatory(String custSource,String custName,String accNo,String freeAccNo,String accountingNo,
			String fundType,String busiNo,String busiType,Long income,Long  sendBack,String summary,String tradeDate,String tradeTime){
		FssAccountingCompanyIncome entity=new FssAccountingCompanyIncome();
		entity.setCustSource(custSource);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setFreeAccNo(freeAccNo);
		entity.setAccountingNo(accountingNo);
		entity.setFundType(fundType);
		entity.setBusiNo(busiNo);
		entity.setBusiType(busiType);
		entity.setIncome(income);
		entity.setSendBack(sendBack);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 公司收入表数据插入
	 * @param entity
	 * @throws FssException
	 */
	public void insert(FssAccountingCompanyIncome entity) throws FssException{
		try {
			fssAccountingCompanyIncomeWritreMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("公司收入表数据插入出错");
		}
	}
}
