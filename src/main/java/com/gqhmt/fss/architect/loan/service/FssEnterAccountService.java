package com.gqhmt.fss.architect.loan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.loan.EnterAccount;
import com.gqhmt.extServInter.dto.loan.EnterAccountDto;
import com.gqhmt.extServInter.dto.loan.EnterAccountResponse;
import com.gqhmt.fss.architect.loan.bean.EnterAccountBean;
import com.gqhmt.fss.architect.loan.bean.SettleListBean;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountEntity;
import com.gqhmt.fss.architect.loan.entity.FssSettleListEntity;
import com.gqhmt.fss.architect.loan.mapper.read.FssEnterAccountReadMapper;
import com.gqhmt.fss.architect.loan.mapper.read.FssSettleListReadMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssEnterAccountWriteMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssSettleListWriteMapper;
import com.gqhmt.fss.architect.merchant.entity.MerchantEntity;
import com.gqhmt.fss.architect.merchant.service.MerchantService;

/**
 * 
 * Filename: com.gqhmt.extServInter.dto.account.CreateAccountByFuiou Copyright:
 * Copyright (c)2015 Company: 冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7 Create at: 2016年3月7日 Description:
 *         <p/>
 *         入账接口（批量）
 *         <p/>
 *         入账回盘
 *         <p/>
 *         入账收费 Modification History: Date Author Version Description
 *         -----------------------------------------------------------------
 *         2016年3月7日 jhz 1.0 1.0 Version
 */
@Service
public class FssEnterAccountService {
	@Resource
	private MerchantService merchantService;
	
	@Resource
	private FssSettleListReadMapper fssSettleListReadMapper;
	
	@Resource
	private FssEnterAccountWriteMapper fssEnterAccountWriteMapper;

	@Resource
	private FssSettleListWriteMapper fssSettleListWriteMapper;

	@Resource
	private FssEnterAccountReadMapper enterAccountReadMapper;

	/**
	 * 
	 * author:jhz time:2016年3月7日 function：添加
	 */
	public void insertSettleListBean(SettleListBean settleListBean) throws FssException {
		FssSettleListEntity fssSettleListEntity=new FssSettleListEntity();
		fssSettleListEntity.setEnterId(settleListBean.getEnterId());
		fssSettleListEntity.setAccountType(settleListBean.getAccount_type());
		fssSettleListEntity.setSettleAmt(settleListBean.getSettle_amt());
		this.insert(fssSettleListEntity);
	}
	/**
	 * 
	 * author:jhz time:2016年3月7日 function：添加
	 */
	public void insert(FssSettleListEntity fssSettleListEntity) throws FssException {
		fssSettleListWriteMapper.insert(fssSettleListEntity);
	}

	/**
	 * 
	 * author:jhz time:2016年3月7日 function：通过id得到费用列表
	 */
	public List<FssSettleListEntity> getsettleList(Long id) throws FssException {
		return fssSettleListReadMapper.getFssSettleList(id);
	}
	/**
	 * 
	 * author:jhz time:2016年3月7日 function：通过id得到费用列表
	 */
	public List<SettleListBean> getsettleListBean(Long id) throws FssException {
		List<SettleListBean> settleListBeans=new ArrayList<>();
		SettleListBean settleListBean=null;
		 List<FssSettleListEntity> fssSettleList = fssSettleListReadMapper.getFssSettleList(id);
		 for (FssSettleListEntity fssSettleListEntity : fssSettleList) {
			 settleListBean=new SettleListBean();
			 settleListBean.setAccount_type(fssSettleListEntity.getAccountType());
			 settleListBean.setSettle_amt(fssSettleListEntity.getSettleAmt());
			 settleListBeans.add(settleListBean);
		}
		 return settleListBeans;
	}

	/**
	 * 
	 * author:jhz time:2016年3月9日 function：入账接口（批量）
	 */
	public void insertEnterAccount(EnterAccountDto enterAccountDto) throws FssException {
		FssEnterAccountEntity fssEnterAccountEntity = null;
		List<SettleListBean> settleListbeans= null;
		for (EnterAccount enterAccount : enterAccountDto.getEnter_account()) {
			MerchantEntity findMerchantByMchnNo = merchantService.findMerchantByMchnNo(enterAccountDto.getMchn());
			fssEnterAccountEntity = new FssEnterAccountEntity();
			fssEnterAccountEntity.setAccNo(enterAccount.getAcc_no());
			fssEnterAccountEntity.setLoanPlatform(enterAccount.getLoan_platform());
			fssEnterAccountEntity.setAccountingNo(enterAccount.getAccounting_no());
			fssEnterAccountEntity.setContractId(enterAccount.getContract_id());
			fssEnterAccountEntity.setContractNo(enterAccount.getContract_no());
			fssEnterAccountEntity.setCreateTime(new Date());
			fssEnterAccountEntity.setMchnChild(enterAccountDto.getMchn());
			fssEnterAccountEntity.setMchnParent(findMerchantByMchnNo.getParentNo());
			fssEnterAccountEntity.setMortgageeAccNo(enterAccount.getMortgagee_acc_no());
			fssEnterAccountEntity.setSeqNo(enterAccountDto.getSeq_no());
			fssEnterAccountEntity.setSerialNumber(enterAccount.getSerial_number());
			fssEnterAccountEntity.setTradeType(enterAccountDto.getTrade_type());
//			long insertEnterAccount = fssLoanWriteMapper.insertEnterAccount(fssEnterAccountEntity);
			long insertEnterAccount = fssEnterAccountWriteMapper.insertEnterAccount(fssEnterAccountEntity);

			settleListbeans = enterAccount.getSettle_list();
			if (settleListbeans != null) {
				for (SettleListBean settleListBean : settleListbeans) {
					settleListBean.setEnterId(insertEnterAccount);
					this.insertSettleListBean(settleListBean);
				}
			}
		}
	}

	/**
	 * 
	 * author:jhz time:2016年3月8日 function：入账回盘
	 */
	public EnterAccountResponse getResponse(String mchnNo, String seqNo) throws FssException {
		EnterAccountResponse enterAccountResponse = new EnterAccountResponse();
		Map<String, String> map = new HashMap<>();
		map.put("mchnNo", mchnNo);
		map.put("seqNo", seqNo);
		List<EnterAccount> enterAccounts=new ArrayList<>();
		EnterAccount enterAccount=null;
		List<FssEnterAccountEntity>  enterAccountEntities = enterAccountReadMapper.getEnterAccount(map);
		for (FssEnterAccountEntity enterAccountEntity : enterAccountEntities) {
			enterAccount=new EnterAccount();
			enterAccount.setAcc_no(enterAccountEntity.getAccNo());
			enterAccount.setSerial_number(enterAccountEntity.getSerialNumber());
			enterAccount.setContract_id(enterAccountEntity.getContractId());
			enterAccount.setContract_no(enterAccountEntity.getContractNo());
			enterAccount.setAccounting_no(enterAccountEntity.getAccountingNo());
			enterAccount.setMortgagee_acc_no(enterAccountEntity.getMortgageeAccNo());
			enterAccount.setLoan_platform(enterAccountEntity.getLoanPlatform());
			enterAccount.setSettle_list(this.getsettleListBean(enterAccountEntity.getId()));
			enterAccounts.add(enterAccount);
		}
		enterAccountResponse.setEnterAccounts(enterAccounts);
		enterAccountResponse.setMchn(enterAccountEntities.get(0).getMchnChild());
		enterAccountResponse.setSeq_no(enterAccountEntities.get(0).getSeqNo());
		enterAccountResponse.setTrade_type(enterAccountEntities.get(0).getTradeType());
		return enterAccountResponse;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月15日
	 * function：得到入账表
	 */
	public List<EnterAccountBean> getEnterAccountEntities(Map map){
		return enterAccountReadMapper.getEnterAccountEntities(map);
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年3月15日
	 * function：根据流水号得到相应的每一批的交易成功数量
	 */
	public int getIsTrue(String seqNo) {
		return enterAccountReadMapper.getIsTrue(seqNo);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月16日
	 * function：根据流水号查看该批流水详情
	 */
	public List<FssEnterAccountEntity> getDetail(String seqNo) {
		
		return enterAccountReadMapper.getDetail(seqNo);
	}
}
