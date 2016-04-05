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
import com.gqhmt.pay.service.trade.impl.FundsTradeImpl;
import com.gqhmt.sys.service.BankDealamountLimitService;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private BankDealamountLimitService bankDealamountLimitService;
	
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
	@Resource
	private FundsTradeImpl fundsTradeImpl;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：通过账户号和交易类型（充值1，提现2）得到客户绑定的银行限额
	 * @throws FssException 
	 */
	public BigDecimal  getLimit(String accNo,int type) throws FssException{
		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(accNo);
		List<BankCardInfoEntity> queryInvestmentByCustId = bankCardInfoService.queryInvestmentByCustId(fssAccountByAccNo.getCustId().intValue());
		return getLimitAmount(queryInvestmentByCustId.get(0).getParentBankId(),type);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月28日
	 * function：得到银行限额
	 * @throws FssException 
	 */
	public BigDecimal getLimitAmount(String bankCode,int type) throws FssException{
		return Application.getInstance().getBankDealamountLimit(bankCode+type);
		
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：给交易记录表添加数据
	 */
	public void insertTradeRecord(int type) throws FssException{
		//查找处于未交易的交易申请"10090002"
		List<FssTradeApplyEntity> tradeAppliesByTradeStatus = fssTradeApplyService.getTradeAppliesByTradeStatus("10090002");
		for (FssTradeApplyEntity fssTradeApplyEntity : tradeAppliesByTradeStatus) {
			insertRecord(fssTradeApplyEntity,type);
			fssTradeApplyEntity.setTradeState("10090004");
			fssTradeApplyService.updateTradeApply(fssTradeApplyEntity);
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
	public List<FssTradeRecordEntity> findNotExecuteRecodes(){
		//查询出处于划扣中的申请
			List<FssTradeRecordEntity> tradeRecordList = fssTradeRecordReadMapper.selectByTradeState(98070001);
			return tradeRecordList;
	}

	/**
	 * 修改执行状态
	 * @param fssTradeRecordEntity		实体备案
	 * @param state	
	 * TradeResult: 98060001交易成功,98060003交易失败					
     */
	public void  updateTradeRecordExecuteState(FssTradeRecordEntity fssTradeRecordEntity,int state,String errCode) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		DateFormat df3 = DateFormat.getTimeInstance();//只显示出时分秒
	    String time=df3.format(date);
		fssTradeRecordEntity.setTradeResult((state == 1?98060001:98060003));//(state == 1?"":"")
		fssTradeRecordEntity.setTradeState(98070002);//修改交易状态为已执行
		fssTradeRecordEntity.setSumary(errCode);
		fssTradeRecordEntity.setModifyTime(new Date());
		fssTradeRecordEntity.setTradeDate(sdf.format(new Date()));
		fssTradeRecordEntity.setTradeTime(time.replace(":",""));
		fssTradeRecordWriteMapper.updateByPrimaryKey(fssTradeRecordEntity);
		//Apply 执行数量更新
		try {
			fssTradeApplyService.updateExecuteCount(fssTradeRecordEntity);
		} catch (FssException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：交易成功修改借款人放宽交易状态
	 */
//	public void updateStatus(FssTradeApplyEntity tradeApply){
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
//	}
	
	/**
	 * 根据申请单好查询抵押权人详细列表
	 * @param map
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
	public void  moneySplit(FssTradeApplyEntity fssTradeApplyEntity) throws FssException{
			FssTradeRecordEntity tradeRecordEntity=null;
			//限额
			BigDecimal limitAmount =this.getBankLimit(fssTradeApplyEntity.getApplyType(),String.valueOf(fssTradeApplyEntity.getCustId()));//根据cust_id 查询银行限额
			tradeRecordEntity = this.creatTradeRecordEntity(fssTradeApplyEntity);
			int moneySplit = this.moneySplit(tradeRecordEntity, limitAmount, fssTradeApplyEntity.getTradeAmount());
			//更新申请表该条数据拆分总条数
			fssTradeApplyEntity.setCount(moneySplit);
			fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
			fssTradeApplyEntity.setMchnParent(Application.getInstance().getParentMchn(fssTradeApplyEntity.getMchnChild()));
			fssTradeApplyService.updateTradeApply(fssTradeApplyEntity);
			/*if(fssTradeApplyEntity.getApplyType()==1104){//	提现申请处理完成后冻结金额
				fundsTradeImpl.froze(fssTradeApplyEntity.getCustId(),Integer.valueOf(fssTradeApplyEntity.getBusiType()),fssTradeApplyEntity.getTradeAmount());
			}*/

	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：添加进交易记录,并进行金额拆分
	 * @throws FssException 
	 */
	public void  insertRecord(FssTradeApplyEntity fssTradeApplyEntity,int type) throws FssException{
		FssTradeRecordEntity tradeRecordEntity=null;
		tradeRecordEntity=this.creatTradeRecordEntity(fssTradeApplyEntity);
		//交易额
		BigDecimal tradeAmount = fssTradeApplyEntity.getTradeAmount();
		//限额
		BigDecimal limitAmount =this.getLimit(fssTradeApplyEntity.getAccNo(),type);
		int moneySplit = this.moneySplit(tradeRecordEntity, limitAmount, tradeAmount);
		//更新申请表该条数据拆分总条数
		fssTradeApplyEntity.setCount(moneySplit);
		fssTradeApplyService.updateTradeApply(fssTradeApplyEntity);
		
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月28日
	 * function：根据交易申请创建交易记录对象
	 */
	public FssTradeRecordEntity creatTradeRecordEntity(FssTradeApplyEntity fssTradeApplyEntity) throws FssException{
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
		tradeRecordEntity.setMchnParent(Application.getInstance().getParentMchn(fssTradeApplyEntity.getMchnChild()));
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
		return tradeRecordEntity;
	}
	
	
	/**
	 * 根据cust_id查询银行限额
	 * @param accNo
	 * @return
	 * @throws FssException
	 */
	public BigDecimal  getBankLimit(Integer applyType,String custId) throws FssException{
		BankCardInfoEntity bankCardInfo = bankCardInfoService.getInvestmentByCustId(Integer.valueOf(custId));
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
	public  int moneySplit(FssTradeRecordEntity tradeRecordEntity,BigDecimal limitAmount,BigDecimal tradeAmount) throws FssException{
		int count=0;
		//金额是否超过银行代付单笔上限
		//否
		if(tradeAmount.compareTo(limitAmount)<=0){
		tradeRecordEntity.setAmount(tradeAmount);
		fssTradeRecordWriteMapper.insert(tradeRecordEntity);
		count=1;
		}else {
			//金额超过银行代付单笔上限
			BigDecimal bg[] = tradeAmount.divideAndRemainder(limitAmount);
			int splitCount = bg[0].intValue();
			BigDecimal lastamount = bg[1];
			//判断是否除尽
			if (lastamount.compareTo(BigDecimal.ZERO) > 0) {
				splitCount = splitCount + 1;
			} else if(lastamount.compareTo(BigDecimal.ZERO) ==0){
				lastamount=limitAmount;
			}
			count=splitCount;
			//拆分处理
			for (int j=0 ; j < splitCount; j++) {
				if (j != (splitCount-1) ) {
					tradeRecordEntity.setId(null);
					tradeRecordEntity.setAmount(limitAmount);
					fssTradeRecordWriteMapper.insert(tradeRecordEntity);
				}else{
					tradeRecordEntity.setId(null);
					tradeRecordEntity.setAmount(lastamount);
					fssTradeRecordWriteMapper.insert(tradeRecordEntity);
				}
			}
		}
		return count;
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
	 * time:2016年4月1日
	 * function：根据申请编号得到该批次实际交易总金额
	 */
	public BigDecimal getSuccessAmt(String applyNo) {
		// TODO Auto-generated method stub
		return fssTradeRecordReadMapper.getSuccessAmt(applyNo);
	}
	
	
}
