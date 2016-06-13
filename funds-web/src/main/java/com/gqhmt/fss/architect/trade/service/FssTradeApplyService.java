package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.extServInter.dto.loan.WithDrawApplyResponse;
import com.gqhmt.extServInter.dto.p2p.WithHoldApplyResponse;
import com.gqhmt.extServInter.dto.trade.GETWithholdAndDrawDto;
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
import com.gqhmt.pay.service.trade.impl.FundsTradeImpl;
import com.gqhmt.util.CommonUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
	private FundsTradeImpl fundsTradeImpl;
	@Resource
    private TradeRecordService tradeRecordService;
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
		return	fssTradeApplyReadMapper.selectBySeqNoAndMchn(seqNo,mchn);
	}
	
	
	public FssTradeApplyEntity createTradeApplyEntity(FssAccountEntity fssAccountEntity,LoanWithDrawApplyDto wthDrawApplyDto) throws FssException {
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(wthDrawApplyDto.getAcc_no());
		//创建提现申请信息
			fssTradeApplyEntity.setApplyNo(com.gqhmt.core.util.CommonUtil.getTradeApplyNo(wthDrawApplyDto.getTrade_type()));
			fssTradeApplyEntity.setApplyType(1104);
			fssTradeApplyEntity.setCustId(fssAccountByAccNo.getCustId());
			fssTradeApplyEntity.setCustNo(fssAccountEntity.getCustNo());
			fssTradeApplyEntity.setUserNo(fssAccountEntity.getUserNo());
			fssTradeApplyEntity.setBusinessNo(wthDrawApplyDto.getContract_no());
			fssTradeApplyEntity.setBusiType(wthDrawApplyDto.getTrade_type());
			fssTradeApplyEntity.setAccNo(wthDrawApplyDto.getAcc_no());
			fssTradeApplyEntity.setTradeAmount(wthDrawApplyDto.getPay_amt());
			fssTradeApplyEntity.setRealTradeAmount(BigDecimal.ZERO);
			fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
			fssTradeApplyEntity.setTradeState(wthDrawApplyDto.getTrade_type());
			fssTradeApplyEntity.setApplyState("10100001");
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
		withDrawApplyResponse.setBespoke_date(CommonUtil.dateTostring(fssTradeApplyEntity.getBespokedate()));
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
			fssAccountByAccNo=fssAccountService.getFssAccountByCustId(Long.valueOf(fssLoanEntity.getMortgageeAccNo()));
		}else{
			fssAccountByAccNo=fssAccountService.getFssAccountByAccNo(fssLoanEntity.getMortgageeAccNo());
		}
			tradeApplyEntity.setAccNo(fssAccountByAccNo.getAccNo());
			tradeApplyEntity.setContractId(fssLoanEntity.getContractId());
			tradeApplyEntity.setBusinessNo(fssLoanEntity.getContractNo());
			tradeApplyEntity.setMchnChild(fssLoanEntity.getMchnChild());
			tradeApplyEntity.setMchnParent(fssLoanEntity.getMchnParent());
			tradeApplyEntity.setSeqNo(fssLoanEntity.getSeqNo());
			tradeApplyEntity.setCustNo(fssAccountByAccNo.getCustNo());
			tradeApplyEntity.setCustId(fssAccountByAccNo.getCustId());
			tradeApplyEntity.setUserNo(fssAccountByAccNo.getUserNo());
			tradeApplyEntity.setChannelNo(fssAccountByAccNo.getChannelNo().toString());
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
				fssTradeRecordService.insertRecord(tradeApplyEntity, 1);
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
	
	/**
	 * 根据id查询借款人提现信息
	 * @param id
	 * @return
	 */
	public FssTradeApplyEntity getFssTradeApplyEntityById(Long id) throws FssException{
		FssTradeApplyEntity tradeapply=fssTradeApplyReadMapper.selectByPrimaryKey(id);
		return tradeapply;
	}
	
	/**
	 * 冠E通后台代扣申请和提现申请
	 */
	public boolean careateTradeApply(GETWithholdAndDrawDto dto) throws FssException{
		//对提现申请金额进行资金冻结
		if("1104".equals(dto.getApply_type())){
	        FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(dto.getCust_no()),Integer.parseInt(dto.getCust_type()));
	        if (fromEntity == null) {
	            throw new FssException("90004006");
	        }
	        //判断提现金额是否大于账户余额
	        BigDecimal bigDecimal = fromEntity.getAmount();
	        if (bigDecimal.compareTo(dto.getAmt()) < 0) {
	            throw new FssException("90004007");
	        }
	        FundAccountEntity toEntity = fundAccountService.getFundAccount(Long.valueOf(dto.getCust_no()), GlobalConstants.ACCOUNT_TYPE_FREEZE);
			tradeRecordService.frozen(fromEntity,toEntity,dto.getAmt(),1007,null,"",BigDecimal.ZERO);//资金冻结
		}
		FssTradeApplyEntity fssTradeApplyEntity=this.CreateFssTradeApplyEntity(dto);
		try {
			fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
		} catch (Exception e) {
			throw new FssException("91009804");
		}
		return true;
	}
	
	/**
	 * 冠E通后台代付申请
	 * @param dto
	 * @return
	 * @throws FssException
	 */
	/*public boolean careateWithDrawApply(GET_WithholdDto dto) throws FssException{
		FssTradeApplyEntity fssTradeApplyEntity=this.CreateFssTradeApplyEntity(dto);
		try {
			fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
		} catch (Exception e) {
			throw new FssException("91009804");
		}
		return true;
	}*/
	
	/**
	 * 创建FssTradeApplyEntity
	 * @param dto
	 * @return
	 * @throws FssException
	 */
	public FssTradeApplyEntity CreateFssTradeApplyEntity(GETWithholdAndDrawDto dto) throws FssException{
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		fssTradeApplyEntity.setApplyNo(com.gqhmt.core.util.CommonUtil.getTradeApplyNo(dto.getTrade_type()));
		fssTradeApplyEntity.setApplyType(Integer.valueOf(dto.getApply_type()));
		fssTradeApplyEntity.setCustNo("");
		fssTradeApplyEntity.setUserNo("");
		if(dto.getContract_no()!=null && !"".equals(dto.getContract_no())){
			fssTradeApplyEntity.setBusinessNo(dto.getContract_no());
		}else{
			fssTradeApplyEntity.setBusinessNo(null);
		}
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
		fssTradeApplyEntity.setContractId(dto.getBusi_no());//合同Id
		fssTradeApplyEntity.setChannelNo(GlobalConstants.TRADE_ACCOUNT_PAY_CHANNEL_MAPPING.get(dto.getTrade_type()));//交易渠道
		fssTradeApplyEntity.setCount(0);
		fssTradeApplyEntity.setSuccessCount(0);
		fssTradeApplyEntity.setCustId(Long.valueOf(dto.getCust_no()));
		fssTradeApplyEntity.setCustType(Integer.valueOf(dto.getCust_type()));
		//提现添加预约到账日期
		if(dto.getApply_type().equals("1104")){//提现
			fssTradeApplyEntity.setBespokedate(new Date());
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
