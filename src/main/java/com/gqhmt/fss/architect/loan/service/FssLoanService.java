package com.gqhmt.fss.architect.loan.service;

import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawRespons;
import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.EnterAccount;
import com.gqhmt.extServInter.dto.loan.EnterAccountDto;
import com.gqhmt.extServInter.dto.loan.EnterAccountResponse;
import com.gqhmt.extServInter.dto.loan.FailedBidDto;
import com.gqhmt.extServInter.dto.loan.FailedBidResponse;
import com.gqhmt.extServInter.dto.loan.LendingDto;
import com.gqhmt.extServInter.dto.loan.LendingResponse;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawDto;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountEntity;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.entity.FssSettleListEntity;
import com.gqhmt.fss.architect.loan.mapper.read.FssLoanReadMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssLoanWriteMapper;
import com.gqhmt.fss.architect.merchant.entity.MerchantEntity;
import com.gqhmt.fss.architect.merchant.service.MerchantService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
 * <p/>入账接口（批量）
 * <p/>入账回盘
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
    private FssFeeListService feeListService;
    @Resource
    private FssSettleListService fssSettleListService;
    
    /**
     * 
     * author:jhz
     * time:2016年3月7日
     * function：借款人放款接口
     */
    public void insertLending(LendingDto dto)throws FssException{
    	FssLoanEntity fssLoanEntity=new FssLoanEntity();
    	MerchantEntity findMerchantByMchnNo = merchantService.findMerchantByMchnNo(dto.getMchn());
		fssLoanEntity.setPayAmt(dto.getPay_amt());
		fssLoanEntity.setAccNo(dto.getAcc_no());
		fssLoanEntity.setContractAmt(dto.getContract_amt());
		fssLoanEntity.setSeqNo(dto.getSeq_no());
		fssLoanEntity.setMortgageeAccNo(dto.getMortgagee_acc_no());
		fssLoanEntity.setBusiNo(dto.getTrade_type());
		fssLoanEntity.setContractId(dto.getContract_id());
		fssLoanEntity.setCreateTime(new Date());
		fssLoanEntity.setMchnChild(dto.getMchn());
		fssLoanEntity.setLoanPlatform(dto.getLoan_platform());
		fssLoanEntity.setMchnParent(findMerchantByMchnNo.getParentNo());
		long insertLending = fssLoanWriteMapper.insertLending(fssLoanEntity);
//		feeList
		List<FssFeeList> feeLists = dto.getFeeLists();
		if(feeLists!=null){
			for (FssFeeList fssFeeList : feeLists) {
				fssFeeList.setId(insertLending);
				fssFeeList.setFeeType(fssFeeList.getFeeType());
				fssFeeList.setFeeAmt(fssFeeList.getFeeAmt() );
				feeListService.insert(fssFeeList);
				}
		}
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
		response.setFeeLists(feeListService.getFeeList(response.getId()));
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
		FssLoanEntity fssLoanEntity=new FssLoanEntity();
    	MerchantEntity findMerchantByMchnNo = merchantService.findMerchantByMchnNo(dto.getMchn());
    	fssLoanEntity.setPayAmt(dto.getPay_amt());
    	fssLoanEntity.setContractAmt(dto.getContract_amt());
    	fssLoanEntity.setSeqNo(dto.getSeq_no());
    	fssLoanEntity.setMortgageeAccNo(dto.getMortgagee_acc_no());
    	fssLoanEntity.setBusiNo(dto.getTrade_type());
    	fssLoanEntity.setContractId(dto.getContract_id());
    	fssLoanEntity.setCreateTime(new Date());
    	fssLoanEntity.setMchnChild(dto.getMchn());
    	fssLoanEntity.setMchnParent(findMerchantByMchnNo.getParentNo());
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
		FssLoanEntity fssLoanEntity=new FssLoanEntity();
    	MerchantEntity findMerchantByMchnNo = merchantService.findMerchantByMchnNo(dto.getMchn());
    	fssLoanEntity.setContractId(dto.getContract_id());
    	fssLoanEntity.setMortgageeAccNo(dto.getMortgagee_acc_no());
    	fssLoanEntity.setAccNo(dto.getAcc_no());
    	fssLoanEntity.setContractAmt(dto.getContract_amt());
		fssLoanEntity.setPayAmt(dto.getPay_amt());
		fssLoanEntity.setSeqNo(dto.getSeq_no());
		fssLoanEntity.setBusiNo(dto.getTrade_type());
		fssLoanEntity.setLoanPlatform(dto.getLoan_platform());
		fssLoanEntity.setCreateTime(new Date());
		fssLoanEntity.setMchnChild(dto.getMchn());
		fssLoanEntity.setMchnParent(findMerchantByMchnNo.getParentNo());
		//添加数据并返回ID
		long insertLending = fssLoanWriteMapper.insertLending(fssLoanEntity);
//		feeList
		
		try {
			List<FssFeeList> feeLists = dto.getFeeLists();
			if(feeLists!=null){
			for (FssFeeList fssFeeList : feeLists) {
				fssFeeList.setId(insertLending);
				fssFeeList.setFeeType(fssFeeList.getFeeType());
				fssFeeList.setFeeAmt(fssFeeList.getFeeAmt() );
				feeListService.insert(fssFeeList);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		failedBidResponse.setFeeLists(feeListService.getFeeList(failedBidResponse.getId()));
		return failedBidResponse;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月9日
	 * function：入账接口（批量）
	 */
	public void insertEnterAccount(EnterAccountDto enterAccountDto)throws FssException {
		FssEnterAccountEntity fssEnterAccountEntity=null;
		List<FssSettleListEntity> settleListEntities =null;
		for (EnterAccount enterAccount : enterAccountDto.getEnterAccounts()) {
			MerchantEntity findMerchantByMchnNo = merchantService.findMerchantByMchnNo(enterAccountDto.getMchn());
			fssEnterAccountEntity=new FssEnterAccountEntity();
			fssEnterAccountEntity.setAccNo(enterAccount.getAcc_no());	
			fssEnterAccountEntity.setAccounting_no(enterAccount.getAccounting_no());
			fssEnterAccountEntity.setContractId(enterAccount.getContract_id());	
			fssEnterAccountEntity.setCreateTime(new Date());	
			fssEnterAccountEntity.setMchnChild(enterAccountDto.getMchn());	
			fssEnterAccountEntity.setMchnParent(findMerchantByMchnNo.getParentNo());	
			fssEnterAccountEntity.setMortgageeAccNo(enterAccount.getMortgagee_acc_no());	
			fssEnterAccountEntity.setSeqNo(enterAccountDto.getSeq_no());
			fssEnterAccountEntity.setSerialNumber(enterAccount.getSerial_number());	
			fssEnterAccountEntity.setBusiNo(enterAccountDto.getTrade_type());
			long insertEnterAccount = fssLoanWriteMapper.insertEnterAccount(fssEnterAccountEntity);
			settleListEntities= enterAccount.getSettleListEntities();
			if(settleListEntities!=null){
				for (FssSettleListEntity fssSettleListEntity : settleListEntities) {
					fssSettleListEntity.setId(insertEnterAccount);
					fssSettleListService.insert(fssSettleListEntity);
				}
			}
		}
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月8日
	 * function：入账回盘
	 */
	public EnterAccountResponse getResponse(String mchnNo,String seqNo)throws FssException  {
		EnterAccountResponse enterAccountResponse=new EnterAccountResponse();
		Map<String,String> map=new HashMap<>();
		map.put("mchnNo", mchnNo);
		map.put("seqNo", seqNo);
		List<EnterAccount> enterAccounts=null;
				enterAccounts= fssLoanReadMapper.getEnterAccount(map);
				for (EnterAccount enterAccount : enterAccounts) {
					enterAccount.setSettleListEntities(fssSettleListService.getFeeList(enterAccount.getId()));
					enterAccounts.add(enterAccount);
				}
			enterAccountResponse.setEnterAccounts(enterAccounts);
		return enterAccountResponse;
	}
}	
