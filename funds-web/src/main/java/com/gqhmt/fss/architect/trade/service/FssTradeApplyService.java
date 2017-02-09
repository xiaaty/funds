package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.WithDrawApplyResponse;
import com.gqhmt.extServInter.dto.p2p.WithHoldApplyResponse;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
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
import com.gqhmt.util.DateUtil;
import com.gqhmt.util.ExportExcel;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.gqhmt.core.util.XmlUtil.log;
import static com.gqhmt.pay.core.configer.ConfigAbstract.getClassPath;

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
		this.whithdrawApply(fssAccountEntity.getCustNo(),fssAccountEntity.getAccNo(),tradeType,amt,mchn,seqNo,fssAccountEntity.getCustId(),1,contractNo,contractId,null,this.compare_date(bespoke_date));
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
		//修改状态
//		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(fssRepaymentEntity.getAccNo());
//		if (fssAccountByAccNo==null) throw new FssException("90002001");
		//修改状态

		fssRepaymentEntity.setState("10090002");
		fssRepaymentEntity.setMotifyTime(new Date());
		fssRepaymentService.updateRepaymentEntity(fssRepaymentEntity);

		long custId = 0;

		//判断是否中间人代扣
		if("10180002".equals(fssRepaymentEntity.getWithholdType())){
			custId = Long.parseLong(fssRepaymentEntity.getMidCustId());
		}else{
			custId = Long.parseLong(fssRepaymentEntity.getAccNo());
		}

		//添加代扣申请
		this.whithholdingApply(null,null,fssRepaymentEntity.getTradeType(),fssRepaymentEntity.getAmt(),fssRepaymentEntity.getMchnChild(),fssRepaymentEntity.getSeqNo(),custId,1,fssRepaymentEntity.getContractNo(),fssRepaymentEntity.getContractId(),fssRepaymentEntity.getId(),true);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年3月18日
	 * function：把借款人放款代扣添加进代扣申请表
	 * applyStatus:申请类型，充值，提现
	 */
	public void insertLoanTradeApply(FssLoanEntity fssLoanEntity,String tradeType) throws FssException {
		FssTradeApplyEntity tradeApplyEntity=new FssTradeApplyEntity();
		//添加代扣申请
		if("11092001".equals(tradeType)){
			FssAccountEntity fssAccountByAccNo=fssAccountService.getFssAccountByAccNo(fssLoanEntity.getMortgageeAccNo());
			this.whithdrawApply(fssAccountByAccNo.getCustNo(),fssAccountByAccNo.getAccNo(),fssLoanEntity.getTradeType(),fssLoanEntity.getContractAmt(),fssLoanEntity.getMchnChild(),fssLoanEntity.getSeqNo(),fssAccountByAccNo.getCustId(),1,fssLoanEntity.getContractNo(),fssLoanEntity.getContractId(),fssLoanEntity.getId(),1);
		}else if("11090006".equals(tradeType)){
			this.whithdrawApply(null,null,fssLoanEntity.getTradeType(),fssLoanEntity.getContractAmt(),fssLoanEntity.getMchnChild(),fssLoanEntity.getSeqNo(),Long.valueOf(fssLoanEntity.getMortgageeAccNo()),1,fssLoanEntity.getContractNo(),fssLoanEntity.getContractId(),fssLoanEntity.getId(),1);
		}else if("11090005".equals(tradeType)){
			this.whithholdingApply(null,null,fssLoanEntity.getTradeType(),fssLoanEntity.getContractAmt(),fssLoanEntity.getMchnChild(),fssLoanEntity.getSeqNo(),Long.valueOf(fssLoanEntity.getMortgageeAccNo()),1,fssLoanEntity.getContractNo(),fssLoanEntity.getContractId(),fssLoanEntity.getId(),true);
		//信用标借款人提现
		}else if("11090004".equals(tradeType)){
			BigDecimal amt=BigDecimal.ZERO;
			//首次提现
			if("10050023".equals(fssLoanEntity.getStatus())){
				amt=fssLoanEntity.getFirstAmt();
				//二次提现
			}else{
				amt=fssLoanEntity.getSecondAmt();
			}
			this.whithdrawApply(null,null,fssLoanEntity.getTradeType(),amt,fssLoanEntity.getMchnChild(),fssLoanEntity.getSeqNo(),Long.valueOf(fssLoanEntity.getAccNo()),GlobalConstants.ACCOUNT_TYPE_LOAN,fssLoanEntity.getContractNo(),fssLoanEntity.getContractId(),fssLoanEntity.getId(),0);
		}else{
			FssAccountEntity fssAccountByAccNo=fssAccountService.getFssAccountByAccNo(fssLoanEntity.getMortgageeAccNo());
			this.whithholdingApply(fssAccountByAccNo.getCustNo(),fssAccountByAccNo.getAccNo(),fssLoanEntity.getTradeType(),fssLoanEntity.getContractAmt(),fssLoanEntity.getMchnChild(),fssLoanEntity.getSeqNo(),fssAccountByAccNo.getCustId(),1,fssLoanEntity.getContractNo(),fssLoanEntity.getContractId(),fssLoanEntity.getId(),true);
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
	 *
	 * author:jhz
	 * time:2016年6月16日
	 * function：根据申请状态的交易申请列表
	 */
	public List<FssTradeApplyEntity> getTradeAppliesByApplyState(String applyState){
		List<FssTradeApplyEntity> select = fssTradeApplyReadMapper.selectByApplyState(applyState);
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
			 BigDecimal realTradeAmt=fssTradeRecordService.getSuccessAmt(applyNo);

				if(null ==realTradeAmt ||"".equals(realTradeAmt)){
					realTradeAmt=BigDecimal.ZERO;
				}
				 //划扣成功
			 String tradeStatus=null;
			 if(applyEntity.getTradeAmount().compareTo(realTradeAmt)==0){
				tradeStatus="10080002";
				//划扣失败
		  	 }else if(realTradeAmt.compareTo(BigDecimal.ZERO)==0){
				 tradeStatus="10080010";
			 //部分成功
			 }else{
				 tradeStatus="10080003";
			 }
			if(!"10080002".equals(tradeStatus) && 1104==applyEntity.getApplyType()) {
				//代付失败进行资金解冻
				fundsTradeImpl.unFroze(applyEntity.getMchnChild(), applyEntity.getSeqNo(), applyEntity.getBusiType(), String.valueOf(applyEntity.getCustId()), applyEntity.getUserNo(), applyEntity.getTradeAmount().subtract(realTradeAmt), applyEntity.getCustType(),applyEntity.getSeqNo());
			}
			FssBackplateEntity fssBackplateEntity = fssBackplateService.selectByMchnAndseqNo(applyEntity.getMchnChild(), applyEntity.getSeqNo());
			if(!"".equals(applyEntity.getFormId())&&applyEntity.getFormId()!=null){
				if("11090001".equals(applyEntity.getBusiType())||"11090005".equals(applyEntity.getBusiType())){
					FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(applyEntity.getFormId());
					//98060001成功 //10080002交易成功
					fssLoanService.update(fssLoanEntityById,tradeStatus,tradeStatus);
				}else if("11092001".equals(applyEntity.getBusiType())||"11090006".equals(applyEntity.getBusiType())){
					//借款系统和冠e通抵押权人提现不处理
				}else if("11093001".equals(applyEntity.getBusiType())||"11093002".equals(applyEntity.getBusiType())){
					//还款代扣
				 FssRepaymentEntity queryRepayment = fssRepaymentService.queryRepaymentById(applyEntity.getFormId());
				 fssRepaymentService.updateRepaymentEntity(queryRepayment, tradeStatus, realTradeAmt,applyEntity.getSeqNo(),applyEntity.getMchnChild(),applyEntity.getBusiType());
				}else if("11090004".equals(applyEntity.getBusiType())){
					FssLoanEntity fssLoanEntity = fssLoanService.getFssLoanEntityById(applyEntity.getFormId());
					//首次提现成功进行回盘
					if("10050023".equals(fssLoanEntity.getStatus())){
					//98060001成功 //10080002交易成功	 //10050023 首次提现中	//10050017 首次提现成功
					fssLoanService.update(fssLoanEntity,tradeStatus,"10050017");
					fssBackplateService.createFssBackplateEntity(applyEntity.getSeqNo(), applyEntity.getMchnChild(), fssLoanEntity.getTradeType());
					//费用代扣成功修改状态
					}else if("10050018".equals(fssLoanEntity.getStatus())){
						fssLoanService.update(fssLoanEntity,tradeStatus,"10050019");
						List<FssFeeList> fssFeeLists = fssLoanService.getFeeList(fssLoanEntity.getId());
						for (FssFeeList fssFeeList : fssFeeLists) {
							if("10050018".equals(fssFeeList.getTradeStatus())){
								fssFeeList.setTradeStatus("10050019");
								fssLoanService.updateFeeList(fssFeeList);
							}
						}
					//二次提现成功修改状态
					}else if("10050020".equals(fssLoanEntity.getStatus())){
						fssLoanService.update(fssLoanEntity,tradeStatus,"10050021");
					}
				}else {
					if (fssBackplateEntity != null) {
					 fssBackplateService.updatebackplate(fssBackplateEntity);
				 	} else{
					 //创建回盘信息
					 fssBackplateService.createFssBackplateEntity(applyEntity.getSeqNo(), applyEntity.getMchnChild(), applyEntity.getBusiType());
					}
			 	}
			 }else{
					if (fssBackplateEntity != null) {
						fssBackplateService.updatebackplate(fssBackplateEntity);
					} else {
						//创建回盘信息
						fssBackplateService.createFssBackplateEntity(applyEntity.getSeqNo(), applyEntity.getMchnChild(), applyEntity.getBusiType());
					}
			  }
				 applyEntity.setModifyTime(new Date());
				 applyEntity.setRealTradeAmount(realTradeAmt);
				 applyEntity.setApplyState("10100005");//申请状态(已交易)
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
	 * time:2016年6月22日
	 * function：修改交易申请
	 */
	public void updateTradeApply(FssTradeApplyEntity applyEntity,String applyState,String tradeState ){
		applyEntity.setApplyState(applyState);
		applyEntity.setTradeState(tradeState);
		applyEntity.setModifyTime(new Date());
		fssTradeApplyWriteMapper.updateByPrimaryKey(applyEntity);
	}
	/**
	 * jhz
	 * 根据申请编号进行批量体现
	 * @param applyNos
	 * @return
	 * @throws FssException
     */
	public int withNumbers(String applyNos,Integer bespokeDate)throws FssException{
		FssTradeApplyEntity tradeapply=null;
		LogUtil.info(this.getClass(),"申请编号字符串为："+applyNos);
		String[] applyNo = applyNos.split(",");
		int count=0;
		for (int i = 0; i < applyNo.length; i++) {
			LogUtil.info(this.getClass(),"查询编号为："+applyNo[i]);

			tradeapply=this.getFssTradeApplyEntityByApplyNo(applyNo[i]);
			if(tradeapply==null){
				continue;
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			try {
			if("10100001".equals(tradeapply.getApplyState())){
				if(bespokeDate==0){
					Date a=new Date();
					String b=DateUtil.dateToString(a);
					tradeapply.setBespokedate(sdf.parse(b));
					tradeapply.setSettleType(0);
				}else if(bespokeDate==1){
					Calendar calendar=Calendar.getInstance();
					Date a=new Date();
					calendar.setTime(a);
					calendar.add(calendar.DATE,1);
					String b=DateUtil.dateToString(calendar.getTime());
					tradeapply.setBespokedate(sdf.parse(b));
					tradeapply.setSettleType(1);
				}
				tradeapply.setAuditAmount(tradeapply.getTradeAmount());
				this.updateTradeApply(tradeapply,"10100002","10080001");
				count++;
			}
			} catch (ParseException e) {
				LogUtil.debug(e.getClass(),e.getMessage());
				e.printStackTrace();
			}
		}
		return (applyNo.length-count);
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
			map2.put("custName", map.get("custName"));
			map2.put("custMobile", map.get("custMobile"));
			map2.put("applyState", map.get("applyState"));
			map2.put("tradeState", map.get("tradeState"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
			//xdw 增加id 查询， 怕影响前面逻辑，改名为 ApplyBeanId
			if(map.get("ApplyBeanId")!=null){
				map2.put("id",map.get("ApplyBeanId"));
			}
		}
		List<FssTradeApplyBean> tradeapplylist=fssTradeApplyReadMapper.queryFssTradeApplyList(map2);
		return tradeapplylist;
	}




	//提现申请
	public void  whithdrawApply(String custNo,String accNo,String tradeType, BigDecimal amt,  String mchn, String seqNo,  Long custId, Integer custType, String contractNo,String cId,Long fromId,Integer settleType) throws FssException {

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
		if (toEntity == null) {
			throw new FssException("90004006");
		}
		//提现前资金冻结
		tradeRecordService.frozen(fromEntity,toEntity,amt,1007,null,"",BigDecimal.ZERO,tradeType,seqNo);//资金冻结
		FssTradeApplyEntity fssTradeApplyEntity = this.createFssTradeApplyEntity(custNo,accNo,tradeType,amt,mchn,seqNo,custId,custType,contractNo,cId,settleType,1104,fromId,false);
		try {
			fssTradeApplyWriteMapper.insertSelective(fssTradeApplyEntity);
		} catch (Exception e) {
			throw new FssException("91009804");
		}
	}
	//代扣申请
	public void whithholdingApply(String custNo,String accNo,String tradeType, BigDecimal amt,  String mchn, String seqNo,  Long custId, Integer custType, String contractNo,String cId,Long fromId,boolean autoPass) throws FssException {
		FssTradeApplyEntity fssTradeApplyEntity = this.createFssTradeApplyEntity(custNo,accNo,tradeType,amt,mchn,seqNo,custId,custType,contractNo,cId,null,1103,fromId,autoPass);
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
	private FssTradeApplyEntity createFssTradeApplyEntity(String custNo,String accNo,String tradeType, BigDecimal amt,  String mchn, String seqNo,  Long custId, Integer custType, String contractNo,String cId,Integer settleType, Integer applyType,Long fromId,boolean autoPass) throws FssException{
		FssTradeApplyEntity fssTradeApplyEntity=new FssTradeApplyEntity();
		fssTradeApplyEntity.setApplyNo(com.gqhmt.core.util.CommonUtil.getTradeApplyNo(tradeType));
		fssTradeApplyEntity.setApplyType(applyType);
		fssTradeApplyEntity.setCustNo(custNo);
		fssTradeApplyEntity.setAccNo(accNo);
		fssTradeApplyEntity.setBusinessNo(contractNo);
		fssTradeApplyEntity.setBusiType(tradeType);
		fssTradeApplyEntity.setTradeAmount(amt);
		fssTradeApplyEntity.setAuditAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setRealTradeAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setTradeChargeAmount(BigDecimal.ZERO);
		fssTradeApplyEntity.setTradeState("10080001");
		String applyState = "10100001";
		if(autoPass){
			fssTradeApplyEntity.setAuditAmount(amt);
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
		if(null!=fromId){
		fssTradeApplyEntity.setFormId(fromId);
		}
		//提现添加预约到账日期
		if(applyType.intValue()==1104){//提现
			//根据settle_type 判断预约到账日期
			if(settleType==null || settleType.intValue()>0){
				Calendar calendar=Calendar.getInstance();
				calendar.roll(Calendar.DAY_OF_YEAR,1);
				fssTradeApplyEntity.setBespokedate(calendar.getTime());
				fssTradeApplyEntity.setSettleType(1);
			}else{
				fssTradeApplyEntity.setBespokedate(new Date());
				fssTradeApplyEntity.setSettleType(settleType);
			}
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

	 public int compare_date(String BespokeDate)throws  FssException{
		 try{
			 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			 Date dateTime1 = df.parse(BespokeDate);
			 String dateTime2 = df.format(new Date());
			 Date today=df.parse(dateTime2);
			 if(dateTime1.compareTo(today)>0){//大于等于今天
				 return 1;
			 }else{
				 return 0;
			 }
		 }catch (Exception e){
			 throw new FssException("日期格式转换异常");
		 }
	 }

	public int compare_date(Date BespokeDate) throws FssException{
		try{
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			String dateTime1 = df.format(BespokeDate);//预约到账日期
			Date bespDate=df.parse(dateTime1);
			String dateTime2 = df.format(new Date());
			Date today=df.parse(dateTime2);
			if(bespDate.compareTo(today)>0){//大于等于今天
				return 1;
			}else{
				return 0;
			}
		}catch (Exception e){
			throw new FssException("日期格式转换异常");
		}
	}

    /**
	 * author:柯禹来
	 * time:2016年4月25日
	 * function：得到冠e通回调对象
	 */
	public WithHoldApplyResponse getWhithHoldApplyResponse(String mchn, String seqNo)throws FssException {
		WithHoldApplyResponse response=fssTradeApplyReadMapper.selectTradeApplyData(mchn,seqNo);
		return response;
	}

	/**
	 * author:jhz
	 * time:2016年6月20日
	 * function：根据id查询申请对象
	 */
	public  FssTradeApplyEntity selectTradeApplyById(Long id){
		return  fssTradeApplyReadMapper.selectByPrimaryKey(id);
	};

	/**
	 * author:xdw
	 * time:2016年7月14日
	 * function：TradeApply,tradeRecord导出excel
	 */
	public void exportTradeApplyList(List<FssTradeApplyBean> tradeApplyList) throws IOException {

		List<Map> mapList = new ArrayList<Map>();

		if(tradeApplyList.size()>0){
			for(FssTradeApplyBean tradeApply:tradeApplyList){
				Map<String, Object> mapObject = new HashMap<String, Object>();
				List<FssTradeRecordEntity> tradeRecordList = fssTradeRecordService.queryFssTradeRecordList(tradeApply.getApplyNo(), null);
				mapObject.put("tradeApply", tradeApply);
				mapObject.put("tradeRecordList", tradeRecordList);
				mapList.add(mapObject);
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String path = getClassPath();
		File filepath = new File(path + File.separator +"excel");
		String address = filepath + File.separator + sdf.format(new Date()) + ".xls";

		String fileName = checkFileName(address, 0);

		OutputStream out = new FileOutputStream(fileName);
		System.out.println("导出路径： " + fileName);
		String[] headers =
				{"序号", "业务编号", "申请单号", "客户姓名", "客户电话", "交易金额", "单次交易金额", "创建时间"};
		ExportExcel<Map> ex = new ExportExcel<Map>();
		try {
			ex.exportExcel("tradeApply", headers, mapList, out);
		} catch (IllegalAccessException i) {
			i.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

		//------------------------------
		//step1. 保存一个临时excel到temp目录下
		//------------------------------
		//并且已经生成了一个File 指向这个临时的 excel，名叫exportFile
		//-------------------------------
		//step2. 弹出下载对话框
		//-------------------------------
		File exportFile = new File(fileName);

		int index = fileName.lastIndexOf(File.separator);
		String excelName = fileName.substring(index);

		if(exportFile == null){
			log.error("生成excel错误! exportFile 为空");
			return;
		}

		//先建立一个文件读取流去读取这个临时excel文件
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(exportFile);
		} catch (FileNotFoundException e) {
			log.error("生成excel错误! " + exportFile + " 不存在!",e);
			return;
		}
		// 设置响应头和保存文件名
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		//这个一定要设定，告诉浏览器这次请求是一个下载的数据流
		response.setContentType("APPLICATION/OCTET-STREAM");
		/*try {
			excelName = URLEncoder.encode(excelName, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			log.error("转换excel名称编码错误!",e1);
		}*/

		response.setHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\"");
		// 写出流信息
		int b = 0;
		try {
			//从服务器下载到本地
			PrintWriter out1 = response.getWriter();
			while ((b = fs.read()) != -1) {
				out1.write(b);
			}
			if(fs!=null){
				fs.close();
			}
			if(out1!=null){
				out1.close();
			}
			log.debug(excelName + " 文件下载完毕.");
		} catch (Exception e) {
			log.error(excelName + " 下载文件失败!.",e);
		}

	//	JOptionPane.showMessageDialog(null, "导出成功!");
	}

	//验证文件是否存在。
	public String checkFileName(String fileName, int i) {
		//验证文件是否存在
		String addressFileName;

		int index = fileName.lastIndexOf(".");
		int index2 = fileName.lastIndexOf(File.separator);
		String fileEnd = fileName.substring(index);
		String fileStart = fileName.substring(0, index);

		String fileFolder = fileStart.substring(0, index2);
		// 验证文件夹是否存在
		File folder = new File(fileFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String newFileName;
		if (i != 0) {
			newFileName = fileStart + "(" + i + ")" + fileEnd;
		} else {
			newFileName = fileName;
		}
		i++;
		File file = new File(newFileName);

		if (!file.exists()) {
			addressFileName = newFileName;
		} else {
			addressFileName = checkFileName(fileName, i);
		}

		return addressFileName;
	}


}


