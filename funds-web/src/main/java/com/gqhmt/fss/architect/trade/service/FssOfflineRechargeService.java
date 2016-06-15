package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.trade.entity.FssOfflineRechargeEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssOfflineRechargeReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssOfflineRechargeWriteMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
 * Create at:   2016年6月8日
 * Description:
 * <p>线下充值
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月8日     1.0     1.0 Version
 */
@Service
public class FssOfflineRechargeService {
	
	@Resource
	private FssOfflineRechargeReadMapper fssOfflineRechargeReadMapper;
	@Resource
	private FssOfflineRechargeWriteMapper fssOfflineRechargeWriteMapper;

	/**
	 * 查询线下充值记录
	 * @param map
	 * @return
     */
	public List<FssOfflineRechargeEntity> queryFssOfflineRechargeList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("applyNo", map.get("applyNo"));
			map2.put("busiNo", map.get("busiNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		List<FssOfflineRechargeEntity> offlinerechargelist=fssOfflineRechargeReadMapper.queryFssOfflineRecharegList(map2);
		return offlinerechargelist;
	}

	/**
	 * 保存线下充值申请信息
	 * @throws FssException
     */
	public FssOfflineRechargeEntity createOfflineRecharge(String applyNo,String applyType,Long custId,String custName,String custType,String fyAccNo,String orderNo,BigDecimal amt,String applyState,String tradeState,
									  String busiNo,String busiType,String seqNo,String mchn,String channelNo,String custNo,String chgCd,String chgDt,String fyAccNm,String fyBank,String fyBankBranch,String descCode)throws FssException{
			FssOfflineRechargeEntity fssOfflineRechargeEntity=new FssOfflineRechargeEntity();
			fssOfflineRechargeEntity.setApplyNo(applyNo);
			fssOfflineRechargeEntity.setApplyType(Integer.valueOf(applyType));
			fssOfflineRechargeEntity.setCustId(custId);
			fssOfflineRechargeEntity.setCustName(custName);
			fssOfflineRechargeEntity.setCustType(custType);
			fssOfflineRechargeEntity.setFyAccNo(fyAccNo);
			fssOfflineRechargeEntity.setOrderNo(orderNo);
			fssOfflineRechargeEntity.setAmt(amt);
			fssOfflineRechargeEntity.setApplyState(applyState);
			fssOfflineRechargeEntity.setTradeState(tradeState);
			fssOfflineRechargeEntity.setCreateTime(new Date());
			fssOfflineRechargeEntity.setModifyTime(new Date());
			fssOfflineRechargeEntity.setBusiNo(busiNo);
			fssOfflineRechargeEntity.setBusiType(busiNo);
			fssOfflineRechargeEntity.setSeqNo(seqNo);
			fssOfflineRechargeEntity.setMchn(mchn);
			fssOfflineRechargeEntity.setChannelNo(channelNo);
			fssOfflineRechargeEntity.setCustNo(custNo);
			fssOfflineRechargeEntity.setChgCd(chgCd);
			fssOfflineRechargeEntity.setChgDt(chgDt);
			fssOfflineRechargeEntity.setFyAccNm(fyAccNm);
			fssOfflineRechargeEntity.setFyBank(fyBank);
			fssOfflineRechargeEntity.setFyBankBranch(fyBankBranch);
			fssOfflineRechargeEntity.setDescCode(descCode);
		try{
			fssOfflineRechargeWriteMapper.insertSelective(fssOfflineRechargeEntity);
			return fssOfflineRechargeEntity;
		}catch (Exception e){
			throw new FssException("91009804");
		}

	}

	/**
	 * 修改线下充值记录
	 * @param entity
     */
	public void update(FssOfflineRechargeEntity entity) throws FssException{
		try{
		fssOfflineRechargeWriteMapper.updateByPrimaryKey(entity);
		}catch (Exception e){
			throw new FssException("91009804");
		}
	}








}
