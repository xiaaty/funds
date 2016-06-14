package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
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
	 * @param amount
	 * @param memo
	 * @param actionType
	 * @param accountType
	 * @param loanType
	 * @param bustType
     * @throws FssException
     */
	public void addChargeRecord(FundAccountEntity fromEntity, FundAccountEntity toEntiry, BigDecimal amount, String memo, int actionType, int accountType, String loanType, Integer bustType, FundSequenceEntity fundSequenceEntity) throws FssException{
		FssChargeRecordEntity entity=new FssChargeRecordEntity();
		Map<Integer,String> busiTypeMap=new HashMap<Integer,String>();
		busiTypeMap.put(0,"主账户");
		busiTypeMap.put(1,"借款账户");
		busiTypeMap.put(2,"线下出借账户");
		busiTypeMap.put(3,"线上出借账户");
		busiTypeMap.put(96,"应付账户");
		busiTypeMap.put(99,"冻结金账户");
		Map<String,String> platFormMap=new HashMap<String,String>();
		platFormMap.put("10040001","北京");
		platFormMap.put("10040002","天津");
		platFormMap.put("10040003","上海");
		platFormMap.put("10040099","其他城市");
		entity.setSeqNo(fundSequenceEntity.getOrderNo());
		entity.setFromAccNo(fromEntity.getAccountNo());
		entity.setFromCustNo(String.valueOf(fromEntity.getCustId()));
		entity.setFromCustName(fromEntity.getCustName());
		entity.setToAccNo(toEntiry.getAccountNo());
		entity.setToCustNo(String.valueOf(toEntiry.getCustId()));
		entity.setToCustName(toEntiry.getCustName());
		entity.setChargeType(Application.getInstance().getDictName(String.valueOf(accountType)));
		entity.setCharge(amount);
		entity.setBusiType(busiTypeMap.get(bustType));
		entity.setTradeType(String.valueOf(actionType));
		entity.setTradeState("10030002");
		entity.setTradeResult("10080002");
		entity.setTradeTime(new Date());
		entity.setPlatform(platFormMap.get(loanType));
		entity.setSumary(fundSequenceEntity.getSumary());
		entity.setRespCode("0000");
		entity.setRespMsg("成功");
		try {
			fssChargeRecordWriteMapper.insert(entity);
		}catch (Exception e){
			entity.setTradeState("10030003");
			entity.setTradeResult("10080010");
			entity.setRespCode("10080010");
			entity.setRespMsg("失败");
			fssChargeRecordWriteMapper.updateByPrimaryKey(entity);
			throw new FssException("91009804");
		}
	}










}
