package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.trade.entity.FssChargeRecordEntity;
import com.gqhmt.fss.architect.trade.mapper.write.FssChargeRecordWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月31日
 * Description:
 * <p>收费交易记录
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月31日      1.0     1.0 Version
 */
@Service
public class FssChargeRecordService {
	
//	@Resource
//	private FssChargeRecordReadMapper fssChargeRecordReadMapper;
	@Resource
	private FssChargeRecordWriteMapper fssChargeRecordWriteMapper;

	/**
	 *创建收费记录
	 * @param fromEntity
	 * @param toEntiry
	 * @param amt
	 * @param loanType
	 * @param bustType
     * @throws FssException
     */
	public FssChargeRecordEntity addChargeRecord(FundAccountEntity fromEntity, FundAccountEntity toEntiry,BigDecimal amt,String loanType, String bustType,String tradeType,String seqNo,String busiNo,String fromAccType,String toAccType,String summary) throws FssException{
		FssChargeRecordEntity entity= GenerateBeanUtil.GenerateClassInstance(FssChargeRecordEntity.class);
		Map<String,String> platFormMap=new HashMap<String,String>();
		platFormMap.put("10040001","北京");
		platFormMap.put("10040002","天津");
		platFormMap.put("10040003","上海");
		platFormMap.put("10040099","其他城市");
		entity.setFromAccNo(fromEntity.getAccountNo());
		entity.setFromCustNo(String.valueOf(fromEntity.getCustId()));
		entity.setFromCustName(fromEntity.getCustName());
		entity.setFromAccType(fromAccType);
		entity.setToAccNo(toEntiry.getAccountNo());
		entity.setToCustNo(String.valueOf(toEntiry.getCustId()));
		entity.setToAccType(toAccType);
		entity.setToCustName(toEntiry.getCustName());
		entity.setAmt(amt);
		entity.setSeqNo(seqNo);
		entity.setBusiNo(busiNo);
		entity.setBusiType(bustType);
		entity.setTradeType(tradeType);
		entity.setChargeType(tradeType);//费用类型
		entity.setTradeTime(new Date());
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setPlatform(platFormMap.get(loanType));
		entity.setSumary(summary);
		try {
			fssChargeRecordWriteMapper.insert(entity);
			return entity;
		}catch (Exception e){
			throw new FssException("91009804");
		}
	}

	/**
	 *成功修改状态
	 * @throws FssException
     */
    public void updateChargeRecord(FssChargeRecordEntity entity,String orderNo,String tradeResult) throws FssException{
		entity.setTradeResult(tradeResult);
		entity.setOrderNo(orderNo);
		entity.setModifyTime(new Date());
		entity.setRespMsg("成功");
		fssChargeRecordWriteMapper.updateByPrimaryKey(entity);
}







}
