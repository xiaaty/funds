package com.gqhmt.fss.architect.accounting.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.accounting.entity.FssAccountingServiceCharge;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.fss.architect.accounting.mapper.write.FssAccountingChargeWritreMapper;
import com.gqhmt.fss.architect.accounting.mapper.read.FssAccountingChargeReadMapper;

@Service
public class FssAccountingChargeService {
	
	@Resource
	private FssAccountingChargeReadMapper fssAccountingChargeReadMapper;
	@Resource
	private FssAccountingChargeWritreMapper fssAccountingChargeWritreMapper;
	
	/**
	 * 逆服务费列表
	 * @param map
	 * @return
	 */
	public List<FssAccountingServiceCharge> queryFssAccountingChargeList(Map<String,String> map){
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
		return fssAccountingChargeReadMapper.queryFssAccountingServiceCharge(map2);
	}
	
	/**
	 * 创建逆服务费实体类
	 */
	public FssAccountingServiceCharge createFssAccountingCharge(String custNo,String custName,String accNo,String accountingNo,
			String fundType,String spending,Long sendBack,String summary,String tradeDate,String tradeTime){
		FssAccountingServiceCharge entity=new FssAccountingServiceCharge();
		entity.setCustNo(custNo);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setAccountingNo(accountingNo);
		entity.setFundType(fundType);
		entity.setSpending(spending);
		entity.setSendBack(sendBack);
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setSummary(summary);
		entity.setTradeDate(tradeDate);
		entity.setTradeTime(tradeTime);
		return entity;
	}
	
	/**
	 * 逆服务费表数据插入
	 * @param entity
	 * @throws FssException
	 */
	public void insert(FssAccountingServiceCharge entity) throws FssException{
		try {
			fssAccountingChargeWritreMapper.insert(entity);
		} catch (Exception e) {
			throw new FssException("逆服务费表数据插入出错");
		}
	}
}
