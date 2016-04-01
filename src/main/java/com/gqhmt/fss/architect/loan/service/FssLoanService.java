package com.gqhmt.fss.architect.loan.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.extServInter.dto.loan.*;
import com.gqhmt.extServInter.dto.p2p.BidApplyDto;
import com.gqhmt.extServInter.dto.p2p.RePaymentDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.mapper.read.FssFeeListReadMapper;
import com.gqhmt.fss.architect.loan.mapper.read.FssLoanReadMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssFeeListWriteMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssLoanWriteMapper;
import com.gqhmt.fss.architect.merchant.service.MerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
			for (FssFeeList fee : feeList) {
				feeListDto=new LendingFeeListDto();
				feeListDto.setFee_amt(fee.getFeeAmt());
				feeListDto.setFee_type(fee.getFeeType());
				feeListDtos.add(feeListDto);
			}
			return feeListDtos;
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
		long insertLending = fssLoanWriteMapper.insertLending(fssLoanEntity);
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
		LendingResponse response = fssLoanReadMapper.getResponse(map);
		response.setFee_list(getFeeListDto(this.getFeeList(response.getId())));
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
    	fssLoanEntity.setMchnChild(dto.getMchn());
    	fssLoanEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
    	return fssLoanWriteMapper.insertLending(fssLoanEntity);
		
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
		fssLoanEntity.setMchnChild(dto.getMchn());
		fssLoanEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
		//添加数据并返回ID
		long insertLending = fssLoanWriteMapper.insertLending(fssLoanEntity);
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
		FailedBidResponse failedBidResponse = fssLoanReadMapper.getFailedBidResponse(map);
		failedBidResponse.setFee_list(getFeeListDto(this.getFeeList(failedBidResponse.getId())));
		return failedBidResponse;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：借款人付款列表
	 */
	public List<FssLoanEntity> findBorrowerLoan(Map map) {
		return fssLoanReadMapper.findBorrowerLoan(map);
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
	
	/**.
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：修改
	 */
	public void update(FssLoanEntity fssLoanEntity) {
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
	 * author:jhz
	 * time:2016年3月23日
	 * function：冠e通后台 满标
	 */
	public void insertFullBidApply(BidApplyDto bidApplyDto) throws FssException{
		FssLoanEntity fssLoanEntity=new FssLoanEntity();
		fssLoanEntity.setContractId(bidApplyDto.getBusi_bid_no());
		fssLoanEntity.setContractAmt(bidApplyDto.getContract_amt());
		fssLoanEntity.setPayAmt(bidApplyDto.getPayment_amt());
		fssLoanEntity.setContractInterest(bidApplyDto.getContract_interest());
		fssLoanEntity.setAccNo(bidApplyDto.getUser_id());
		fssLoanEntity.setMortgageeAccNo(bidApplyDto.getMortgagee_user_id());
		fssLoanEntity.setContractId(bidApplyDto.getBusi_bid_no());
		fssLoanEntity.setContractId(bidApplyDto.getBusi_bid_no());
		fssLoanEntity.setMchnChild(bidApplyDto.getMchn());
		String parentMchn = Application.getInstance().getParentMchn(bidApplyDto.getMchn());
		fssLoanEntity.setMchnParent(parentMchn);
		fssLoanEntity.setSeqNo(bidApplyDto.getSeq_no());
		fssLoanEntity.setTradeType(bidApplyDto.getTrade_type());
		fssLoanEntity.setCreateTime(new Date());
		fssLoanEntity.setStatus("10050001");
		fssLoanWriteMapper.insert(fssLoanEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月23日
	 * function：冠e通后台 回款
	 */
	public void insertRepaymentDto(RePaymentDto rePaymentDto) throws FssException {
		FssLoanEntity fssLoanEntity=new FssLoanEntity();
		fssLoanEntity.setContractId(rePaymentDto.getBusi_bid_no());	//标的编号
		fssLoanEntity.setBusiNo(rePaymentDto.getRepayment_no());	//回款编号	
		fssLoanEntity.setAccNo(rePaymentDto.getUser_id());	//借款人客户id
		fssLoanEntity.setMortgageeAccNo(rePaymentDto.getMortgagee_user_id());	//抵押权人客户id
		fssLoanEntity.setContractNo(rePaymentDto.getContract_no());
		fssLoanEntity.setUserNo(rePaymentDto.getPeriod());  	//期数
		fssLoanEntity.setPayAmt(rePaymentDto.getPayment_amt());//回款金额
		fssLoanEntity.setRepMsg(rePaymentDto.getRemark());	//备注
		fssLoanEntity.setTradeTypeParent(rePaymentDto.getPayment_type());//交易类型
		String parentMchn = Application.getInstance().getParentMchn(rePaymentDto.getMchn());
		fssLoanEntity.setMchnChild(rePaymentDto.getMchn());
		fssLoanEntity.setMchnParent(parentMchn);
		fssLoanEntity.setSeqNo(rePaymentDto.getSeq_no());
		fssLoanEntity.setTradeType(rePaymentDto.getTrade_type());
		fssLoanEntity.setCreateTime(new Date());
		fssLoanEntity.setStatus("10050001");
		fssLoanWriteMapper.insert(fssLoanEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月23日
	 * function：冠e通后台 流标
	 * @throws FssException 
	 */
	public void insertBidRepayApply(BidApplyDto BidApplyDto) throws FssException {
		FssLoanEntity fssLoanEntity=new FssLoanEntity();
		fssLoanEntity.setContractId(BidApplyDto.getBusi_bid_no());
		fssLoanEntity.setMchnChild(BidApplyDto.getMchn());
		String parentMchn = Application.getInstance().getParentMchn(BidApplyDto.getMchn());
		fssLoanEntity.setMchnParent(parentMchn);
		fssLoanEntity.setSeqNo(BidApplyDto.getSeq_no());
		fssLoanEntity.setTradeType(BidApplyDto.getTrade_type());
		fssLoanEntity.setCreateTime(new Date());
		fssLoanEntity.setStatus("10050001");
		fssLoanWriteMapper.insert(fssLoanEntity);
	}

	/**
	 * 获取需要满标转账的数据列表(信用标,抵押权人提现,新增状态)
	 * @return
     */
	public List<FssLoanEntity> findLoanBySettle(){
		return this.fssLoanReadMapper.findLoanBySettle();
	}
	

}	
