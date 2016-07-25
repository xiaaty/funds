package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.fss.architect.account.entity.FssFuiouAccountEntity;
import com.gqhmt.fss.architect.trade.entity.FssBondTransferEntity;
import com.gqhmt.fss.architect.trade.entity.FssOfflineRechargeEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssBondTransferReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssBondTransferWriteMapper;
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
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年6月29日
 * Description:
 * <p>债权转让
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年6月29日     1.0     1.0 Version
 */
@Service
public class FssBondTransferService {
	
	@Resource
	private FssBondTransferReadMapper fssBondTransferReadMapper;
	@Resource
	private FssBondTransferWriteMapper fssBondTransferWriteMapper;

	/**
	 * 查询所有债权转让信息
	 * @param map
	 * @return
     */
	public List<FssBondTransferEntity> queryBondTransferList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("busiNo", map.get("busiNo"));
			map2.put("tradeState", map.get("tradeState"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		List<FssBondTransferEntity> bondlist=fssBondTransferReadMapper.queryBondTransferList(map2);
		return bondlist;
	}

	/**
	 *添加债权转让记录
	 * @param mchn
	 * @param seq_no
	 * @param trade_type
	 * @param bid_id
	 * @param busi_bid_no
	 * @param cust_no
	 * @param o_cust_no
	 * @param busi_no
     * @return
     * @throws FssException
     */
	public FssBondTransferEntity createBondTransferInfo(String mchn,String seq_no,String trade_type,String bid_id,String busi_bid_no,String cust_no,String o_cust_no,String busi_no,String custName,String accNo,Integer acc_type,Integer to_acc_type,String tender_no,String o_tender_no,String o_busi_no) throws FssException{
		FssBondTransferEntity entity = GenerateBeanUtil.GenerateClassInstance(FssBondTransferEntity.class);
		entity.setTradeType(trade_type);
		entity.setBidId(bid_id);
		entity.setBusiNo(busi_no);
		entity.setCustId(cust_no);
		entity.setoCustNo(o_cust_no);
		entity.setCustNo(cust_no);
		entity.setCustName(custName);
		entity.setAccNo(accNo);
		entity.setBusiBidNo(busi_bid_no);
		entity.setSeqNo(seq_no);
		entity.setMchn(mchn);
		entity.setTradeState("10080001");
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
		entity.setAccType(String.valueOf(acc_type));
		entity.setToAccType(String.valueOf(to_acc_type));
		entity.setTenderNo(tender_no);
		entity.setTenderNo(o_tender_no);
		entity.setoBusiNo(o_busi_no);
		try {
			fssBondTransferWriteMapper.insertSelective(entity);
		}catch (Exception e){
			throw new FssException("90099005");
		}
		return entity;
	}

	/**
	 * 转账成功后修改记账信息
	 * @param bondEntity
	 * @param amt
	 * @param orderNo
	 * @param tradeState
	 * @throws FssException
     */
	public void updateBandTransfer(FssBondTransferEntity bondEntity,BigDecimal amt,String orderNo,String tradeState,String respCode) throws FssException{
		bondEntity.setAmount(amt);
		bondEntity.setOrderNo(orderNo!=null?orderNo:null);
		bondEntity.setTradeState(tradeState);
		bondEntity.setModifyTime(new Date());
		bondEntity.setRespCode(respCode);
		try {
			fssBondTransferWriteMapper.updateByPrimaryKey(bondEntity);
		}catch (Exception e){
			throw new FssException("90099005");
		}
	}
}
