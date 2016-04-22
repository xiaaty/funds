package com.gqhmt.fss.architect.loan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.extServInter.dto.loan.EnterAccount;
import com.gqhmt.extServInter.dto.loan.EnterAccountDto;
import com.gqhmt.extServInter.dto.loan.EnterAccountResponse;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.loan.bean.SettleListBean;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountEntity;
import com.gqhmt.fss.architect.loan.entity.FssEnterAccountParentEntity;
import com.gqhmt.fss.architect.loan.entity.FssSettleListEntity;
import com.gqhmt.fss.architect.loan.mapper.read.FssEnterAccountParentReadMapper;
import com.gqhmt.fss.architect.loan.mapper.read.FssEnterAccountReadMapper;
import com.gqhmt.fss.architect.loan.mapper.read.FssSettleListReadMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssEnterAccountParentWriteMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssEnterAccountWriteMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssSettleListWriteMapper;
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
	private FssEnterAccountReadMapper fssEnterAccountReadMapper;

	@Resource
	private FssEnterAccountParentWriteMapper fssEnterAccountParentWriteMapper;

	@Resource
	private FssEnterAccountParentReadMapper fssEnterAccountParentReadMapper;
	@Resource
	private FssBackplateService fssBackplateService;

	/**
	 * 
	 * author:jhz time:2016年3月7日 function：添加
	 */
	public void insertSettleListBean(SettleListBean settleListBean) throws FssException {
		FssSettleListEntity fssSettleListEntity = new FssSettleListEntity();
		fssSettleListEntity.setEnterId(settleListBean.getEnterId());
		fssSettleListEntity.setAccountType(settleListBean.getAccount_type());
		fssSettleListEntity.setSettleAmt(settleListBean.getSettle_amt());
		this.insert(fssSettleListEntity);
	}

	/**
	 * 
	 * author:jhz time:2016年3月7日 function：添加子表
	 */
	public void insert(FssSettleListEntity fssSettleListEntity) throws FssException {
		fssSettleListWriteMapper.insert(fssSettleListEntity);
	}

	/**
	 * 
	 * author:jhz time:2016年3月7日 function：添加子表
	 */
	public void insert(List<FssEnterAccountEntity> enterAccountEntities) throws FssException {
		fssEnterAccountWriteMapper.insertList(enterAccountEntities);
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
		List<SettleListBean> settleListBeans = new ArrayList<>();
		SettleListBean settleListBean = null;
		List<FssSettleListEntity> fssSettleList = fssSettleListReadMapper.getFssSettleList(id);
		for (FssSettleListEntity fssSettleListEntity : fssSettleList) {
			settleListBean = new SettleListBean();
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
		List<SettleListBean> settleListbeans = null;
		List<EnterAccount> enter_account = enterAccountDto.getEnter_account();
		//创建主表
		FssEnterAccountParentEntity createEnterAccountParentEntity = this.createEnterAccountParentEntity(enterAccountDto);
		for (EnterAccount enterAccount : enter_account) {
			fssEnterAccountEntity=this.createFssEnterAccountEntity(enterAccount, enterAccountDto, createEnterAccountParentEntity);
			// fssLoanWriteMapper.insertEnterAccount(fssEnterAccountEntity);
			fssEnterAccountWriteMapper.insertSelective(fssEnterAccountEntity);
			settleListbeans = enterAccount.getSettle_list();
			if (settleListbeans != null) {
				for (SettleListBean settleListBean : settleListbeans) {
					settleListBean.setEnterId(fssEnterAccountEntity.getId());
					this.insertSettleListBean(settleListBean);
				}
			}
		}
//		fssBackplateService.createFssBackplateEntity(enterAccountDto.getSeq_no(), enterAccountDto.getMchn(), enterAccountDto.getTrade_type());
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
		List<EnterAccount> enterAccounts = new ArrayList<>();
		EnterAccount enterAccount = null;
		//得到主表对象
		FssEnterAccountParentEntity enterAccountParent = fssEnterAccountParentReadMapper.getEnterAccountParent(map);
		//根据主表id得到子表集合对象
		List<FssEnterAccountEntity> enterAccountEntities = fssEnterAccountReadMapper.getEnterAccounts(enterAccountParent.getId());
		if(enterAccountEntities!=null){
		for (FssEnterAccountEntity enterAccountEntity : enterAccountEntities) {
			enterAccount = new EnterAccount();
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
		}
		enterAccountResponse.setEnter_account(enterAccounts);
		enterAccountResponse.setMchn(enterAccountParent.getMchnChild());
		enterAccountResponse.setSeq_no(enterAccountParent.getSeqNo());
		enterAccountResponse.setTrade_type(enterAccountParent.getTradeType());
		return enterAccountResponse;
	}

	/**
	 * 
	 * author:jhz time:2016年3月16日 function：根据parent_id查看该批流水详情
	 */
	public List<FssEnterAccountEntity> getEnterAccounts(Long id) {

		return fssEnterAccountReadMapper.getEnterAccounts(id);
	}

	/**
	 * 
	 * author:jhz time:2016年3月25日 function：创建入账主表
	 */
	public FssEnterAccountParentEntity createEnterAccountParentEntity(EnterAccountDto enterAccountDto)
			throws FssException {
		FssEnterAccountParentEntity enterAccountParentEntity = new FssEnterAccountParentEntity();
		enterAccountParentEntity.setSeqNo(enterAccountDto.getSeq_no());
		enterAccountParentEntity.setTradeType(enterAccountDto.getTrade_type());
		enterAccountParentEntity.setTradeCount(enterAccountDto.getEnter_account().size());
		enterAccountParentEntity.setSuccessCount(0);
		enterAccountParentEntity.setFiledCount(0);
		enterAccountParentEntity.setState("10090001");
		enterAccountParentEntity.setResultState("10080001");
		enterAccountParentEntity.setCreateTime(new Date());
		enterAccountParentEntity.setMotifyTime(new Date());
		enterAccountParentEntity.setMchnChild(enterAccountDto.getMchn());
		enterAccountParentEntity.setMchnParent(Application.getInstance().getParentMchn(enterAccountDto.getMchn()));
		fssEnterAccountParentWriteMapper.insertSelective(enterAccountParentEntity);
		return enterAccountParentEntity;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月25日
	 * function：创建子表对象
	 */
	public FssEnterAccountEntity createFssEnterAccountEntity(EnterAccount enterAccount,EnterAccountDto enterAccountDto,FssEnterAccountParentEntity enterAccountParentEntity) throws FssException{
		FssEnterAccountEntity enterAccountEntity = new FssEnterAccountEntity();
		enterAccountEntity.setParentId(enterAccountParentEntity.getId());
		enterAccountEntity.setContractId(enterAccount.getContract_id());
		enterAccountEntity.setAccNo(enterAccount.getAcc_no());
		enterAccountEntity.setMortgageeAccNo(enterAccount.getMortgagee_acc_no());
		enterAccountEntity.setTradeType(enterAccountDto.getTrade_type());
		enterAccountEntity.setSeqNo(enterAccountDto.getSeq_no());
		enterAccountEntity.setCreateTime(new Date());
		enterAccountEntity.setModifyTime(new Date());
		enterAccountEntity.setResult("10080001");
		enterAccountEntity.setSerialNumber(enterAccount.getSerial_number());
		enterAccountEntity.setAccountingNo(enterAccount.getAccounting_no());
		enterAccountEntity.setMchnParent(Application.getInstance().getParentMchn(enterAccountDto.getMchn()));
		enterAccountEntity.setMchnChild(enterAccountDto.getMchn());
		enterAccountEntity.setLoanPlatform(enterAccount.getLoan_platform());
		enterAccountEntity.setContractNo(enterAccount.getContract_no());
		return enterAccountEntity;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年3月26日
	 * function：得到主表列表
	 */
	public List<FssEnterAccountParentEntity> getEnterAccountParentEntities(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		return fssEnterAccountParentReadMapper.getEnterAccountParentList(map);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月7日
	 * function：根据交易状态查询所有处于新增状态的主表列表
	 */
	public List<FssEnterAccountParentEntity> getEnterAccountParentByState() {
		// TODO Auto-generated method stub
		return fssEnterAccountParentReadMapper.getEnterAccountParentByState();
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年4月7日
	 * function：修改主表执行条数，修改时间
	 * @throws FssException 
	 */
	public void updateExecuteCount(FssEnterAccountEntity fssEnterAccountEntity) throws FssException{
		fssEnterAccountEntity.setModifyTime(new Date());
		fssEnterAccountParentWriteMapper.updateEnterParent(fssEnterAccountEntity);
		this.checkExecuteCount(fssEnterAccountEntity.getParentId());
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月7日
	 * function：修改主表状态
	 * @throws FssException 
	 */
	public void checkExecuteCount(Long parentId) throws FssException{
		FssEnterAccountParentEntity enterParent = this.getEnterParent(parentId);
		int successCount = this.getSuccessCount(parentId);
		//判断 应执行数量 == 已执行数量,如果相等,执行状态 修改
		if(enterParent.getTradeCount()<=enterParent.getSuccessCount()){
			if(enterParent.getTradeCount()==successCount){
			enterParent.setState("10080002");
			fssEnterAccountParentWriteMapper.updateByPrimaryKey(enterParent);
			}else if(successCount==0){
				enterParent.setState("10080010");
				fssEnterAccountParentWriteMapper.updateByPrimaryKey(enterParent);
			}else{
				enterParent.setState("10080003");
				fssEnterAccountParentWriteMapper.updateByPrimaryKey(enterParent);
			}
			fssBackplateService.createFssBackplateEntity(enterParent.getSeqNo(), enterParent.getMchnChild(), enterParent.getTradeType());
		}
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月7日
	 * function：通过id查询主表对象
	 */
	public FssEnterAccountParentEntity getEnterParent(Long id){
		return fssEnterAccountParentReadMapper.selectByPrimaryKey(id);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月7日
	 * function：通过父id得到该批次成功条数
	 */
	public int getSuccessCount(Long parentId){
		return fssEnterAccountReadMapper.getSuccessCount(parentId);
		
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月7日
	 * function：跑批要运行的入账方法
	 */
	public void updateEnterAccount() throws FssException{
		List<FssEnterAccountParentEntity> enterAccountParentByState = this.getEnterAccountParentByState();
		for (FssEnterAccountParentEntity EnterAccountParent : enterAccountParentByState) {
			List<FssEnterAccountEntity> enterAccounts = this.getEnterAccounts(EnterAccountParent.getId());
				for (FssEnterAccountEntity fssEnterAccountEntity : enterAccounts) {
					fssEnterAccountEntity.setResult("10080002");
					fssEnterAccountWriteMapper.updateByPrimaryKey(fssEnterAccountEntity);
					this.updateExecuteCount(fssEnterAccountEntity);
				}
		}
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月18日
	 * function：入账跑批
	 * @throws FssException 
	 */
	public void enterAccounting(FssEnterAccountParentEntity fssEnterAccountParentEntity) throws FssException {
		List<FssEnterAccountEntity> enterAccounts = this.getEnterAccounts(fssEnterAccountParentEntity.getId());
		fssEnterAccountParentEntity.setState("10080002");
		fssEnterAccountParentWriteMapper.updateByPrimaryKey(fssEnterAccountParentEntity);
		
		//TODO：入账
		fssBackplateService.createFssBackplateEntity(fssEnterAccountParentEntity.getSeqNo(), fssEnterAccountParentEntity.getMchnChild(), fssEnterAccountParentEntity.getTradeType());
	}
	
}
