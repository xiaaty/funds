package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.extServInter.dto.loan.WithDrawApplyResponse;
import com.gqhmt.extServInter.dto.trade.GET_PrePaymentDto;
import com.gqhmt.extServInter.dto.trade.GET_WithholdDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentParentEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeApplyReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssTradeApplyWriteMapper;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.util.CommonUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
	private FundAccountService fundAccountService;
	
	@Resource
	private FssRepaymentService fssRepaymentService;
	
	
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
			fssTradeApplyEntity.setBusinessNo(wthDrawApplyDto.getContract_no());
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
			Date date=new Date();
	    	Date BespokeDate = CommonUtil.stringToDate(wthDrawApplyDto.getBespoke_date());
	    	if(BespokeDate.before(date)){
	    		fssTradeApplyEntity.setBespokedate(date);
	    	}else{
	    		fssTradeApplyEntity.setBespokedate(BespokeDate);
	    	}
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
	public WithDrawApplyResponse withDrasApplyCallBack(String seqNo, String mchn) throws FssException {
		WithDrawApplyResponse withDrawApplyResponse=new WithDrawApplyResponse();
		FssTradeApplyEntity fssTradeApplyEntity=this.getTradeApplyByParam(seqNo,mchn);
		if(fssTradeApplyEntity==null){
			throw new FssException("90004002");
		}
		withDrawApplyResponse.setMchn(fssTradeApplyEntity.getMchnChild());
		withDrawApplyResponse.setSeq_no(fssTradeApplyEntity.getSeqNo());
		withDrawApplyResponse.setTrade_type(fssTradeApplyEntity.getTradeState());
		withDrawApplyResponse.setContract_id(fssTradeApplyEntity.getContractId());
		withDrawApplyResponse.setContract_amt(fssTradeApplyEntity.getTradeAmount());
		withDrawApplyResponse.setPay_amt(fssTradeApplyEntity.getRealTradeAmount());
		withDrawApplyResponse.setBespoke_date(fssTradeApplyEntity.getBespokedate());
		return withDrawApplyResponse;
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
		}
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：把借款人放款代扣添加进代扣申请表
	 * applyStatus:申请类型，充值，提现
	 */
	public void insertLoanTradeApply(FssLoanEntity fssLoanEntity,String applyStatus) throws FssException {
			FssTradeApplyEntity tradeApplyEntity=new FssTradeApplyEntity();
			//添加代扣申请
			tradeApplyEntity.setAccNo(fssLoanEntity.getMortgageeAccNo());
			tradeApplyEntity.setContractId(fssLoanEntity.getContractId());
			tradeApplyEntity.setMchnChild(fssLoanEntity.getMchnChild());
			tradeApplyEntity.setMchnParent(fssLoanEntity.getMchnParent());
			tradeApplyEntity.setSeqNo(fssLoanEntity.getSeqNo());
			tradeApplyEntity.setCustNo(fssLoanEntity.getId().toString());
			tradeApplyEntity.setCreateTime(new Date());
			tradeApplyEntity.setModifyTime(new Date());
			tradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
			tradeApplyEntity.setTradeAmount(fssLoanEntity.getContractAmt());
			tradeApplyEntity.setRealTradeAmount(fssLoanEntity.getPayAmt());
			tradeApplyEntity.setBusiType(fssLoanEntity.getTradeType());
			tradeApplyEntity.setApplyType(1103);
			tradeApplyEntity.setApplyState(applyStatus);
			tradeApplyEntity.setTradeState(fssLoanEntity.getStatus());
			tradeApplyEntity.setApplyNo(this.getApplyNo());
			fssTradeApplyWriteMapper.insert(tradeApplyEntity);
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
	 * 修改执行条数
	 * @param applyNo
     */
	public void updateExecuteCount(String applyNo){
		FssTradeApplyEntity applyEntity =new FssTradeApplyEntity();
		applyEntity.setApplyNo(applyNo);
		 List<FssTradeApplyEntity> select = this.fssTradeApplyReadMapper.select(applyEntity);
		 applyEntity=select.get(0);
		 applyEntity.setSuccessCount(applyEntity.getSuccessCount()+1);
		 this.updateTradeApply(applyEntity);
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月19日
	 * function：判断 应执行数量 == 已执行数量,如果相等,执行状态 修改
	 * 
	 */
	public void checkExecuteCount(String applyNo){
		FssTradeApplyEntity applyEntity =new FssTradeApplyEntity();
		applyEntity.setApplyNo(applyNo);
		 List<FssTradeApplyEntity> select = this.fssTradeApplyReadMapper.select(applyEntity);
		 applyEntity=select.get(0);
		//判断 应执行数量 == 已执行数量,如果相等,执行状态 修改
		 if(applyEntity.getCount()<=applyEntity.getSuccessCount()){
			 applyEntity.setTradeState("10090003");
			 applyEntity.setModifyTime(new Date());
			fssTradeApplyWriteMapper.updateByPrimaryKey(applyEntity);
			//通过交易类型,回调通知相应交易申请方.  //借款划扣 ,通知 相应划扣记录表..
			//todo
			fssRepaymentService.changeTradeStatus(Long.parseLong(applyEntity.getCustNo()));
		 }

	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月19日
	 * function：修改交易申请
	 */
	public void updateTradeApply(FssTradeApplyEntity applyEntity ){
		fssTradeApplyWriteMapper.updateByPrimaryKey(applyEntity);
	}
	/**
	 * 查询抵押权人代扣信息
	 * @param tradeapply
	 * @return
	 */
	public List<FssTradeApplyEntity> queryFssTradeApplyList(Map map){
		List<FssTradeApplyEntity> tradeapplylist=fssTradeApplyReadMapper.queryFssTradeApplyList(map);
		return tradeapplylist;
	}
	
	/**
	 * 根据id查询借款人体现信息
	 * @param tradeapply
	 * @return
	 */
	public FssTradeApplyEntity getFssTradeApplyEntityById(Long id) throws FssException{
		FssTradeApplyEntity tradeapply=fssTradeApplyReadMapper.selectByPrimaryKey(id);
		return tradeapply;
	}
	
	/**
	 * 冠E通后台发起代扣申请
	 * 
	 */
	public Response careateWithholdApplyEntity(GET_WithholdDto dto) throws FssException{
		Response respon=new Response();
		FssTradeApplyEntity fssTradeApplyEntity=this.CreateNewFssTradeApplyEntity(dto);
		try {
			fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
			respon.setResp_code("00000000");
		} catch (Exception e) {
			respon.setResp_code("91009804");
		}
		return respon;
	}
	
	/**
	 * 冠E通后台发起代付申请
	 * @return
	 */
	public Response createPrePaymentApply(GET_PrePaymentDto dto) throws FssException{
		Response respon=new Response();
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		fssTradeApplyEntity.setApplyNo(fundAccountService.getAccountNo());//时间+随机数
		fssTradeApplyEntity.setApplyType(1104);//提现
		fssTradeApplyEntity.setCustId(Long.valueOf(dto.getCust_id()));
		fssTradeApplyEntity.setCustNo("");
		fssTradeApplyEntity.setUserNo("");
		fssTradeApplyEntity.setBusinessNo(dto.getContract_no());
		fssTradeApplyEntity.setBusiType(dto.getTrade_type());
		fssTradeApplyEntity.setAccNo("");
		fssTradeApplyEntity.setTradeAmount(dto.getAmt());
		fssTradeApplyEntity.setRealTradeAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setTradeState("");
		fssTradeApplyEntity.setApplyState("10100001");
		fssTradeApplyEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
		fssTradeApplyEntity.setMchnChild(dto.getMchn());
		fssTradeApplyEntity.setCreateTime((new Date()));
		fssTradeApplyEntity.setModifyTime((new Date()));
		fssTradeApplyEntity.setSeqNo(dto.getSeq_no());
		fssTradeApplyEntity.setBespokedate(new Date());
		fssTradeApplyEntity.setContractId(dto.getContract_id());//合同Id
		fssTradeApplyEntity.setChannelNo("97010001");//交易渠道
		fssTradeApplyEntity.setCount(0);
		fssTradeApplyEntity.setSuccessCount(0);
		try {
			fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
			respon.setResp_code("00000000");
		} catch (Exception e) {
			respon.setResp_code("91009804");
		}
		return respon;
	}
	
	/**
	 * 创建FssTradeApplyEntity
	 * @param dto
	 * @return
	 * @throws FssException
	 */
	public FssTradeApplyEntity CreateNewFssTradeApplyEntity(GET_WithholdDto dto) throws FssException{
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		fssTradeApplyEntity.setApplyNo(fundAccountService.getAccountNo());//时间+随机数
		fssTradeApplyEntity.setApplyType(1103);
		fssTradeApplyEntity.setCustNo("");
		fssTradeApplyEntity.setUserNo("");
		fssTradeApplyEntity.setBusinessNo(dto.getContract_no());
		fssTradeApplyEntity.setBusiType(dto.getTrade_type());
		fssTradeApplyEntity.setAccNo("");
		fssTradeApplyEntity.setTradeAmount(dto.getAmt());
		fssTradeApplyEntity.setRealTradeAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setTradeState("10080001");
		fssTradeApplyEntity.setApplyState("10100001");
		fssTradeApplyEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
		fssTradeApplyEntity.setMchnChild(dto.getMchn());
		fssTradeApplyEntity.setCreateTime((new Date()));
		fssTradeApplyEntity.setModifyTime((new Date()));
		fssTradeApplyEntity.setSeqNo(dto.getSeq_no());
		fssTradeApplyEntity.setBespokedate(new Date());
		fssTradeApplyEntity.setContractId(dto.getContract_id());//合同Id
		fssTradeApplyEntity.setChannelNo("97010001");//交易渠道
		fssTradeApplyEntity.setCount(0);
		fssTradeApplyEntity.setSuccessCount(0);
		fssTradeApplyEntity.setCustId(Long.valueOf(dto.getCust_id()));
		return fssTradeApplyEntity;
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
}
