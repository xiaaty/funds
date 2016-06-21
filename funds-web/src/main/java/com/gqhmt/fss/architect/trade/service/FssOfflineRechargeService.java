package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.trade.entity.FssOfflineRechargeEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssOfflineRechargeReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssOfflineRechargeWriteMapper;
import com.gqhmt.util.LogUtil;
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
	@Resource
	private FssBackplateService fssBackplateService;

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
	public FssOfflineRechargeEntity createOfflineRecharge(String applyType,Long custId,String custName,String custType,BigDecimal amt,String trade_type,String seqNo,String mchn)throws FssException{
			FssOfflineRechargeEntity fssOfflineRechargeEntity=new FssOfflineRechargeEntity();
			fssOfflineRechargeEntity.setApplyNo(CommonUtil.getTradeApplyNo(trade_type));
			fssOfflineRechargeEntity.setApplyType(Integer.valueOf(applyType));
			fssOfflineRechargeEntity.setCustId(custId);
			fssOfflineRechargeEntity.setCustName(custName);
			fssOfflineRechargeEntity.setCustType(custType);
			fssOfflineRechargeEntity.setAmt(amt);
		    fssOfflineRechargeEntity.setTradeType(trade_type);
			fssOfflineRechargeEntity.setCreateTime(new Date());
			fssOfflineRechargeEntity.setModifyTime(new Date());
			fssOfflineRechargeEntity.setSeqNo(seqNo);
			fssOfflineRechargeEntity.setMchn(mchn);
			fssOfflineRechargeEntity.setChannelNo("9701");
		try{
			fssOfflineRechargeWriteMapper.insertSelective(fssOfflineRechargeEntity);
			return fssOfflineRechargeEntity;
		}catch (Exception e){
			throw new FssException("91009804");
		}

	}

	/**
	 * 修改线下充值记录
	 * fuiou返回成功修改线下充值记录
	 * @param id
	 * @param fy_acc_no
	 * @param fy_acc_nm
	 * @param fy_bank
	 * @param fy_bank_branch
	 * @param chg_cd
	 * @param chg_dt
	 * @param amt
     * @param orderNo
     * @throws FssException
     */
	public void updateSuccess(Long id,Object fy_acc_no,Object fy_acc_nm,Object fy_bank,Object fy_bank_branch,Object chg_cd,Object chg_dt,Object amt,String orderNo) throws FssException{
		FssOfflineRechargeEntity entity=fssOfflineRechargeReadMapper.selectByPrimaryKey(id);
		entity.setFyAccNo(fy_acc_no == null ? null:String.valueOf(fy_acc_no));
		entity.setFyAccNm(fy_acc_nm == null ? null:String.valueOf(fy_acc_nm));
		entity.setFyBank(fy_bank == null ? null:String.valueOf(fy_bank));
		entity.setFyBankBranch(fy_bank_branch == null ? null:String.valueOf(fy_bank_branch));
		entity.setChgCd(chg_cd == null ? null:String.valueOf(chg_cd));
		entity.setChgDt(chg_dt == null ? null:String.valueOf(chg_dt));
		entity.setAmt(amt == null ? null:new BigDecimal((String)amt));
		entity.setOrderNo(orderNo);
		entity.setResultState("10120002");//充值码获取成功，待客户充值
		try{
			fssOfflineRechargeWriteMapper.updateByPrimaryKey(entity);
		}catch (Exception e){
			throw new FssException("91009804");
		}
	}

	/**
	 * 失败修改充值状态
	 * @param id
	 * @param orderNo
	 * @throws FssException
     */
	public void updateFiled(Long id,String orderNo) throws FssException{
		FssOfflineRechargeEntity entity=fssOfflineRechargeReadMapper.selectByPrimaryKey(id);
		entity.setOrderNo(orderNo);
		entity.setResultState("10120004");//充值码获取失败
		try{
			fssOfflineRechargeWriteMapper.updateByPrimaryKey(entity);
		}catch (Exception e){
			throw new FssException("91009804");
		}
	}


	/**
	 * 线下充值成功回调
	 * @param mchn
	 * @param seqNo
	 * @return
	 * @throws FssException
     */
	public Response getOfflineRechargeResponse(String mchn, String seqNo)throws FssException {
		Response response=new Response();
		response=fssOfflineRechargeReadMapper.getOfflineRechargeResponse(mchn,seqNo);
		return response;
	}

	/**
	 * 根据商户代码和流水号查询充值信息
	 * @param mchntCd
	 * @param mchntTxnSsn
     * @return
     */
	public FssOfflineRechargeEntity getOfflineRechargeBy(String mchntCd,String mchntTxnSsn){
		return fssOfflineRechargeReadMapper.queryFssOfflineRecharge(mchntTxnSsn,mchntCd);
	}

	public FssOfflineRechargeEntity getOfflineRechargeById(Long id){
		return fssOfflineRechargeReadMapper.selectByPrimaryKey(id);
	}
	/**
	 * 修改线下充值结果，回盘处理
	 * @param id
	 * @param result
     */
	public void fuiouCallBack(Long id,String result) throws FssException{
		FssOfflineRechargeEntity entity = fssOfflineRechargeReadMapper.selectByPrimaryKey(id);
		//修改成功状态
		if("0000".equals(result)){
			entity.setResultState("10120003");
		}
		fssOfflineRechargeWriteMapper.updateByPrimaryKey(entity);
		//创建回盘信息
		fssBackplateService.createFssBackplateEntity(entity.getSeqNo(),entity.getMchn(),entity.getTradeType());
	}
}
