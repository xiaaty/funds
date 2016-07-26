package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssTradeRecordWriteMapper;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月18日
 * Description:
 * <p>交易记录service
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月18日  jhz      1.0     1.0 Version
 */
@Service
public class FssTradeRecordService {
	

	@Resource
	private FssTradeRecordWriteMapper fssTradeRecordWriteMapper;
	@Resource
	private FssTradeRecordReadMapper fssTradeRecordReadMapper;

	@Resource
	private BankCardInfoService bankCardInfoService;
	
	@Resource
	private FssTradeApplyService fssTradeApplyService;

	@Resource
	private CustomerInfoService customerInfoService;

	/**
	 * 修改执行状态
	 * @param fssTradeRecordEntity		实体备案
	 * @param state	
	 * TradeResult: 98060001交易成功,98060003交易失败					
     */
	public void  updateTradeRecordExecuteState(FssTradeRecordEntity fssTradeRecordEntity,int state,String respCode) throws  FssException {

		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String time=sdf.format(date);
		Integer tradeResult=null;
		if(state == 1){
			tradeResult=98060001;//成功
		}else if(state==3){
			tradeResult=98060009;//中断
		}else{
			tradeResult=98060003;//失败
		}
		fssTradeRecordEntity.setTradeResult(tradeResult);
		fssTradeRecordEntity.setTradeState(98070002);//修改交易状态为已执行
		fssTradeRecordEntity.setModifyTime(new Date());
		fssTradeRecordEntity.setTradeDate(time.substring(0,8));
		fssTradeRecordEntity.setTradeTime(time.substring(8));
		fssTradeRecordEntity.setRespCode(respCode);
		fssTradeRecordWriteMapper.updateByPrimaryKey(fssTradeRecordEntity);
		//Apply 执行数量更新
		fssTradeApplyService.updateExecuteCount(fssTradeRecordEntity);
	}

	/**
	 * 根据申请单好查询抵押权人详细列表
	 * @param 
	 * @return
	 */
	public  List<FssTradeRecordEntity> queryFssTradeRecordList(String applyNo,Integer tradeState){
		FssTradeRecordEntity fssTradeRecordEntity=new FssTradeRecordEntity();
		fssTradeRecordEntity.setApplyNo(applyNo);
		fssTradeRecordEntity.setTradeState(tradeState);
		List<FssTradeRecordEntity> tradeRecordList=fssTradeRecordReadMapper.select(fssTradeRecordEntity);
		return tradeRecordList;
	}
	
	/**
	 * 提现金额拆分
	 * @param fssTradeApplyEntity
	 * @throws FssException
	 * 柯禹来
	 */
	public List<FssTradeRecordEntity>  moneySplit(FssTradeApplyEntity fssTradeApplyEntity) throws FssException{
			//限额
			BigDecimal limitAmount =this.getBankLimit(fssTradeApplyEntity.getApplyType(),fssTradeApplyEntity.getCustId());//根据cust_id 查询银行限额

			List<FssTradeRecordEntity> recordEntityList= this.moneySplit(fssTradeApplyEntity, limitAmount);
			//更新申请表该条数据拆分总条数
			fssTradeApplyEntity.setCount(recordEntityList.size());
			fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
			if("01".equals(fssTradeApplyEntity.getMchnChild())){
				fssTradeApplyEntity.setMchnParent("01");
			}else{
				fssTradeApplyEntity.setMchnParent(Application.getInstance().getParentMchn(fssTradeApplyEntity.getMchnChild()));
			}

			fssTradeApplyService.updateTradeApply(fssTradeApplyEntity);
			return recordEntityList;
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月28日
	 * function：根据交易申请创建交易记录对象
	 */
	public FssTradeRecordEntity creatTradeRecordEntity(FssTradeApplyEntity fssTradeApplyEntity,BigDecimal tradeAmount) throws FssException{
		FssTradeRecordEntity tradeRecordEntity=new FssTradeRecordEntity();
		int settleType=0;
		if(fssTradeApplyEntity.getBespokedate()!=null){//结算类型0：T+0,1：T+1
			settleType=fssTradeApplyService.compare_date(fssTradeApplyEntity.getBespokedate());
			tradeRecordEntity.setSettleType(settleType);
		}
		tradeRecordEntity.setAccNo(fssTradeApplyEntity.getAccNo());
		tradeRecordEntity.setTradeType(fssTradeApplyEntity.getApplyType());
		tradeRecordEntity.setTradeTypeChild(Integer.valueOf(fssTradeApplyEntity.getBusiType()));
		tradeRecordEntity.setMchnChild(fssTradeApplyEntity.getMchnChild());
		tradeRecordEntity.setAmount(tradeAmount);
		if("01".equals(fssTradeApplyEntity.getMchnChild())){
			tradeRecordEntity.setMchnParent("01");
		}else{
			tradeRecordEntity.setMchnParent(Application.getInstance().getParentMchn(fssTradeApplyEntity.getMchnChild()));
		}
		tradeRecordEntity.setCustNo(fssTradeApplyEntity.getCustNo());
		tradeRecordEntity.setTradeDate("0");
		tradeRecordEntity.setTradeTime("0");
		tradeRecordEntity.setBespokeDate(fssTradeApplyEntity.getBespokedate());
		tradeRecordEntity.setApplyNo(fssTradeApplyEntity.getApplyNo());
		tradeRecordEntity.setTradeState(98070001);
		tradeRecordEntity.setChannelNo(fssTradeApplyEntity.getChannelNo());
		tradeRecordEntity.setCreateTime(new Date());
		tradeRecordEntity.setModifyTime(new Date());
		tradeRecordEntity.setChannelNo(fssTradeApplyEntity.getChannelNo());
		tradeRecordEntity.setCustId(fssTradeApplyEntity.getCustId());
		tradeRecordEntity.setCustType(fssTradeApplyEntity.getCustType());
		return tradeRecordEntity;
	}
	
	
	/**
	 * 根据cust_id查询银行限额
	 * @return
	 * @throws FssException
	 */
	public BigDecimal  getBankLimit(Integer applyType,Long custId) throws FssException{
		CustomerInfoEntity customerInfoEntity=customerInfoService.getCustomerById(custId);
		BankCardInfoEntity bankCardInfo=null;
		if(null!=customerInfoEntity.getBankId()&&!"".equals(customerInfoEntity.getBankId())) {
			bankCardInfo = bankCardInfoService.getBankCardInfoById(customerInfoEntity.getBankId());
		}else {
		List<BankCardInfoEntity> bankCardInfos = bankCardInfoService.getBankCardByCustNo(custId.toString());
			if (CollectionUtils.isNotEmpty(bankCardInfos)){
				bankCardInfo=bankCardInfos.get(0);
			}
		}
		if(bankCardInfo==null){
			throw new FssException("90004027");
		}
		int type=0;
		if(applyType==1103){//充值
			 type=1;
		}else{   //提现
			 type=2;
		}
		Application instance = Application.getInstance();
		BigDecimal bankDealamountLimit = instance.getBankDealamountLimit(bankCardInfo.getParentBankId()+type);
		return bankDealamountLimit;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月28日
	 * function：拆分金额
	 * limitAmount:限额
	 * realTradeAmount:实际交易额
	 */
	public  List<FssTradeRecordEntity> moneySplit(FssTradeApplyEntity tradeApplyEntity,BigDecimal limitAmount) throws FssException {
		List<FssTradeRecordEntity> recordEntityList = new ArrayList<>();

		BigDecimal bg[] = tradeApplyEntity.getAuditAmount().divideAndRemainder(limitAmount);
		int splitCount = bg[0].intValue();
		BigDecimal lastamount = bg[1];

		for (int i = 0; i < splitCount; i++) {
			FssTradeRecordEntity tradeRecordEntity = this.creatTradeRecordEntity(tradeApplyEntity, limitAmount);
			fssTradeRecordWriteMapper.insert(tradeRecordEntity);
			recordEntityList.add(tradeRecordEntity);
		}

		if (lastamount.compareTo(BigDecimal.ZERO) > 0) {
			FssTradeRecordEntity tradeRecordEntity = this.creatTradeRecordEntity(tradeApplyEntity, lastamount);
			fssTradeRecordWriteMapper.insert(tradeRecordEntity);
			recordEntityList.add(0,tradeRecordEntity);
		}


		return recordEntityList;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月1日
	 * function：根据申请编号得到该批次成功条数
	 */
	public int getSuccessCount(String applyNo){
		
		return fssTradeRecordReadMapper.getSuccessCount(applyNo);
		
	}
	/**
	 *
	 * author:jhz
	 * time:2016年6月16日
	 * function：根据申请编号得到该批次条数
	 */
	public int getCountByApplyNo(String applyNo){

		return fssTradeRecordReadMapper.getCountByApplyNo(applyNo);

	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月1日
	 * function：根据申请编号得到该批次实际交易总金额
	 */
	public BigDecimal getSuccessAmt(String applyNo) {
		// TODO Auto-generated method stub
		return fssTradeRecordReadMapper.getSuccessAmt(applyNo);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年5月4日
	 * function：通过申请编号得到交易记录表信息
	 */
	public List<FssTradeRecordEntity> getByApplyNo(String applyNo) throws FssException{
		return fssTradeRecordReadMapper.getByApplyNo(applyNo);
		
	}
	/**
	 * author:jhz
	 * time:2016年6月20日
	 * function：修改申请状态
	 */
	public  void updateTradeRecord(Long id, String status) throws  FssException{
		FssTradeRecordEntity entity=this.selectTradeApplyById(id);
		entity.setModifyTime(new Date());
		if ("1".equals(status)) {
			entity.setTradeState(98060003);
		} else if ("0".equals(status)) {
//			List<FssTradeRecordEntity> list=fssTradeRecordService.getByApplyNo(entity.getApplyNo());
			entity.setTradeResult(98060001);
		}
		fssTradeRecordWriteMapper.updateByPrimaryKey(entity);
		fssTradeApplyService.checkExecuteCount(entity.getApplyNo());
	}
	/**
	 * author:jhz
	 * time:2016年6月20日
	 * function：根据id查询申请对象
	 */
	public  FssTradeRecordEntity selectTradeApplyById(Long id){
		return  fssTradeRecordReadMapper.selectByPrimaryKey(id);
	}


}
