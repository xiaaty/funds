package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.loan.WithDrawApplyResponse;
import com.gqhmt.extServInter.dto.p2p.WithHoldApplyResponse;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.bean.FssTradeApplyBean;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeApplyReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssTradeApplyWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
	
	@Resource
	private FssAccountService fssAccountService;
	@Resource
	private FssBackplateService fssBackplateService;
	@Resource
	private FssTradeRecordService fssTradeRecordService;
	@Resource
	private FssLoanService fssLoanService;
	@Resource
    private FundOrderService fundOrderService;

	@Resource
    private TradeRecordService tradeRecordService;

	
	
	/**
	 * 借款人提现完成通知借款系统
	 * @param seqNo
	 * @param mchn
	 * @return
	 */
	public FssTradeApplyEntity getTradeApplyByParam(String seqNo,String mchn) throws FssException{
		return	fssTradeApplyReadMapper.selectBySeqNoAndMchn(seqNo,mchn);
	}

	/**
	 * 借款人提现
	 * @param tradeType
	 * @param seqNo
	 * @param mchn
	 * @param accNo
	 * @param contractNo
	 * @param contractId
	 * @param amt
	 * @param bespoke_date
     * @throws FssException
     */
	public void createLoanWithdrawApply(String tradeType,String seqNo,String mchn,String accNo,String contractNo,String contractId,BigDecimal amt,String bespoke_date) throws FssException {
		FssAccountEntity fssAccountEntity= this.fundAccountService.getFssFundAccountInfo(accNo);
		//借款人提现,custTYpe 默认值 为1
		this.whithdrawApply(fssAccountEntity.getCustNo(),fssAccountEntity.getAccNo(),tradeType,amt,mchn,seqNo,fssAccountEntity.getCustId(),1,contractNo,contractId,this.compare_date(bespoke_date));
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
		if("10080002".equals(fssTradeApplyEntity.getTradeState())){
			withDrawApplyResponse.setResp_code("0000");
			withDrawApplyResponse.setResp_msg("成功");
		}else{
			withDrawApplyResponse.setResp_code(fssTradeApplyEntity.getTradeState());
		}
		withDrawApplyResponse.setMchn(fssTradeApplyEntity.getMchnChild());
		withDrawApplyResponse.setSeq_no(fssTradeApplyEntity.getSeqNo());
		withDrawApplyResponse.setTrade_type(fssTradeApplyEntity.getBusiType());
		withDrawApplyResponse.setContract_id(fssTradeApplyEntity.getContractId());
		withDrawApplyResponse.setContract_no(fssTradeApplyEntity.getBusinessNo());
		withDrawApplyResponse.setAcc_no(fssTradeApplyEntity.getAccNo());
		withDrawApplyResponse.setContract_amt(fssTradeApplyEntity.getTradeAmount());
		withDrawApplyResponse.setPay_amt(fssTradeApplyEntity.getRealTradeAmount());
		List<FssTradeRecordEntity> byApplyNo = fssTradeRecordService.getByApplyNo(fssTradeApplyEntity.getApplyNo());
		if(byApplyNo!=null){
			if(byApplyNo.get(0).getOrderNo()!=null) {
				FundOrderEntity findfundOrder = fundOrderService.findfundOrder(byApplyNo.get(0).getOrderNo());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String format = sdf.format(findfundOrder.getCreateTime());
				withDrawApplyResponse.setWithDraw_date(format);
			}
		}
		withDrawApplyResponse.setBespoke_date(DateUtil.dateTostring(fssTradeApplyEntity.getBespokedate()));
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
	public void insertTradeApply(FssRepaymentEntity fssRepaymentEntity) throws FssException {
		FssTradeApplyEntity tradeApplyEntity=null;
		//修改状态
		tradeApplyEntity=new FssTradeApplyEntity();
		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(fssRepaymentEntity.getAccNo());
		if (fssAccountByAccNo==null) throw new FssException("90002001");
		//修改状态
		fssRepaymentEntity.setState("10090002");
		fssRepaymentEntity.setMotifyTime(new Date());
		fssRepaymentService.updateRepaymentEntity(fssRepaymentEntity);
		//添加代扣申请
		tradeApplyEntity.setAccNo(fssRepaymentEntity.getAccNo());
		tradeApplyEntity.setCustNo(fssAccountByAccNo.getCustNo());
		tradeApplyEntity.setCustId(fssAccountByAccNo.getCustId());
		tradeApplyEntity.setUserNo(fssAccountByAccNo.getUserNo());
		tradeApplyEntity.setChannelNo(fssAccountByAccNo.getChannelNo().toString());
		tradeApplyEntity.setApplyType(1103);
		tradeApplyEntity.setTradeAmount(fssRepaymentEntity.getAmt());
		tradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
		tradeApplyEntity.setContractId(fssRepaymentEntity.getContractId());
		tradeApplyEntity.setMchnChild(fssRepaymentEntity.getMchnChild());
		tradeApplyEntity.setMchnParent(fssRepaymentEntity.getMchnParent());
		tradeApplyEntity.setSeqNo(fssRepaymentEntity.getSeqNo());
		tradeApplyEntity.setFormId(fssRepaymentEntity.getId());
		tradeApplyEntity.setCreateTime(new Date());
		tradeApplyEntity.setModifyTime(new Date());
		tradeApplyEntity.setRealTradeAmount(BigDecimal.ZERO);
		tradeApplyEntity.setBusiType(fssRepaymentEntity.getTradeType());
		tradeApplyEntity.setApplyState("10100001");
		tradeApplyEntity.setTradeState("10090002");
		tradeApplyEntity.setApplyNo(com.gqhmt.core.util.CommonUtil.getApplyNo(fssRepaymentEntity.getTradeType()));
		try {
			fssTradeApplyWriteMapper.insert(tradeApplyEntity);
			fssTradeRecordService.insertRecord(tradeApplyEntity, 1);
		} catch (FssException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月18日
	 * function：把借款人放款代扣添加进代扣申请表
	 * applyStatus:申请类型，充值，提现
	 */
	public void insertLoanTradeApply(FssLoanEntity fssLoanEntity,String applyStatus,String tradeType) throws FssException {
			FssTradeApplyEntity tradeApplyEntity=new FssTradeApplyEntity();
			//添加代扣申请
		FssAccountEntity fssAccountByAccNo =null;
		if("11090005".equals(tradeType)){
			tradeApplyEntity.setCustId(Long.valueOf(fssLoanEntity.getMortgageeAccNo()));
			tradeApplyEntity.setChannelNo("97010001");
		}else{
			fssAccountByAccNo=fssAccountService.getFssAccountByAccNo(fssLoanEntity.getMortgageeAccNo());
			tradeApplyEntity.setAccNo(fssAccountByAccNo.getAccNo());
			tradeApplyEntity.setChannelNo(fssAccountByAccNo.getChannelNo().toString());
			tradeApplyEntity.setCustNo(fssAccountByAccNo.getCustNo());
			tradeApplyEntity.setCustId(fssAccountByAccNo.getCustId());
			tradeApplyEntity.setUserNo(fssAccountByAccNo.getUserNo());
		}
			tradeApplyEntity.setContractId(fssLoanEntity.getContractId());
			tradeApplyEntity.setBusinessNo(fssLoanEntity.getContractNo());
			tradeApplyEntity.setMchnChild(fssLoanEntity.getMchnChild());
			tradeApplyEntity.setMchnParent(fssLoanEntity.getMchnParent());
			tradeApplyEntity.setSeqNo(fssLoanEntity.getSeqNo());
			tradeApplyEntity.setCreateTime(new Date());
			tradeApplyEntity.setModifyTime(new Date());
			tradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
			tradeApplyEntity.setTradeAmount(fssLoanEntity.getContractAmt());
			tradeApplyEntity.setRealTradeAmount(BigDecimal.ZERO);
			tradeApplyEntity.setBusiType(fssLoanEntity.getTradeType());
			tradeApplyEntity.setFormId(fssLoanEntity.getId());
			tradeApplyEntity.setApplyState(applyStatus);
			tradeApplyEntity.setApplyNo(com.gqhmt.core.util.CommonUtil.getApplyNo(tradeType));
			if ("11092001".equals(tradeType)){//提现申请
				tradeApplyEntity.setApplyType(1104);
				tradeApplyEntity.setTradeState("10090001");
				fssTradeApplyWriteMapper.insertSelective(tradeApplyEntity);
			}else {//代扣充值
				tradeApplyEntity.setTradeState("10090004");
				tradeApplyEntity.setApplyType(1103);
				fssTradeApplyWriteMapper.insertSelective(tradeApplyEntity);
				if("11090005".equals(tradeType)){
					fssTradeRecordService.moneySplit(tradeApplyEntity);
				}else {
					fssTradeRecordService.insertRecord(tradeApplyEntity, 1);
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
		List<FssTradeApplyEntity> select = fssTradeApplyReadMapper.selectByTradeState(tradeStatus);
		return select;
	}


	/**
	 * 修改执行条数
	 * @param fssTradeRecordEntity
	 * 根据申请编号修改执行条数,实际交易金额，修改日期
	 * @throws FssException 
     */
	public void updateExecuteCount(FssTradeRecordEntity fssTradeRecordEntity) throws FssException{
			fssTradeRecordEntity.setModifyTime(new Date());
			fssTradeApplyWriteMapper.updateTradeApplyByApplyNo(fssTradeRecordEntity);
		this.checkExecuteCount(fssTradeRecordEntity.getApplyNo());
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月19日
	 * function：判断 应执行数量 == 已执行数量,如果相等,执行状态 修改
	 * @throws FssException 
	 * 
	 */
	public void checkExecuteCount(String applyNo) {
		FssTradeApplyEntity applyEntity = fssTradeApplyReadMapper.selectByApplyNo(applyNo);
		//判断 应执行数量 == 已执行数量,如果相等,执行状态 修改
		 if(applyEntity.getCount()<=applyEntity.getSuccessCount()){
			try {
			 int successCount = fssTradeRecordService.getSuccessCount(applyNo);
			 BigDecimal realTradeAmt=fssTradeRecordService.getSuccessAmt(applyNo);
				 //划扣成功
			 String tradeStatus=null;
			 if(successCount==applyEntity.getCount()){
				 tradeStatus="10080002";
			 //划扣失败
			 }else if(successCount==0){
				 tradeStatus="10080010";
			 //部分成功
			 }else{
				 tradeStatus="10080003";
			 }
			 
			 if(!"".equals(applyEntity.getFormId())&&applyEntity.getFormId()!=null){
				 if("11090001".equals(applyEntity.getBusiType())||"11092001".equals(applyEntity.getBusiType())||"11090005".equals(applyEntity.getBusiType())){
					 FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(applyEntity.getFormId());
					 //98060001成功 //10080002交易成功
					 fssLoanService.update(fssLoanEntityById,tradeStatus);
				 }else if("11093001".equals(applyEntity.getBusiType())||"11093002".equals(applyEntity.getBusiType())){
					 //还款代扣
					 FssRepaymentEntity queryRepayment = fssRepaymentService.queryRepaymentById(applyEntity.getFormId());
					 fssRepaymentService.updateRepaymentEntity(queryRepayment, tradeStatus, realTradeAmt,applyEntity.getSeqNo(),applyEntity.getMchnChild(),applyEntity.getBusiType());
				 }else{
					 //创建回盘信息
					 fssBackplateService.createFssBackplateEntity(applyEntity.getSeqNo(),applyEntity.getMchnChild(),applyEntity.getBusiType());
				 }
			 }else{
				 //创建回盘信息
				 fssBackplateService.createFssBackplateEntity(applyEntity.getSeqNo(),applyEntity.getMchnChild(),applyEntity.getBusiType());
			 }
				 applyEntity.setModifyTime(new Date());
				 applyEntity.setRealTradeAmount(realTradeAmt);
				 applyEntity.setApplyState("10100003");//申请状态(已交易)
				 applyEntity.setTradeState(tradeStatus);
				 fssTradeApplyWriteMapper.updateByPrimaryKey(applyEntity);
				} catch (FssException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//通过交易类型,回调通知相应交易申请方.  //借款划扣 ,通知 相应划扣记录表..
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
	 *
	 * author:jhz
	 * time:2016年5月26日
	 * function：通过fromId和budiType查询申请表信息
	 */
	public FssTradeApplyEntity queryForFromId(Long fromId ,String busiType){
		return fssTradeApplyReadMapper.queryForFromId(fromId,busiType);
	}
	/**
	 * 查询抵押权人代扣信息
	 * @param map
	 * @return
	 */
		public List<FssTradeApplyBean> queryFssTradeApplyList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("applyType",map.get("applyType"));
			map2.put("busiType", map.get("busiType"));
			map2.put("applyNo", map.get("applyNo"));
			map2.put("businessNo", map.get("businessNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		List<FssTradeApplyBean> tradeapplylist=fssTradeApplyReadMapper.queryFssTradeApplyList(map2);
		return tradeapplylist;
	}
	

	
//	/**
//	 * 冠E通后台代扣申请和提现申请
//	 */
//	public boolean careateTradeApply(GETWithholdAndDrawDto dto) throws FssException{
//		//对提现申请金额进行资金冻结
//		if("1104".equals(dto.getApply_type())){
//
//		}
//		FssTradeApplyEntity fssTradeApplyEntity=this.CreateFssTradeApplyEntity(dto);
//		try {
//			fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
//		} catch (Exception e) {
//			throw new FssException("91009804");
//		}
//		return true;
//	}


	public void  whithdrawApply(String custNo,String accNo,String tradeType, BigDecimal amt,  String mchn, String seqNo,  Long custId, Integer custType, String contractNo,String cId,Integer settleType) throws FssException {

		//获取账户信息
		FundAccountEntity fromEntity = fundAccountService.getFundAccount(custId,custType);
		if (fromEntity == null) {
			throw new FssException("90004006");
		}
		//判断提现金额是否大于账户余额
		BigDecimal bigDecimal = fromEntity.getAmount();
		if (bigDecimal.compareTo(amt) < 0) {
			throw new FssException("90004007");
		}
		FundAccountEntity toEntity = fundAccountService.getFundAccount(custId, GlobalConstants.ACCOUNT_TYPE_FREEZE);
		//提现前资金冻结
		tradeRecordService.frozen(fromEntity,toEntity,amt,1007,null,"",BigDecimal.ZERO);//资金冻结
		FssTradeApplyEntity fssTradeApplyEntity = this.creareateFssTradeApplyEntity(custNo,accNo,tradeType,amt,mchn,seqNo,custId,custType,contractNo,cId,settleType,1104,false);
		try {
			fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
		} catch (Exception e) {
			throw new FssException("91009804");
		}
	}

	public void whithholdingApply(String custNo,String accNo,String tradeType, BigDecimal amt,  String mchn, String seqNo,  Long custId, Integer custType, String contractNo,String cId,boolean autoPass) throws FssException {
		FssTradeApplyEntity fssTradeApplyEntity = this.creareateFssTradeApplyEntity(custNo,accNo,tradeType,amt,mchn,seqNo,custId,custType,contractNo,cId,null,1103,autoPass);
		try {
			fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
		} catch (Exception e) {
			throw new FssException("91009804");
		}
	}

	

	/**
	 * 创建FssTradeApplyEntity
	 * @param tradeType				交易类型
	 * @param applyType				支付类型   1103 代扣  1104 代付
	 * @param amt					交易金额
	 * @param contractNo			业务编号 出借编号  借款合同号  无，则为互联网账户
	 * @param mchn					商户号
	 * @param seqNo					流水号
	 * @param cId					业务系统id  仅记录并回盘返回
	 * @param custId				业务系统客户id
	 * @param custType				业务系统账户类型  如果是96，新版交易流水则判断应付款账户余额
	 * @param settleType			代付时效性   目前付款资费 t0t1统一，但是有限额
     * @return
     * @throws FssException
     */
	private FssTradeApplyEntity creareateFssTradeApplyEntity(String custNo,String accNo,String tradeType, BigDecimal amt,  String mchn, String seqNo,  Long custId, Integer custType, String contractNo,String cId,Integer settleType, Integer applyType,boolean autoPass) throws FssException{
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		fssTradeApplyEntity.setApplyNo(com.gqhmt.core.util.CommonUtil.getTradeApplyNo(tradeType));
		fssTradeApplyEntity.setApplyType(applyType);

		fssTradeApplyEntity.setCustNo(custNo);
		fssTradeApplyEntity.setAccNo(accNo);
		fssTradeApplyEntity.setBusinessNo(contractNo);
		fssTradeApplyEntity.setBusiType(tradeType);
		fssTradeApplyEntity.setTradeAmount(amt);
		fssTradeApplyEntity.setRealTradeAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setTradeState("10080001");
		String applyState = "10100001";
		if(autoPass){
			applyState = "10100002";
		}
		fssTradeApplyEntity.setApplyState(applyState);
		//判断商户号是否为空
		if(mchn!=null && !"".equals(mchn)){
			fssTradeApplyEntity.setMchnChild(mchn);
			fssTradeApplyEntity.setMchnParent(Application.getInstance().getParentMchn(mchn));
		}else{
			fssTradeApplyEntity.setMchnChild("01");
			fssTradeApplyEntity.setMchnParent("01");
		}
		fssTradeApplyEntity.setCreateTime((new Date()));
		fssTradeApplyEntity.setModifyTime((new Date()));
		fssTradeApplyEntity.setSeqNo(seqNo);
		fssTradeApplyEntity.setContractId(cId);//合同Id
		fssTradeApplyEntity.setChannelNo(GlobalConstants.TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.get(tradeType));//交易渠道
		fssTradeApplyEntity.setCount(0);
		fssTradeApplyEntity.setSuccessCount(0);
		fssTradeApplyEntity.setCustId(custId);
		fssTradeApplyEntity.setCustType(custType);
		//提现添加预约到账日期
		if(applyType.equals("1104")){//提现
			fssTradeApplyEntity.setBespokedate(new Date());
			fssTradeApplyEntity.setSettleType(settleType);
		}
		return fssTradeApplyEntity;
	}


	public FssTradeApplyEntity getFssTradeApplyEntityByApplyNo(String applyNo) throws FssException{
		FssTradeApplyEntity tradeapply=fssTradeApplyReadMapper.selectFssTradeApplyEntityByApplyNo(applyNo);
		return tradeapply;
	}
	
	public FssTradeApplyBean getFssTradeApply(String applyNo){
		FssTradeApplyBean fssTradeApplyBean=fssTradeApplyReadMapper.queryFssTradeApply(applyNo);
		return fssTradeApplyBean;
	}
	
	 /**
     * 判断预约到账日期是否为今天
     */

	 public int compare_date(String BespokeDate){
		 return 0;
	 }
    public int compare_date(Date BespokeDate) {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date date=new Date();
    	String nowdate=sdf.format(date);
    	String bespokeDate=sdf.format(BespokeDate);
    	if(bespokeDate.equals(nowdate)){
    		return 0;
    	}else{
    		return 1;
    	}
    }
    
    /**
	 * author:柯禹来
	 * time:2016年4月25日
	 * function：得到冠e通回调对象
	 */
	public WithHoldApplyResponse getWhithHoldApplyResponse(String mchn, String seqNo)throws FssException {
		WithHoldApplyResponse response=new WithHoldApplyResponse();
		response=fssTradeApplyReadMapper.selectTradeApplyData(mchn,seqNo);
		return response;
	}
	
}
