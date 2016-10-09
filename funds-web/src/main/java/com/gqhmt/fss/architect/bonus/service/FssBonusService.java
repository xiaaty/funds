package com.gqhmt.fss.architect.bonus.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.bonus.BonusResponse;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.bonus.bean.BonusBatchBean;
import com.gqhmt.fss.architect.bonus.entity.FssBonusEntity;
import com.gqhmt.fss.architect.bonus.entity.FssBonusParentEntity;
import com.gqhmt.fss.architect.bonus.mapper.read.FssBonusParentReadMapper;
import com.gqhmt.fss.architect.bonus.mapper.read.FssBonusReadMapper;
import com.gqhmt.fss.architect.bonus.mapper.write.FssBonusParentWriteMapper;
import com.gqhmt.fss.architect.bonus.mapper.write.FssBonusWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.trade.impl.FundsTradeImpl;
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
public class FssBonusService {
	
	@Resource
	private FssBonusReadMapper fssBonusReadMapper;
	@Resource
	private FssBonusWriteMapper fssBonusWriteMapper;
	@Resource
	private FssBonusParentReadMapper fssBonusParentReadMapper;
	@Resource
	private FssBonusParentWriteMapper fssBonusParentWriteMapper;
	@Resource
	private TradeRecordService tradeRecordService;
	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private FundTradeService fundTradeService;
	@Resource
	private FundsTradeImpl fundsTradeImpl;
	@Resource
	private PaySuperByFuiou paySuperByFuiou;
	@Resource
	private FssBackplateService fssBackplateService;

	/**
	 * jhz
	 * 更新
	 * @param fssBonusEntity
     */
	public void update(FssBonusEntity fssBonusEntity){
		fssBonusEntity.setModifyTime(new Date());
		fssBonusWriteMapper.updateByPrimaryKey(fssBonusEntity);
	}
	/**
	 * jhz
	 * 批量插入
	 * @param bonusEntityList
     */
	public void insertList(List<FssBonusEntity> bonusEntityList){
		fssBonusWriteMapper.insertList(bonusEntityList);
	}
	/**
	 * 查询所有债权转让信息
	 * @param map
	 * @return
     */
	public List<FssBonusEntity> queryBondTransferList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("busiNo", map.get("busiNo"));
			map2.put("tradeState", map.get("tradeState"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		List<FssBonusEntity> bondlist= fssBonusReadMapper.queryBondTransferList(map2);
		return bondlist;
	}

	/**
	 * jhz
	 * 创建红包子表信息
	 * @param mchn
	 * @param seq_no
	 * @param trade_type
	 * @param cust_id
	 * @param busi_no
	 * @param amount
     * @return
     * @throws FssException
     */
	public FssBonusEntity createBonus(String mchn, String seq_no, String trade_type, Integer cust_id, Long busi_no,BigDecimal amount,Long parentId,Integer busiType) throws FssException{
		FssBonusEntity entity = new FssBonusEntity();
		entity.setTradeType(trade_type);
		entity.setParentId(parentId);
		entity.setBusiNo(busi_no);
		entity.setCustId(cust_id);
		entity.setSeqNo(seq_no);
		entity.setMchn(mchn);
		entity.setBusiType(busiType);
		entity.setAmount(amount);
		entity.setTradeState("10100002");
		entity.setTradeResult("10080001");
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
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
	public void updateBandTransfer(FssBonusEntity bondEntity, BigDecimal amt, String orderNo, String tradeState, String respCode) throws FssException{
		bondEntity.setAmount(amt);
//		bondEntity.setOrderNo(orderNo);
		bondEntity.setTradeState(tradeState);
		bondEntity.setModifyTime(new Date());
		try {
			fssBonusWriteMapper.updateByPrimaryKey(bondEntity);
		}catch (Exception e){
			throw new FssException("90099005");
		}
	}
	/**
	 * 红包
	 * @param trade_type
	 * @param cust_id
	 * @param busi_type
	 * @param amt
	 * @param busi_no
	 * @return
	 * @throws FssException
	 */
	public void compensation(String trade_type,Integer cust_id,Integer busi_type,BigDecimal amt,Long busi_no) throws FssException{
		//红包
		Long pubCustId = 4L;
		if(pubCustId.intValue() == cust_id.intValue()){
			throw  new FssException("90004017");
		}
		FundAccountEntity  fromEntity = fundAccountService.getFundAccount(pubCustId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);//对公账户
		FundAccountEntity  toEntity = fundsTradeImpl.getFundAccount(cust_id,busi_type);//个人账户
		//判断是从对公账户转入到个人账户还是从个人账户转入对公账户
		FundOrderEntity fundOrderEntity=null;
		try{
			this.hasEnoughBanlance(fromEntity,amt);
			Long lendNo=null;
			Long loanNo=null;
			if(busi_type==1){//借款账户
				loanNo= busi_no;//投标，满标，回款，等等对应借款合同编号
			}else if(busi_type==2){//线下出借账户
				lendNo=busi_no;
			}else if(busi_type==96){//应付账户（出借）
				lendNo=busi_no;
			}
			//第三方交易
			fundOrderEntity = paySuperByFuiou.transerer(fromEntity,toEntity,amt,3,busi_no,GlobalConstants.ORDER_TRANSFER,trade_type.substring(0,4),trade_type,lendNo==null?null:String.valueOf(lendNo),null,null,loanNo==null?null:String.valueOf(loanNo));
			//资金处理
			Integer fundType=1013;
			Integer actionType=3;
			tradeRecordService.transfer(fromEntity,toEntity,amt,fundType,fundOrderEntity,actionType,null,trade_type.substring(0,4),trade_type,lendNo==null?null:String.valueOf(lendNo),toEntity.getCustId(),null,Long.valueOf(cust_id),loanNo==null?null:String.valueOf(loanNo));
			//添加交易记录
			fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO,amt,fundType,"资金转出:"+amt+"元",BigDecimal.ZERO);
			fundTradeService.addFundTrade(toEntity,amt, BigDecimal.ZERO,fundType,"资金转入:"+amt+"元");
		}catch (Exception e){
			LogUtil.error(this.getClass(),"红包交易失败："+e.getMessage());
			throw new FssException("10080010");
		}
	}
	/**
	 * 判余额是否充足
	 * @param entity
	 * @param amount
	 * @throws CommandParmException
	 */
	private void hasEnoughBanlance(FundAccountEntity entity, BigDecimal amount) throws CommandParmException {
		BigDecimal bigDecimal = entity.getAmount();
		if (bigDecimal.compareTo(amount) < 0) {
			throw new CommandParmException("90004007");
		}
	}

	/**
	 * jhz
	 * 更新主表执行条数
	 * @param parentId
     */
	public void updateParentExuCount(Long parentId){
		fssBonusParentWriteMapper.updateExuCount(parentId);
	}

	/**
	 * jhz
	 * 执行更新
	 * @param fssBonusEntity
	 * @throws FssException
     */
	public void updateBonusExecuteState(FssBonusEntity fssBonusEntity) throws FssException{
		try {
			this.compensation(fssBonusEntity.getTradeType(), fssBonusEntity.getCustId(), fssBonusEntity.getBusiType(), fssBonusEntity.getAmount(), fssBonusEntity.getBusiNo());
			fssBonusEntity.setTradeState("10100005");
			fssBonusEntity.setTradeResult("10080002");
			this.update(fssBonusEntity);
		}catch (FssException e){
			LogUtil.error(this.getClass(),e.getMessage());
			fssBonusEntity.setTradeState("10100005");
			fssBonusEntity.setTradeResult("10080010");
			this.update(fssBonusEntity);
		}

		this.updateParentExuCount(fssBonusEntity.getParentId());
		FssBonusParentEntity parentEntity=this.getParentById(fssBonusEntity.getParentId());
		if(parentEntity.getCount()<=parentEntity.getExecuteCount()){
			FssBackplateEntity fssBackplateEntity=fssBackplateService.selectByMchnAndseqNo(parentEntity.getMchn(),parentEntity.getSeqNo());
			if (fssBackplateEntity!=null){
				fssBackplateService.updatebackplate(fssBackplateEntity);
			}else {
				//创建回盘信息,
				fssBackplateService.createFssBackplateEntity(parentEntity.getSeqNo(), parentEntity.getMchn(), parentEntity.getTradeType());
			}
		}
	}

	/**
	 * jhz
	 * 查询
	 * @param id
	 * @return
     */
	public FssBonusParentEntity getParentById(Long id){
		return  fssBonusParentReadMapper.selectByPrimaryKey(id);
	}

	/**
	 * jhz
	 * 创建主表信息
	 * @param mchn
	 * @param seqNo
	 * @param tradeType
     * @param count
     */
	public FssBonusParentEntity creatParentParentBonus(String mchn,String seqNo,String tradeType,int count) throws FssException{
		FssBonusParentEntity entity=new FssBonusParentEntity();
		entity.setCount(count);
		entity.setModifyTime(new Date());
		entity.setCreateTime(new Date());
		entity.setMchn(mchn);
		entity.setSeqNo(seqNo);
		entity.setTradeType(tradeType);
		fssBonusParentWriteMapper.insertSelective(entity);
		return entity;
	}

	/**
	 * jhz
	 * 查询所有处于待执行状态的红包列表
	 * @return
	 */
	public List<FssBonusEntity> queryBonusList(){
		return fssBonusReadMapper.queryBonusList();
	}

	/**
	 * jhz
	 * 获取回盘信息
	 * @param mchn
	 * @param seqNo
     * @return
     */
	public BonusResponse getBonusResponse(String mchn,String seqNo)throws FssException{
		BonusResponse response=new BonusResponse();
		List<BonusBatchBean> bonus_list= Lists.newArrayList();
		BonusBatchBean bean=null;
		FssBonusParentEntity parentEntity=fssBonusParentReadMapper.queryBonusParent(mchn,seqNo);
		if(parentEntity==null) {
			LogUtil.error(this.getClass(),"未查到主表信息");
			return null;
		}
		List<FssBonusEntity> list=fssBonusReadMapper.selectByParentId(parentEntity.getId());
		for (FssBonusEntity entity:list) {
			bean=new BonusBatchBean();
			bean.setSeq_no(entity.getSeqNo());
			if("10080002".equals(entity.getTradeResult())){
				bean.setResp_code("0000");
				bean.setResp_msg("success");
			}else {
				bean.setResp_code(entity.getTradeResult());
			}
			bonus_list.add(bean);
		}
		response.setSeq_no(parentEntity.getSeqNo());
		response.setMchn(parentEntity.getMchn());
		response.setTrade_type(parentEntity.getTradeType());
		response.setBonus_list(bonus_list);
		return response;
	}

	/**
	 * jhz
	 * 查询红包是否存在
	 * “0000”不存在
	 * “0001”存在
	 * @param seqNos
	 * @return
	 * @throws FssException
     */
	public Map<String,String> getResult(String seqNos)throws FssException{
		Map<String,String> map= Maps.newHashMap();
		String[] seqNo=seqNos.split(",");
		for (int i=0;i<seqNo.length;i++){
			if(fssBonusReadMapper.selectCountBySeqNo(seqNo[i])>0){
				map.put(seqNo[i],"0001");
			}else{
				map.put(seqNo[i],"0000");
			}
		}
		return map;
	}
}
