package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssTradeRecordWriteMapper;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
	private FundAccountService fundAccountService;
	
	@Resource
	private FssRepaymentService fssRepaymentService;
	
	@Resource
	private FssAccountService fssAccountService;
	
	@Resource
	private BankCardInfoService bankCardInfoService;
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：通过账户号得到客户绑定的银行限额
	 * @throws FssException 
	 */
	public BigDecimal  getBankCode(String accNo) throws FssException{
		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(accNo);
		List<BankCardInfoEntity> queryInvestmentByCustId = bankCardInfoService.queryInvestmentByCustId(fssAccountByAccNo.getCustId());
		Application instance = Application.getInstance();
		BigDecimal bankDealamountLimit = instance.getBankDealamountLimit(queryInvestmentByCustId.get(0).getCardIndex());
		return bankDealamountLimit;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：给交易记录表添加数据
	 */
	public void insertTradeRecord() throws FssException{
		//查找处于划扣中的交易申请
		List<FssTradeApplyEntity> tradeAppliesByTradeStatus = fssTradeApplyService.getTradeAppliesByTradeStatus("10090002");
		for (FssTradeApplyEntity fssTradeApplyEntity : tradeAppliesByTradeStatus) {
			insertRecord(fssTradeApplyEntity);
		}
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：添加进交易记录,并进行金额拆分
	 * @throws FssException 
	 */
	public void  insertRecord(FssTradeApplyEntity fssTradeApplyEntity) throws FssException{
		FssTradeRecordEntity tradeRecordEntity=null;
			tradeRecordEntity=new FssTradeRecordEntity();
			String accNo = fssTradeApplyEntity.getAccNo();
			//交易额
			BigDecimal realTradeAmount = fssTradeApplyEntity.getRealTradeAmount();
			//限额
			BigDecimal limitAmount =this.getBankCode(accNo);
			//金额是否超过银行代付单笔上限
			//否
			if(realTradeAmount .compareTo(limitAmount)<=0){
			tradeRecordEntity.setAmount(realTradeAmount);
			tradeRecordEntity.setAccNo(accNo);
			tradeRecordEntity.setApplyNo(fssTradeApplyEntity.getApplyNo());
			tradeRecordEntity.setCreateTime(new Date());
			fssTradeRecordWriteMapper.insert(tradeRecordEntity);
			}else {
				//金额超过银行代付单笔上限
				BigDecimal bg[] = realTradeAmount.divideAndRemainder(limitAmount);
				int splitCount = bg[0].intValue();
				BigDecimal lastamount = bg[1];
				//判断是否除尽
				if (lastamount.compareTo(BigDecimal.ZERO) > 0) {
					splitCount = splitCount + 1;
				} 
				//拆分处理
				for (int j=0 ; j < splitCount; j++) {
					if (j != (splitCount-1) ) {
						tradeRecordEntity.setAmount(limitAmount);
						tradeRecordEntity.setAccNo(accNo);
						tradeRecordEntity.setApplyNo(fssTradeApplyEntity.getApplyNo());
						tradeRecordEntity.setCreateTime(new Date());
						fssTradeRecordWriteMapper.insert(tradeRecordEntity);
					}else{
						tradeRecordEntity.setAmount(lastamount);
						tradeRecordEntity.setAccNo(accNo);
						tradeRecordEntity.setApplyNo(fssTradeApplyEntity.getApplyNo());
						tradeRecordEntity.setCreateTime(new Date());
						fssTradeRecordWriteMapper.insert(tradeRecordEntity);
					}
				}
			}
		}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：处理交易
	 */
	/*public void handleTrade(){
		//查询出处于划扣中的申请
		List<FssTradeApplyEntity> tradeAppliesByTradeStatus =fssTradeApplyService.getTradeAppliesByTradeStatus("10090002");
			for (FssTradeApplyEntity tradeApply : tradeAppliesByTradeStatus) {
				FssTradeRecordEntity record=new FssTradeRecordEntity();
				record.setApplyNo(tradeApply.getApplyNo());
				List<FssTradeRecordEntity> tradeRecordList = fssTradeRecordReadMapper.select(record);
				//处理交易
				for (FssTradeRecordEntity fssTradeRecordEntity : tradeRecordList) {
		//			交易成功	
					
					//修改交易状态
					fssTradeRecordEntity.setTradeState(10030002);
					fssTradeRecordEntity.setModifyTime(new Date());
					fssTradeRecordWriteMapper.updateByPrimaryKey(fssTradeRecordEntity);
				}
				//根据applyNo查找交易申请记录，并修改交易状态
				tradeApply.setTradeState("10090003");
				tradeApply.setModifyTime(new Date());
				fssTradeApplyWriteMapper.updateByPrimaryKey(tradeApply);
//				交易成功修改借款人放宽交易状态
				FssRepaymentEntity fssRepaymentEntity = fssRepaymentService.queryRepaymentById(Long.parseLong(tradeApply.getCustNo()));
				fssRepaymentEntity.setState("10090003");
				fssRepaymentEntity.setMotifyTime(new Date());
				fssRepaymentService.updateRepaymentEntity(fssRepaymentEntity);
				
			}
			
			
	}*/
	/**
	 * 
	 * author:jhz
	 * time:2016年3月19日
	 * function：批量代扣
	 */
	public List<FssTradeRecordEntity> batchWithHolding(){
		//查询出处于划扣中的申请
			FssTradeRecordEntity record=new FssTradeRecordEntity();
			record.setTradeType(10030001);
			List<FssTradeRecordEntity> tradeRecordList = fssTradeRecordReadMapper.select(record);
			return tradeRecordList;
	}

	/**
	 * 修改执行状态
	 * @param fssTradeRecordEntity		实体备案
	 * @param state						1交易成功,2交易失败
     */
	public void  updateTradeRecordExecuteState(FssTradeRecordEntity fssTradeRecordEntity,int state){
		fssTradeRecordEntity.setTradeResult(0);//(state == 1?"":"")
		fssTradeRecordEntity.setTradeState(0);//修改交易状态为已执行

		//Apply 执行数量更新
		fssTradeApplyService.updateExecuteCount(fssTradeRecordEntity.getApplyNo());

	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：交易成功修改借款人放宽交易状态
	 */
	public void updateStatus(FssTradeApplyEntity tradeApply){
//		FssRepaymentEntity fssRepaymentEntity = fssRepaymentService.queryRepaymentById(Long.parseLong(tradeApply.getCustNo()));
//		fssRepaymentEntity.setState("10090003");
//		fssRepaymentEntity.setMotifyTime(new Date());
//		fssRepaymentService.updateRepaymentEntity(fssRepaymentEntity);
		//查询该批次交易成功数
		
//		queryRepaymentParentById.setMotifyTime(new Date());
//		int successCount = fssRepaymentService.getSuccessCount(id);
//		if(successCount==queryFssRepaymentEntity.size()){
//			//成功
//			queryRepaymentParentById.setState("10080002");
//			fssRepaymentService.updateRepaymentParent(queryRepaymentParentById);
//		}else if(successCount==0){
//			//失败
//			queryRepaymentParentById.setState("10080010");
//			fssRepaymentService.updateRepaymentParent(queryRepaymentParentById);
//		}else{
//			//部分成功
//			queryRepaymentParentById.setState("10080003");
//			fssRepaymentService.updateRepaymentParent(queryRepaymentParentById);
//			
//		}
	}
	
}
