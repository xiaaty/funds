package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentParentEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeApplyReadMapper;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssTradeApplyWriteMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssTradeRecordWriteMapper;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gqhmt.util.CommonUtil;


/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:29
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Service
public class FssTradeApplyService {
	
	@Resource
    private FssTradeApplyWriteMapper fssTradeApplyWriteMapper;
	
	@Resource
	private FssTradeApplyReadMapper fssTradeApplyReadMapper;
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
	/**
	 * 创建借款人提现申请
	 * @param fssTradeApplyEntity
	 * @throws FssException
	 */
	public void createTradeApply(FssTradeApplyEntity fssTradeApplyEntity) throws FssException{
		fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
	}
	
	/**
	 * 借款人提现完成通知借款系统
	 * @param seqNo
	 * @param mchn
	 * @return
	 */
	public FssTradeApplyEntity getTradeApplyByParam(String seqNo,String mchn) throws FssException{
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		fssTradeApplyEntity.setSeqNo(seqNo);
		fssTradeApplyEntity.setMchnChild(mchn);
		fssTradeApplyReadMapper.selectOne(fssTradeApplyEntity);
		return fssTradeApplyEntity;
	}
	
	
	public FssTradeApplyEntity createTreadeApplyEntity(FssAccountEntity fssAccountEntity,LoanWithDrawApplyDto wthDrawApplyDto) throws FssException {
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		//创建提现申请信息
			fssTradeApplyEntity.setApplyNo(fundAccountService.getAccountNo());//时间+随机数
			fssTradeApplyEntity.setApplyType(Integer.valueOf(wthDrawApplyDto.getTrade_type()));
			fssTradeApplyEntity.setCustNo(fssAccountEntity.getCustNo());
			fssTradeApplyEntity.setUserNo(fssAccountEntity.getUserNo());
			fssTradeApplyEntity.setBusinessNo(fssAccountEntity.getBusiNo());
			fssTradeApplyEntity.setBusiType("0");
			fssTradeApplyEntity.setAccNo(wthDrawApplyDto.getAcc_no());
			fssTradeApplyEntity.setTradeAmount(wthDrawApplyDto.getContract_amt());
			fssTradeApplyEntity.setRealTradeAmount(wthDrawApplyDto.getPay_amt());
			fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
			fssTradeApplyEntity.setTradeState(wthDrawApplyDto.getTrade_type());
			fssTradeApplyEntity.setApplyState("0");
			fssTradeApplyEntity.setMchnParent(Application.getInstance().getParentMchn(wthDrawApplyDto.getMchn()));
			fssTradeApplyEntity.setMchnChild(wthDrawApplyDto.getMchn());
			fssTradeApplyEntity.setCreateTime((new Timestamp(new Date().getTime())));
			fssTradeApplyEntity.setModifyTime((new Timestamp(new Date().getTime())));
			fssTradeApplyEntity.setSeqNo(wthDrawApplyDto.getSeq_no());
			fssTradeApplyEntity.setBespokedate(new Date());//todo
			fssTradeApplyEntity.setContractId(wthDrawApplyDto.getContract_id());
		
		return fssTradeApplyEntity;
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人提现
	 */
	public List<FssTradeApplyEntity> getBorrowWithDraw(Map map) {
		return fssTradeApplyReadMapper.getBorrowWithDraw(map);
	}
	
	/**
	 * 完成抵押标借款人提现后，通知借款系统
	 */
	public FssTradeApplyEntity withDrasApplyCallBack(String seqNo, String mchn) throws FssException {
		FssTradeApplyEntity fssTradeApplyEntity=null;
		fssTradeApplyEntity=this.getTradeApplyByParam(seqNo,mchn);
		if(fssTradeApplyEntity==null){
			throw new FssException("90004002");
		}
		return fssTradeApplyEntity;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：判断申请编号是否唯一
	 */
	public boolean uniqueByApplyNo(String applyNo){
		FssTradeApplyEntity record=new FssTradeApplyEntity();
		record.setApplyNo(applyNo);
		int selectCount = fssTradeApplyReadMapper.selectCount(record);
		return selectCount==0;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：得到申请编号(唯一)
	 */
	public String getApplyNo(){
		String applyNo = CommonUtil.getUUID();
		if(uniqueByApplyNo(applyNo)){
			return  applyNo;
		}else{
			applyNo = CommonUtil.getUUID();
			return this.getApplyNo();
		}
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月17日
	 * function：查询出代扣列表并添加进代扣申请表，
	 * 修改借款代扣状态为‘10090002’代扣中，
	 * '10090003'代扣成功
	 * @throws FssException 
	 */
	public void insertTradeApply(Long id) throws FssException {
		FssTradeApplyEntity tradeApplyEntity=null;
		//修改状态
		FssRepaymentParentEntity queryRepaymentParentById = fssRepaymentService.queryRepaymentParentById(id);
		queryRepaymentParentById.setState("10090002");
		queryRepaymentParentById.setMotifyTime(new Date());
		fssRepaymentService.updateRepaymentParent(queryRepaymentParentById);
		//根据对象查找借款代扣集合
		FssRepaymentEntity repayment=new FssRepaymentEntity();
		repayment.setId(id);
		List<FssRepaymentEntity> queryFssRepaymentEntity = fssRepaymentService.queryFssRepaymentEntity(repayment);
		for (FssRepaymentEntity fssRepaymentEntity : queryFssRepaymentEntity) {
			tradeApplyEntity=new FssTradeApplyEntity();
			//修改状态
			fssRepaymentEntity.setState("10090002");
			fssRepaymentEntity.setMotifyTime(new Date());
			fssRepaymentService.updateRepaymentEntity(fssRepaymentEntity);
			//添加代扣申请
			tradeApplyEntity.setAccNo(fssRepaymentEntity.getAccNo());
			tradeApplyEntity.setContractId(fssRepaymentEntity.getContractId());
			tradeApplyEntity.setMchnChild(fssRepaymentEntity.getMchnChild());
			tradeApplyEntity.setMchnParent(fssRepaymentEntity.getMchnParent());
			tradeApplyEntity.setSeqNo(fssRepaymentEntity.getSeqNo());
			tradeApplyEntity.setCustNo(fssRepaymentEntity.getId().toString());
			tradeApplyEntity.setCreateTime(new Date());
			tradeApplyEntity.setRealTradeAmount(fssRepaymentEntity.getAmt());
			tradeApplyEntity.setBusiType(fssRepaymentEntity.getTradeType());
			tradeApplyEntity.setTradeState("10090002");
			tradeApplyEntity.setApplyNo(this.getApplyNo());
			fssTradeApplyWriteMapper.insert(tradeApplyEntity);
			//添加交易记录
			this.insertTradeRecord(tradeApplyEntity);
		}
	}
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
	 * time:2016年3月17日
	 * function：添加进交易记录
	 * @throws FssException 
	 */
	public void  insertTradeRecord(FssTradeApplyEntity fssTradeApplyEntity) throws FssException{
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
	 * function：根据交易状态的交易申请列表
	 */
	public List<FssTradeApplyEntity> getTradeAppliesByTradeStatus(String tradeStatus){
		//查询所有处于划扣中状态的交易申请
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		fssTradeApplyEntity.setTradeState(tradeStatus);
		List<FssTradeApplyEntity> select = fssTradeApplyReadMapper.select(fssTradeApplyEntity);
		return select;
		
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：处理交易
	 */
	public void handleTrade(){
		//查询出处于划扣中的申请
		List<FssTradeApplyEntity> tradeAppliesByTradeStatus = getTradeAppliesByTradeStatus("10090002");
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
