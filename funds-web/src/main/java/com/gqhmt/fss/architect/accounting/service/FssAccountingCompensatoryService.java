package com.gqhmt.fss.architect.accounting.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingLoanCompensatory;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingServiceCharge;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingCompensatoryWritreMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingCompensatoryReadMapper;
/**
 * 借款代偿service
 * @author 柯禹来
 */
@Service
public class FssAccountingCompensatoryService {
	
	@Resource
	private FssAccountingCompensatoryReadMapper fssAccountingCompensatoryReadMapper;
	@Resource
	private FssAccountingCompensatoryWritreMapper fssAccountingCompensatoryWritreMapper;
	
	/**
	 * 借款代偿列表
	 * @param map
	 * @return
	 */
	public List<FssAccountingLoanCompensatory> queryFssAccountingCompensatoryList(Map<String,String> map){
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
		return fssAccountingCompensatoryReadMapper.queryFssAccountingLoanCompensatory(map2);
	}
	
	/**
	 * 借款代偿实体类
	 */
	public FssAccountingLoanCompensatory createFssAccountingLoanCompensatory(String custNo,String custName,String accNo,String loanAccNo,String loanNo,String accountingNo,
			String fundType,String compensatory,Long sendBack,String summary,String tradeDate,String tradeTime){
		FssAccountingLoanCompensatory entity=new FssAccountingLoanCompensatory();
		entity.setCustNo(custNo);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setLoanNo(loanNo);
		entity.setLoanAccNo(loanAccNo);
		entity.setAccountingNo(accountingNo);
		entity.setFundType(fundType);
		entity.setCompensatory(compensatory);
		entity.setSendBack(sendBack);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 借款代偿表数据插入
	 * @param entity
	 * @throws FssException
	 */
	public void insert(FssAccountingServiceCharge entity) throws FssException{
		try {
			fssAccountingCompensatoryWritreMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("借款代偿表数据插入出错");
		}
	}
}
