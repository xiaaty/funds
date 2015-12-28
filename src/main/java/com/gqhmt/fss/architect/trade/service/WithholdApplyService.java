package com.gqhmt.fss.architect.trade.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.gqhmt.fss.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.fss.architect.account.exception.NeedSMSValidException;
import com.gqhmt.fss.architect.trade.bean.WithholdApplyBean;
import com.gqhmt.fss.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.fss.architect.trade.bean.WithholdApplyFormBean;
import com.gqhmt.fss.architect.trade.mapper.read.WithholdApplyReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.WithholdApplyWriteMapper;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.fss.pay.exception.LazyDealException;
import com.gqhmt.fss.pay.exception.ThirdpartyErrorAsyncException;
import com.gqhmt.util.CommonUtil;

/**
 * Filename: com.gq.funds.service Copyright: Copyright (c)2014 Company:
 * 冠群驰骋投资管理(北京)有限公司
 *
 * @author guofu
 * @version: 1.0
 * @since: JDK 1.7 Create at: 2015/04/08 22:29 Description:
 *         <p/>
 *         Modification History: Date Author Version Description
 *         -----------------------------------------------------------------
 *         2015/04/08 guofu 1.0 1.0 Version
 */
@Service
public class WithholdApplyService {

	@Resource
	private WithholdApplyReadMapper withholdApplyReadMapper;
	
	@Resource
	private WithholdApplyWriteMapper withholdApplyWriteMapper;

//	@Autowired
//	BankCardinfoService bankCardinfoService;

//	@Autowired
//	InvestmentService investmentService;

//	@Autowired
//	BankDealamountLimitService bankDealamountLimitService;

//	@Autowired
//	BidRepaymentService bidRepaymentService;
	
//	@Autowired
//	BidService bidService;
	 

	
	public void insert(WithholdApplyEntity entity) throws Exception {
		this.withholdApplyWriteMapper.insert(entity);
	}

	/**
	 * 根据条件对象查询并返回对应的代扣信息列表
	 * 
	 * @param pageReq
	 * @return
	 * @throws AppException
	 */
	public Page queryWithholdList(WithholdApplyBean withholdBean) throws Exception {
		return withholdApplyReadMapper.querywithholdByConditionList(withholdBean);
	}

	/**
	 * 根据编号获取代扣对象
	 * 
	 * @param id
	 * @return
	 */
	public WithholdApplyEntity getWithholdInfo(Long id) throws Exception {
		return withholdApplyReadMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新取代扣对象
	 * 
	 * @param entity
	 * @return
	 */
	public void update(WithholdApplyEntity entity) throws Exception {
		withholdApplyWriteMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 更新取代扣对象
	 * 
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void updateWithholdList(List<WithholdApplyEntity> list) throws Exception {
		for (WithholdApplyEntity entity : list) {
			withholdApplyWriteMapper.updateByPrimaryKeySelective(entity);
		}
	}

	/**
	 * 根据编号获取代扣对象
	 * 
	 * @param id
	 * @return
	 */
	public WithholdApplyFormBean getWithholdApplyFormBean(String id) throws Exception {
		WithholdApplyFormBean withholdApplyFormBean = new WithholdApplyFormBean();

		WithholdApplyEntity withholdApplyEntity = withholdApplyReadMapper.selectByPrimaryKey(id);

		// bean的copy
		BeanUtils.copyProperties(withholdApplyEntity, withholdApplyFormBean);
		//ClassReflection.reflectionAttr(withholdApplyEntity, withholdApplyFormBean);
		//withholdApplyFormBean.setId(String.valueOf(withholdApplyEntity.getId()));

		// 申请日期
		withholdApplyFormBean.setApplyTimeForm((CommonUtil.dateToString(withholdApplyEntity.getApplyTime())));
		/*TODO BankCardinfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(withholdApplyEntity.getBankId());
		BankDealamountLimitEntity bankDealamountLimitEntity = bankDealamountLimitService.queryBankLimitInfo(withholdApplyEntity.getThirdPartyType(), 1, bankCardinfoEntity.getParentBankId());
		// 上限额度
		if (bankDealamountLimitEntity != null) {
			withholdApplyFormBean.setLimitAmount(bankDealamountLimitEntity.getLimitAmount());
		} else {
			withholdApplyFormBean.setLimitAmount(new BigDecimal("500000.00"));
		}*/
		

		return withholdApplyFormBean;
	}

	/**
	 * 代扣
	 * 
	 */
	public String updateWithholdRech(String id, String sysUserId) throws Exception {

		WithholdApplyEntity withholdApplyEntity = null;

		BankCardInfoEntity bankCardinfoEntity = null;

		// 代扣申请明细
		withholdApplyEntity = withholdApplyReadMapper.selectByPrimaryKey(id);

		if (withholdApplyEntity.getApplyStatus() != 1) {
			throw new Exception("0001" + withholdApplyEntity.getCustName());
		}
		
//TODO		bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(withholdApplyEntity.getBankId());
//		BankDealamountLimitEntity bankDealamountLimitEntity = bankDealamountLimitService.queryBankLimitInfo(withholdApplyEntity.getThirdPartyType(), 1, bankCardinfoEntity.getParentBankId());
//
//		if (bankDealamountLimitEntity != null && withholdApplyEntity.getDrawAmount().compareTo(bankDealamountLimitEntity.getLimitAmount()) > 0) {
//			throw new Exception("0002" + withholdApplyEntity.getCustName());
//		}

		// 审核时间
		withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
		// 审核user
		withholdApplyEntity.setReviewUserId(Integer.parseInt(sysUserId));

		

		String returnCode = "0000";

		// 富友支付
		if (withholdApplyEntity.getThirdPartyType().intValue() == 2) {

			try {
				//WithholdApplyCallback withholdApplyCallback = new WithholdApplyCallback();
				// 代扣
//				AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_WITHHOLDING, ThirdPartyType.FUIOU, withholdApplyEntity.getCustId(), withholdApplyEntity.getAccountType(), bankCardinfoEntity, withholdApplyEntity.getDrawAmount(), withholdApplyEntity.getId().intValue());
			
			}catch (ThirdpartyErrorAsyncException e){
				//需要手动核对
				returnCode = "0002";
			} catch (NeedSMSValidException | LazyDealException e) {
				//需要异步处理
				returnCode = "0001";
			} catch (CommandParmException e) {
				throw new Exception("0008" + withholdApplyEntity.getCustName() + "_" + e.getMessage());
			}
		}

		//成功
		if (returnCode.equals("0000")) {
			// 申请状态 2-划扣成功
			withholdApplyEntity.setApplyStatus(2);
			withholdApplyEntity.setFactDrawAmount(withholdApplyEntity.getDrawAmount());
			// 审核时间
			withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
			// 审核user
			withholdApplyEntity.setReviewUserId(Integer.parseInt(sysUserId));
				
			//回调
			this.updateCallBackBussness(withholdApplyEntity);
			
		}  else if (returnCode.equals("0001")) {
			// 申请状态 4-代扣中
			withholdApplyEntity.setApplyStatus(4);
			withholdApplyEntity.setFactDrawAmount(withholdApplyEntity.getDrawAmount());
			// 审核时间
			withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
			// 审核user
			withholdApplyEntity.setReviewUserId(Integer.parseInt(sysUserId));

			//回调
			this.updateCallBackBussness(withholdApplyEntity);
		} else if ( returnCode.equals("0002") ) {
			// 申请状态 99-人工审核
			withholdApplyEntity.setApplyStatus(99);
			// 审核时间
			withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
			// 审核user
			withholdApplyEntity.setReviewUserId(Integer.parseInt(sysUserId));

		}
		
		// 更新申请状态
		update(withholdApplyEntity);

		return returnCode;

	}
	
	/**
	 * 回调各个业务逻辑处理
	 * @param id
	 */
	public void updateCallBackBussness(String id) {
		// 代扣申请明细
		WithholdApplyEntity withholdApplyEntity = withholdApplyReadMapper.selectByPrimaryKey(id);
		
		this.updateCallBack(withholdApplyEntity.getBussinessType(), withholdApplyEntity.getApplyStatus(), withholdApplyEntity.getBussinessId());
	}
	
	/**
	 * 金额拆分成功回调各个业务逻辑处理
	 * @param withholdApplyEntity
	 */
	public void updateCallBackBussness(WithholdApplyEntity withholdApplyEntity) {
		// 代扣申请明细
		this.updateCallBack(withholdApplyEntity.getBussinessType(), withholdApplyEntity.getApplyStatus(), withholdApplyEntity.getBussinessId());
	}


	/**
	 * 回调各个业务逻辑处理
	 * 
	 * @param bussinessType
	 * @param applyStatus
	 * @param bussinessId
	 */
	private void updateCallBack(int bussinessType, int applyStatus, int bussinessId) {
		/*TODO
		// 业务类型(1-线下出借合同代扣)的情况更新出借合同关联数据
		if (bussinessType == 1) {

			this.updateInvestInfo(bussinessId, applyStatus);

		} else if (bussinessType == 2) {
			updateRepaymentDeduct(bussinessId, applyStatus);

		} else if (bussinessType == 3) {
			updateRepaymentMortgageDeduct(bussinessId, applyStatus);

		} else  if (bussinessType == 6) {
//		    bidService.updateWithholdApplyStatus(bussinessId, applyStatus); 
        } 
		*/
	}

	/**
	 * 更新线下出借合同关联数据
	 * 
	 */
	private void updateInvestInfo(int investId, int applyStatus) {
		/*TODO
		// 成功
		if (applyStatus == 2) {
			// 划扣申请成 回调 出借合同更新方法
			investmentService.updateInvestInfoStatus(investId);

			// 划扣中
		} else if (applyStatus == 4) {

			InvestmentEntity investmentEntity = investmentService.queryInvestmentById(investId);
			// 债权状态 5-划扣中
			investmentEntity.setStatus(4);
			investmentService.saveOrUpdateInvestmentEntity(investmentEntity);

			// 取消
		} else if (applyStatus == 3) {

			InvestmentEntity investmentEntity = investmentService.queryInvestmentById(investId);
			// 债权状态 6-划扣失败
			investmentEntity.setStatus(6);

			investmentService.saveOrUpdateInvestmentEntity(investmentEntity);

			// 失败
		} else if (applyStatus == 5) {

			InvestmentEntity investmentEntity = investmentService.queryInvestmentById(investId);
			// 债权状态 6-划扣失败
			investmentEntity.setStatus(6);

			investmentService.saveOrUpdateInvestmentEntity(investmentEntity);
		}*/
	}
		
	private void updateRepaymentDeduct(int bidRepaymentId, int applyStatus) {
		/*TODO
		// 成功
		if (applyStatus == 2) {
			// 划扣申请成 回调 出借合同更新方法
			bidRepaymentService.updateCallbackRepaymentDeduct(bidRepaymentId, true, -1);

			// 划扣中
		} else if (applyStatus == 4) {

			// 取消
		} else if (applyStatus == 3) {

			bidRepaymentService.updateCallbackRepaymentDeduct(bidRepaymentId, false, -1);

			// 失败
		} else if (applyStatus == 5) {

			bidRepaymentService.updateCallbackRepaymentDeduct(bidRepaymentId, false, -1);
		}
		*/
	}
	
	private void updateRepaymentMortgageDeduct(int bidRepaymentId, int applyStatus) {
		/*
		// 成功
		if (applyStatus == 2) {
			// 划扣申请成 回调 出借合同更新方法
			bidRepaymentService.updateCallbackRepaymentMortgageDeduct(bidRepaymentId, true, -1);

			// 划扣中
		} else if (applyStatus == 4) {

			// 取消
		} else if (applyStatus == 3) {

			bidRepaymentService.updateCallbackRepaymentMortgageDeduct(bidRepaymentId, false, -1);

			// 失败
		} else if (applyStatus == 5) {

			bidRepaymentService.updateCallbackRepaymentMortgageDeduct(bidRepaymentId, false, -1);
		}
		 	*/
	}
	
	/**
	 * 更新失败状态
	 * 
	 */
	public void updateWithholdRechStatus(String id, String sysUserId) throws Exception {

		// 代扣申请明细
		WithholdApplyEntity withholdApplyEntity = withholdApplyReadMapper.selectByPrimaryKey(id);

		// 审核时间
		withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
		// 审核user
		withholdApplyEntity.setReviewUserId(Integer.parseInt(sysUserId));
		// 申请状态 5-失败
		withholdApplyEntity.setApplyStatus(5);

		// 更新申请状态
		update(withholdApplyEntity);

		// 业务类型(1-线下出借合同代扣,2-借款代扣,3-抵押标借款人代扣)
		/*TODO if (withholdApplyEntity.getBussinessType().intValue() == 1) {
			this.investmentService.updateInvestStatusById(withholdApplyEntity.getBussinessId());
		} else if (withholdApplyEntity.getBussinessType().intValue() == 2) {
			bidRepaymentService.updateCallbackRepaymentDeduct(withholdApplyEntity.getBussinessId(), false, -1);
		} else if (withholdApplyEntity.getBussinessType().intValue() == 3) {
			bidRepaymentService.updateCallbackRepaymentMortgageDeduct(withholdApplyEntity.getBussinessId(), false, -1);
		} else if (withholdApplyEntity.getBussinessType().intValue() == 6) {
            bidService.updateWithholdApplyStatus(withholdApplyEntity.getBussinessId(), 5);
        }*/

	}

	/**
	 * 保存
	 * 
	 */
	public String updateWithholdRechSave(WithholdApplyFormBean withholdApplyFormBean, String sysUserId) throws Exception {

		WithholdApplyEntity withholdApplyEntity = null;

		BankCardInfoEntity bankCardinfoEntity = null;
		String returnCode = "0000";
		// 代扣申请明细
		withholdApplyEntity = withholdApplyReadMapper.selectByPrimaryKey(withholdApplyFormBean.getId());
	
		if (withholdApplyEntity.getApplyStatus() != 1) {
			throw new Exception("0001" + withholdApplyEntity.getCustName());
		}
		
		// 审核不通过
		if (withholdApplyFormBean.getApplyStatus().intValue() == 3) {
			// 申请状态 3-取消
			withholdApplyEntity.setApplyStatus(3);
			returnCode = "0001";
		} else {

			//bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(withholdApplyEntity.getBankId());

			for (int i = 0; i < withholdApplyFormBean.getDrawAmountSplit().size(); i++) {
				// 富友支付
				if (withholdApplyEntity.getThirdPartyType().intValue() == 2) {
					//WithholdApplyCallback withholdApplyCallback = new WithholdApplyCallback();
					try {
						// 代扣
						//AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_WITHHOLDING, ThirdPartyType.FUIOU, withholdApplyEntity.getCustId(), withholdApplyEntity.getAccountType(), bankCardinfoEntity, withholdApplyFormBean.getDrawAmountSplit().get(i), withholdApplyEntity.getId().intValue(), withholdApplyCallback.getClass());
					}catch (ThirdpartyErrorAsyncException e){
						//需要手动核对
						returnCode = "0002";
					} catch (NeedSMSValidException | LazyDealException e) {
						//需要异步处理
						returnCode = "0001";
					} catch (CommandParmException e) {
						throw new Exception("0008" + e.getMessage(),e);
					}
				}
			}

			if ("0000".equals(returnCode)) {
				// 申请状态 2-划扣成功
				withholdApplyEntity.setApplyStatus(2);

			} else if ("0001".equals(returnCode)) {
				// 申请状态 4-代扣中
				withholdApplyEntity.setApplyStatus(4);
			} else if ("0002".equals(returnCode)) {
				// 申请状态 99-人工审核
				withholdApplyEntity.setApplyStatus(99);
			}
			
		}
		// 审核时间
		withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
		// 审核user
		withholdApplyEntity.setReviewUserId(Integer.parseInt(sysUserId));

		// 更新申请状态
		update(withholdApplyEntity);

		// 回调各个业务逻辑处理
		//this.updateCallBack(withholdApplyEntity.getBussinessType(), withholdApplyEntity.getApplyStatus(), withholdApplyEntity.getBussinessId());
		return returnCode;
	}

	/**
	 * 代扣审核接口回调用
	 * 
	 * @param entity
	 * @param 0 成功 1 失败
	 * @return
	 */
	public void updateWithholdCallback(Long id, String status) throws Exception {

		WithholdApplyEntity entity = withholdApplyReadMapper.selectByPrimaryKey(id);
        if(entity == null){
            return;
        }
        //成功
        if (entity.getApplyStatus() == 2 || entity.getApplyStatus() == 6) {
        	return;
        }
        
        //防止重复回调业务逻辑
        List<WithholdApplyEntity> holdlist = withholdApplyReadMapper.queryWithholdListByBussinessIdAndType(entity.getBussinessId(), entity.getBussinessType());
        
        boolean continueFlg = false;
        
        for (int i =0; i < holdlist.size(); i ++) {
        	if (holdlist.get(i).getApplyStatus() == 2) {
        		continueFlg =true;
        		break;
        	}
        }
        
        if (continueFlg) {
        	return;
        }
        
        
		if ("1".equals(status)) {
			entity.setApplyStatus(5); // 失败
		} else if ("0".equals(status)) {
			entity.setApplyStatus(2); // 成功
			entity.setFactDrawAmount(entity.getDrawAmount()); // 实际放款金额
		} else if ("2".equals(status)) {
			entity.setApplyStatus(4); // 挂起 自己失误失败
		}
		
		// 审核时间
		entity.setReviewTime(new Date(System.currentTimeMillis()));
		// 审核user
		//entity.setReviewUserId(Integer.parseInt(sysUserId));

		// 回调各个业务
		this.updateCallBack(entity.getBussinessType(), entity.getApplyStatus(), entity.getBussinessId());
		// 更新申请状态
		update(entity);
	}
	
	
	
	/**
	 * 更新申请状态
	 * 
	 */
	public void updateWithholdStatus(String id, String sysUserId, Integer status) throws Exception {
		
		// 代扣申请明细
		WithholdApplyEntity withholdApplyEntity = withholdApplyReadMapper.selectByPrimaryKey(id);
		
		if (withholdApplyEntity.getApplyStatus() != 1) {
			throw new Exception("0001" + withholdApplyEntity.getCustName());
		}


		// 审核时间
		withholdApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
		// 审核user
		withholdApplyEntity.setReviewUserId(Integer.parseInt(sysUserId));
		// 申请状态 5-失败
		withholdApplyEntity.setApplyStatus(status);

		// 更新申请状态
		update(withholdApplyEntity);

		this.updateCallBack(withholdApplyEntity.getBussinessType(), withholdApplyEntity.getApplyStatus(), withholdApplyEntity.getBussinessId());

	}
	
	
	
	/**
	 * 保存
	 * 
	 */
	public String updateWithholdRechSave2(WithholdApplyEntity withholdApplyEntity, BankCardInfoEntity bankCardinfoEntity, BigDecimal drawMoney, boolean callBackFlg) throws Exception {


		String returnCode = "0000";

		// // 富友支付
		if (withholdApplyEntity.getThirdPartyType().intValue() == 2) {
//			WithholdApplyCallback withholdApplyCallback = new WithholdApplyCallback();
			try {

				if (callBackFlg) {
					// 代扣
//					AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_WITHHOLDING, ThirdPartyType.FUIOU, withholdApplyEntity.getCustId(), withholdApplyEntity.getAccountType(), bankCardinfoEntity, drawMoney, withholdApplyEntity.getId().intValue(), withholdApplyCallback.getClass());
				} else {
					// 代扣
//					AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_WITHHOLDING, ThirdPartyType.FUIOU, withholdApplyEntity.getCustId(), withholdApplyEntity.getAccountType(), bankCardinfoEntity, drawMoney, withholdApplyEntity.getId().intValue());
				}
	
			} catch (ThirdpartyErrorAsyncException e){
				//需要手动核对
				returnCode = "0002";
			} catch (NeedSMSValidException | LazyDealException e) {
				//需要异步处理
				returnCode = "0001";
			} catch (CommandParmException e) {
				throw new Exception("0008" + e.getMessage(),e);
			}
		}

		return returnCode;
	}
	
	/**
	 * 结束代扣
	 * @param id
	 */
	public void updateReviewOver(int bussinessId,BigDecimal bigDecimal){
		//investmentService.updateInvestInfoStatus(bussinessId,bigDecimal);
	}
}
