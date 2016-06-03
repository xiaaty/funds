package com.gqhmt.fss.architect.loan.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.loan.*;
import com.gqhmt.extServInter.dto.p2p.BidApplyDto;
import com.gqhmt.extServInter.dto.p2p.BidApplyResponse;
import com.gqhmt.extServInter.dto.p2p.RePaymentDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.fss.architect.loan.bean.FssLoanBean;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.mapper.read.FssFeeListReadMapper;
import com.gqhmt.fss.architect.loan.mapper.read.FssLoanReadMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssFeeListWriteMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssLoanWriteMapper;
import com.gqhmt.fss.architect.merchant.service.MerchantService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.*;

/**
 * Filename:    com.gq.p2p.customer.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 16:36
 * Description:
 * <p/>借款人放款接口
 * <p/>得到借款人放款回调对象（回盘）
 * <p/>抵押权人提现接口
 * <p/>抵押权人提现回盘
 * <p/>流标申请
 * <p/>流标回盘
 * <p/>放借款收费列表
 * <p/>入账接口（批量）
 * <p/>入账回盘
 * <p/>冠e通后台满标
 * <p/>冠e通后台回款
 * <p/>冠e通后台流标
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
@Service
public class FssLoanService {

    @Resource
    private FssLoanReadMapper fssLoanReadMapper;
    @Resource
    private FssLoanWriteMapper fssLoanWriteMapper;
    @Resource
    private MerchantService merchantService;
    @Resource
    private FssFeeListWriteMapper fssFeeListWriteMapper;
    @Resource
    private FssFeeListReadMapper fssFeeListReadMapper;
    @Resource
    private FssAccountService fssAccountService;
    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;
    @Resource
    private FundOrderService fundOrderService;
    @Resource
    private FundAccountService fundAccountService;
	 /**
	  * 
	  * author:jhz
	  * time:2016年3月7日
	  * function：添加费用
	  */
	 public void insert(FssFeeList feeList){
		 fssFeeListWriteMapper.insert(feeList);
	 }
	 /**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：通过loan_id得到相应的收费列表
	 */
	public List<FssFeeList> getFeeList(Long id) {
		return fssFeeListReadMapper.getFeeList(id);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月19日
	 * function：把List<FssFeeList>集合转变为List<LendingFeeListDto>
	 */
	public List<LendingFeeListDto> getFeeListDto(List<FssFeeList> feeList){
			List<LendingFeeListDto> feeListDtos=new ArrayList<>();
			LendingFeeListDto feeListDto=null;
			if(feeList.size()!=0){
			for (FssFeeList fee : feeList) {
				feeListDto=new LendingFeeListDto();
				feeListDto.setFee_amt(fee.getFeeAmt());
				feeListDto.setFee_type(fee.getFeeType());
				feeListDtos.add(feeListDto);
			}
			return feeListDtos;
			}else{
				return null;
			}
	}
    
    /**
     * 
     * author:jhz
     * time:2016年3月7日
     * function：借款人放款接口
     */
    public void insertLending(LendingDto dto)throws FssException{
    	FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(dto.getAcc_no());
    	FssLoanEntity fssLoanEntity=new FssLoanEntity();
    	fssLoanEntity.setCustNo(fssAccountByAccNo.getCustNo());
    	fssLoanEntity.setUserNo(fssAccountByAccNo.getUserNo());
		fssLoanEntity.setPayAmt(dto.getPay_amt());
		fssLoanEntity.setAccNo(dto.getAcc_no());
		fssLoanEntity.setContractAmt(dto.getContract_amt());
		fssLoanEntity.setSeqNo(dto.getSeq_no());
		fssLoanEntity.setMortgageeAccNo(dto.getMortgagee_acc_no());
		fssLoanEntity.setTradeType(dto.getTrade_type());
		fssLoanEntity.setContractId(dto.getContract_id());
		fssLoanEntity.setContractNo(dto.getContract_no());
		fssLoanEntity.setContractInterest(dto.getContract_interest());
		fssLoanEntity.setCreateTime(new Date());
		fssLoanEntity.setModifyTime(new Date());
		fssLoanEntity.setMchnChild(dto.getMchn());
		fssLoanEntity.setLoanPlatform(dto.getLoan_platform());
		fssLoanEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
		if("11090001".equals(dto.getTrade_type())){
			long insertLending = fssLoanWriteMapper.insertLending(fssLoanEntity);
		}else{
			long insertLending = fssLoanWriteMapper.insertFullBid(fssLoanEntity);
		}
//		feeList
		List<LendingFeeListDto> feeLists = dto.getFee_list();

		if(feeLists==null){
			return;
		}

		List<FssFeeList> fssFeeLists = new ArrayList<>();

		for (LendingFeeListDto feeListEntity: feeLists) {
			FssFeeList fssFeeList = new FssFeeList();
			fssFeeList.setLoanId(fssLoanEntity.getId());
			fssFeeList.setLoanPlatform(dto.getLoan_platform());
			fssFeeList.setFeeAmt(feeListEntity.getFee_amt());
			fssFeeList.setFeeType(feeListEntity.getFee_type());
			fssFeeLists.add(fssFeeList);
		}

		this.fssFeeListWriteMapper.insertList(fssFeeLists);
    }
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：得到借款人放款回调对象
	 */
	public LendingResponse getLendingResponse(String mchnNo, String seqNo)throws FssException {
		Map<String,String> map=new HashMap<>();
		map.put("mchnNo", mchnNo);
		map.put("seqNo", seqNo);
		LendingResponse response=new LendingResponse();
		FssLoanEntity loan = fssLoanReadMapper.getResponse(map);
		response.setMchn(loan.getMchnChild());
		response.setSeq_no(loan.getSeqNo());
		response.setTrade_type(loan.getTradeType());
		response.setContract_id(loan.getContractId());
		response.setContract_no(loan.getContractNo());
		response.setMortgagee_acc_no(loan.getMortgageeAccNo());
		response.setContract_amt(loan.getContractAmt());
		response.setContract_interest(loan.getContractInterest());
		response.setAcc_no(loan.getAccNo());
		response.setPay_amt(loan.getPayAmt());
		response.setResp_code(loan.getRepCode());
		response.setLoan_platform(loan.getLoanPlatform());
		List<LendingFeeListDto> feeListDto = getFeeListDto(this.getFeeList(loan.getId()));
		if(feeListDto!=null){
			response.setFee_list(feeListDto);
		}
		return response;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：抵押权人提现接口
	 * @return 
	 */
	public long insertmortgageeWithDraw(MortgageeWithDrawDto dto) throws FssException {
		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(dto.getMortgagee_acc_no());
		FssLoanEntity fssLoanEntity=new FssLoanEntity();
		fssLoanEntity.setCustNo(fssAccountByAccNo.getCustNo());
    	fssLoanEntity.setUserNo(fssAccountByAccNo.getUserNo());
		fssLoanEntity.setPayAmt(dto.getPay_amt());
    	fssLoanEntity.setContractAmt(dto.getContract_amt());
    	fssLoanEntity.setSeqNo(dto.getSeq_no());
    	fssLoanEntity.setAccNo(dto.getAcc_no());
    	fssLoanEntity.setMortgageeAccNo(dto.getMortgagee_acc_no());
    	fssLoanEntity.setTradeType(dto.getTrade_type());
    	fssLoanEntity.setContractId(dto.getContract_id());
    	fssLoanEntity.setContractNo(dto.getContract_no());
    	fssLoanEntity.setCreateTime(new Date());
    	fssLoanEntity.setModifyTime(new Date());
    	fssLoanEntity.setMchnChild(dto.getMchn());
    	fssLoanEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
    	return fssLoanWriteMapper.insertFullBid(fssLoanEntity);
		
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：抵押权人提现回盘
	 */
	public MortgageeWithDrawRespons getMortgageeWithDrawRespons(String mchnNo, String seqNo)throws FssException {
		Map<String,String> map=new HashMap<>();
		map.put("mchnNo", mchnNo);
		map.put("seqNo", seqNo);
		return fssLoanReadMapper.getMortgageeWithDrawRespons(map);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月8日
	 * function：流标申请
	 */
	public void insertfailedBidDto(FailedBidDto dto) throws FssException{
		FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(dto.getAcc_no());
		FssLoanEntity fssLoanEntity=new FssLoanEntity();
		fssLoanEntity.setCustNo(fssAccountByAccNo.getCustNo());
    	fssLoanEntity.setUserNo(fssAccountByAccNo.getUserNo());
    	fssLoanEntity.setContractId(dto.getContract_id());
    	fssLoanEntity.setContractNo(dto.getContract_no());
    	fssLoanEntity.setMortgageeAccNo(dto.getMortgagee_acc_no());
    	fssLoanEntity.setAccNo(dto.getAcc_no());
    	fssLoanEntity.setContractAmt(dto.getContract_amt());
		fssLoanEntity.setPayAmt(dto.getPay_amt());
		fssLoanEntity.setSeqNo(dto.getSeq_no());
		fssLoanEntity.setTradeType(dto.getTrade_type());
		fssLoanEntity.setLoanPlatform(dto.getLoan_platform());
		fssLoanEntity.setCreateTime(new Date());
		fssLoanEntity.setModifyTime(new Date());
		fssLoanEntity.setMchnChild(dto.getMchn());
		fssLoanEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
		//添加数据并返回ID
		if("11090013".equals(dto.getTrade_type())){
			//放款前流标
			long insertLending = fssLoanWriteMapper.insertAbortBid(fssLoanEntity);
			this.abort(fssLoanEntity);
		}else{
			//放款后流标
		long insertLending = fssLoanWriteMapper.insertFullBid(fssLoanEntity);
		}
//		feeList
		
			List<LendingFeeListDto> feeLists = dto.getFee_list();

			if(feeLists==null){
				return;
			}

			List<FssFeeList> fssFeeLists = new ArrayList<>();

			for (LendingFeeListDto feeListEntity: feeLists) {
				FssFeeList fssFeeList = new FssFeeList();
				fssFeeList.setLoanId(fssLoanEntity.getId());
				fssFeeList.setLoanPlatform(dto.getLoan_platform());
				fssFeeList.setFeeAmt(feeListEntity.getFee_amt());
				fssFeeList.setFeeType(feeListEntity.getFee_type());
				fssFeeLists.add(fssFeeList);
			}
			this.fssFeeListWriteMapper.insertList(fssFeeLists);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月8日
	 * function：流标回盘
	 */
	public FailedBidResponse getFailedBidResponse(String mchnNo, String seqNo)throws FssException {
		Map<String,String> map=new HashMap<>();
		map.put("mchnNo", mchnNo);
		map.put("seqNo", seqNo);
		FailedBidResponse response = new FailedBidResponse();
		FssLoanEntity loan=	fssLoanReadMapper.getResponse(map);
		response.setAcc_no(loan.getAccNo());
		response.setContract_amt(loan.getContractAmt());
		response.setContract_id(loan.getContractId());
		response.setContract_no(loan.getContractNo());
		response.setLoan_platform(loan.getLoanPlatform()); 
		response.setMchn(loan.getMchnChild());
		response.setMortgagee_acc_no(loan.getMortgageeAccNo());
		response.setPay_amt(loan.getPayAmt());
		response.setTrade_type(loan.getTradeType());
		response.setSeq_no(loan.getSeqNo());
		
		response.setFee_list(getFeeListDto(this.getFeeList(loan.getId())));
		return response;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人付款列表
	 */
	public List<FssLoanEntity> findBorrowerLoan(Map<String,String> map) {
		Map<String, String> map2=new HashMap<String, String>();
		if (map != null) {
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("type",map.get("type"));
			map2.put("status",map.get("status"));
			map2.put("custNo",map.get("custNo"));
			map2.put("contractNo", map.get("contractNo"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		return fssLoanReadMapper.findBorrowerLoan(map2);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月8日
	 * function：根据交易类型得到放款列表
	 */
	public List<FssLoanEntity> findByType(String type) {
		return fssLoanReadMapper.findByType(type);
	}
//	/**
//	 * 
//	 * author:jhz
//	 * time:2016年3月15日
//	 * function：通过交易类型查询交易列表
//	 */
//	public List<FssLoanEntity> findByTradeType(String tradeType,String stutas) {
//		return fssLoanReadMapper.findByTradeType(tradeType);
//	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：通过ID查询放款信息
	 */
	public FssLoanEntity getFssLoanEntityById(Long id) {
		// TODO Auto-generated method stub
		return fssLoanReadMapper.selectByPrimaryKey(id);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月27日
	 * function：交易类型.标的Id查询满标信息是否存在
	 */
	public FssLoanEntity getFssLoanEntityByBidBusiNo(String tradeType,String busiBidNo) {
		return fssLoanReadMapper.getFssLoanEntityByBidBusiNo(tradeType,busiBidNo);
	}
	/**
	 *
	 * author:jhz
	 * time:2016年5月30日
	 * function：（回款编号，交易类型，回款类型）查询回款信息是否存在
	 */
	public FssLoanEntity getLoanRepayment(String repayNo,String tradeType,String repayType) {
		return fssLoanReadMapper.getLoanRepayment(repayNo,tradeType,repayType);
	}

	/**.
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：修改
	 */
	public void update(FssLoanEntity fssLoanEntity) {
		fssLoanWriteMapper.updateByPrimaryKey(fssLoanEntity);
	}
	/**.
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：修改
	 */
	public void update(FssLoanEntity fssLoanEntity,String tradeStatus) {
		//成功
			fssLoanEntity.setResult(tradeStatus);
			fssLoanEntity.setStatus(tradeStatus);
		fssLoanEntity.setModifyTime(new Date());
		fssLoanWriteMapper.updateByPrimaryKey(fssLoanEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月23日
	 * function：添加
	 */
	public void insert(FssLoanEntity fssLoanEntity) {
		fssLoanWriteMapper.insert(fssLoanEntity);
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月23日
	 * function：冠e通后台 满标，流标，回款
	 * @throws FssException 
	 */
	public void insertBidApply(BidApplyDto dto) throws FssException {
		FssLoanEntity fssLoanEntity=new FssLoanEntity();
		if("11090004".equals(dto.getTrade_type())||"11090012".equals(dto.getTrade_type())){
			FssLoanEntity	fssLoanEntity1=this.getFssLoanEntityByBidBusiNo(dto.getTrade_type(),dto.getBusi_bid_no());
			if(fssLoanEntity1!=null)  throw  new FssException("90004011");
			fssLoanEntity.setTradeType(dto.getTrade_type());//交易类型
		}else {
			//不允许重复提交（回款编号，交易类型，回款类型）
			FssLoanEntity	fssLoanEntity1 = this.getLoanRepayment(dto.getRepayment_no(), dto.getTrade_type(), dto.getPayment_type());
			if(fssLoanEntity1!=null)  throw  new FssException("90004011");
			fssLoanEntity.setTradeType(dto.getPayment_type());//交易类型
		}
		fssLoanEntity.setContractId(dto.getBusi_bid_no());//标的编号
		fssLoanEntity.setMchnChild(dto.getMchn());//商户号
		fssLoanEntity.setContractAmt(dto.getContract_amt());//合同金额
		fssLoanEntity.setPayAmt(dto.getPayment_amt());//金额
		fssLoanEntity.setContractInterest(dto.getContract_interest());//合同利息
		fssLoanEntity.setContractNo(dto.getContract_no());
		fssLoanEntity.setAccNo(dto.getUser_id());//借款人客户id
		fssLoanEntity.setMortgageeAccNo(dto.getMortgagee_user_id());//抵押权人客户id
		String parentMchn = Application.getInstance().getParentMchn(dto.getMchn());
		fssLoanEntity.setMchnParent(parentMchn);//父商户号
		fssLoanEntity.setSeqNo(dto.getSeq_no());//流水号

		fssLoanEntity.setUserNo(dto.getPeriod());  	//期数
		fssLoanEntity.setRepMsg(dto.getRemark());	//备注
		fssLoanEntity.setTradeTypeParent(dto.getTrade_type());//交易类型
		fssLoanEntity.setBusiNo(dto.getRepayment_no());	//回款编号
		fssLoanEntity.setCreateTime(new Date());
		fssLoanEntity.setModifyTime(new Date());
		if ("11090012".equals(dto.getTrade_type())){//流标
		fssLoanWriteMapper.insertAbortBid(fssLoanEntity);
		this.abort(fssLoanEntity);
		}else if("11090004".equals(dto.getTrade_type())){//满标
			fssLoanEntity.setStatus("10050010");
			fssLoanWriteMapper.insert(fssLoanEntity);
		}else{//回款
			fssLoanEntity.setStatus("10050011");
			fssLoanWriteMapper.insert(fssLoanEntity);
		}
	}

	/**
	 * 获取需要满标转账的数据列表(信用标,抵押权人提现,新增状态)
	 * @return
     */
	public List<FssLoanEntity> findLoanBySettle(){
		return this.fssLoanReadMapper.findLoanBySettle();
	}


	/**
	 * 获取回款列表
	 * @return
	 */
	public List<FssLoanEntity> findLoanRepayment(){
		return this.fssLoanReadMapper.findLoanRepayment();
	}


	/**
	 * 获取流标列表
	 * @return
	 */
	public List<FssLoanEntity> findAbortBid() {
		return this.fssLoanReadMapper.findAbortBid();
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年4月5日
	 * function：修改费用状态
	 */
	public void updateFeeList(FssFeeList feeList) throws FssException{
		fssFeeListWriteMapper.updateByPrimaryKey(feeList);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月6日
	 * function：信用标退款
	 */
	public void abort(FssLoanEntity fssLoanEntity) throws FssException {
		FssAccountEntity fssAccountEntity =new FssAccountEntity();
		if("11090012".equals(fssLoanEntity.getTradeType())){
			fssAccountEntity.setCustId(Long.parseLong(fssLoanEntity.getAccNo()));
		}else{
		 fssAccountEntity = fssAccountService.getFssAccountByAccNo(fssLoanEntity.getAccNo());
		}
		FundAccountEntity toSFEntity = fundAccountService.getFundAccount(fssAccountEntity.getCustId(), GlobalConstants.ACCOUNT_TYPE_LOAN);
		FundOrderEntity fundOrderEntity =fundOrderService.createOrder(toSFEntity, null,fssLoanEntity.getPayAmt(),BigDecimal.ZERO, GlobalConstants.ORDER_ABORT_BID, fssLoanEntity.getId(), GlobalConstants.BUSINESS_BID,"2");
		fuiouFtpOrderService.addOrder(fundOrderEntity, 3);
		fundOrderService.updateOrder(fundOrderEntity, 6, "0002", "ftp异步处理");
//		throw new FssException("异步处理，等待回调通知");
	}
	
	//线下代扣
	/*public List<FssLoanBean> findLoanOffilne(String type) {
		return fssLoanReadMapper.findBorrowerLoanOffline(type);
	}*/
	public List<FssLoanBean> findLoanOffilne() {
		return fssLoanReadMapper.findBorrowerLoanOffline();
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月25日
	 * function：得到冠e通回调对象
	 */
	public BidApplyResponse getBidApplyResponse(String mchn, String seqNo)throws FssException {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<>();
		map.put("mchnNo", mchn);
		map.put("seqNo", seqNo);
		return fssLoanReadMapper.getBidApplyResponse(map);
	}
	
}	
