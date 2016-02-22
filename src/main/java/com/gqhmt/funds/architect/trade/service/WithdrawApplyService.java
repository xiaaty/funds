package com.gqhmt.funds.architect.trade.service;

import com.github.pagehelper.Page;
import com.gqhmt.pay.service.exception.NeedSMSValidException;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.exception.LazyDealException;
import com.gqhmt.pay.exception.ThirdpartyErrorAsyncException;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.trade.bean.WithdrawApplyBean;
import com.gqhmt.funds.architect.trade.entity.WithdrawApplyEntity;
import com.gqhmt.funds.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.funds.architect.trade.mapper.read.WithdrawApplyReadMapper;
import com.gqhmt.funds.architect.trade.mapper.write.WithdrawApplyWriteMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename: com.gq.p2p.account.service Copyright: Copyright (c)2014 Company:
 * 冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7 Create at: 2015/1/16 22:30 Description:
 *         <p/>
 *         Modification History: Date Author Version Description
 *         -----------------------------------------------------------------
 *         2015/1/16 于泳 1.0 1.0 Version
 */
@Service
public class WithdrawApplyService {

	@Resource
	private WithdrawApplyReadMapper withdrawApplyReadMapper;
	@Resource
	private WithdrawApplyWriteMapper withdrawApplyWriteMapper;

//	@Autowired
//	private TenderService tenderService;
//
//	@Autowired
//	private BidRepaymentService bidRepaymentService;
//	
//	@Autowired
//	private BidService bidService;
//	
//	@Autowired
//	BankDealamountLimitService bankDealamountLimitService;
//	
//	@Autowired
//	BankCardinfoService bankCardinfoService;
//	
//	@Autowired
//	DebtQuartzJobDayTask newQuartzJobDayTask;
//	
//	@Autowired
//	private AssignmentDebtService assignmentDebtService;
//	
//	@Autowired
//	private CreditorInfoService creditorInfoService;
	
	private static final Log log = LogFactory.getLog(WithdrawApplyService.class);

	public void insert(WithdrawApplyEntity entity) throws Exception {
		withdrawApplyWriteMapper.insertSelective(entity);
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年2月18日
	 * function：根据条件对象查询并返回对应的提现信息列表
	 */
	public List<WithholdApplyEntity> queryWithdrawList(WithdrawApplyBean withDrawBean) throws Exception {
		return withdrawApplyReadMapper.queryWithdrawByConditionList(withDrawBean);
	}

	/**
	 * 根据编号获取提现对象
	 * 
	 * @param id
	 * @return
	 */
	public WithdrawApplyEntity getWithDrawInfo(Long id) throws Exception {
		return withdrawApplyReadMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新取提现对象
	 * 
	 * @param entity
	 * @return
	 */
	public void update(WithdrawApplyEntity entity) throws Exception {
		withdrawApplyWriteMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 更新取提现对象
	 * 
	 * @param entity
	 * @return
	 */
	public void updateWithDrawEntity(Long id, String sysId, int status) throws Exception {
		WithdrawApplyEntity entity = getWithDrawInfo(id);
		entity.setApplyStatus(status); // 转账中
		entity.setReviewTime(new Date());
		entity.setReviewUserId(Integer.valueOf(sysId));
		update(entity);
	}

	/**
	 * 提现审核接口回调用
	 * 
	 * @param entity
	 * @param 0 成功 1 失败
	 * @return
	 */
	public void updateWithDrawCallback(Long id, String status) throws Exception {
		WithdrawApplyEntity entity = getWithDrawInfo(id);
		// 审核时间
		entity.setReviewTime(new Date(System.currentTimeMillis()));
		if ("1".equals(status)) {
			entity.setApplyStatus(5); // 失败
			/*TODO if (entity.getBussinessType().intValue() == 1) {
				tenderService.updateCallbackFundsWithdrawApplyStatus(entity.getBussinessId(), false, -1);
			}
			if (entity.getBussinessType().intValue() == 3) {
				bidRepaymentService.updateCallbackFundsWithdrawApplyStatus(entity.getBussinessId(), false, -1);
			}
			if (entity.getBussinessType().intValue() == 4) {
				newQuartzJobDayTask.updateAssignmentStatus(entity.getBussinessId(),3);
			}
			if (entity.getBussinessType().intValue() == 5) {
                bidService.updateCallbackFundsWithdrawApplyStatus(entity.getBussinessId(), false, -1);
            }*/
			
			//资金解冻 1借款客户   2 线下出借客户 3线上出借客户 96线下用应付款账户 
			//业务类型(1.满标提现 2-月月通代付申请,3-还款归还保证金，4-债权赎回提现申请;5-抵押标借款人提现)  
			/*TODO if (entity.getBussinessType().intValue() == 1) {
	            AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_UNFREEZE,ThirdPartyType.getThirdPartyType(2), entity.getCustId(), 1, entity.getDrawAmount(),
	                    "满标提现资金解冻" + entity.getDrawAmount());
			} else if (entity.getBussinessType().intValue() == 2) {
	            AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_UNFREEZE,ThirdPartyType.getThirdPartyType(2), entity.getCustId(), 96, entity.getDrawAmount(),
	                    "月月通代付资金解冻" + entity.getDrawAmount());
			} else if (entity.getBussinessType().intValue() == 3) {
	            AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_UNFREEZE,ThirdPartyType.getThirdPartyType(2), entity.getCustId(), 1, entity.getDrawAmount(),
	                    "归还保证金资金解冻" + entity.getDrawAmount());
			} else if (entity.getBussinessType().intValue() == 4) {
	            AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_UNFREEZE,ThirdPartyType.getThirdPartyType(2), entity.getCustId(), 96, entity.getDrawAmount(),
	                    "债权赎回资金解冻" + entity.getDrawAmount());
			} else if (entity.getBussinessType().intValue() == 5) {
	            AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_UNFREEZE,ThirdPartyType.getThirdPartyType(2), entity.getCustId(), 1, entity.getDrawAmount(),
	                    "抵押标借款人提现资金解冻" + entity.getDrawAmount());
			}*/

			
			
		} else if ("0".equals(status)) {
			entity.setApplyStatus(2); // 提现完成
			entity.setFactDrawAmount(entity.getDrawAmount());
			/*TODO if (entity.getBussinessType().intValue() == 1) {
				tenderService.updateCallbackFundsWithdrawApplyStatus(entity.getBussinessId(), true, -1);
			}
			if (entity.getBussinessType().intValue() == 3) {
				bidRepaymentService.updateCallbackFundsWithdrawApplyStatus(entity.getBussinessId(), true, -1);
			}
			if (entity.getBussinessType().intValue() == 4) {
				newQuartzJobDayTask.updateAssignmentStatus(entity.getBussinessId(),2);
			}
			if (entity.getBussinessType().intValue() == 5) {
                bidService.updateCallbackFundsWithdrawApplyStatus(entity.getBussinessId(), true, -1);
            }*/
		} else if ("2".equals(status)) {
			entity.setApplyStatus(4); // 提现中
			entity.setFactDrawAmount(entity.getDrawAmount());
		}
		update(entity);
	}

	/**
	 * 查询提现申请
	 * 
	 * @param debtId
	 */
	public WithdrawApplyEntity queryByDebtId(Integer debtId) {
		return withdrawApplyReadMapper.queryByDebtId(debtId);
	}

	/**
	 * 删除提现申请
	 * 
	 * @param entity
	 */
	public void removal(WithdrawApplyEntity entity) {
		withdrawApplyWriteMapper.delete(entity);
	}
	
	
	
	/**
	 * 代付
	 * 
	 */
	public String updateWithdrawDepute(String id, String sysUserId) throws Exception {

		WithdrawApplyEntity withdrawApplyEntity = null;

		// 提现申请明细
		withdrawApplyEntity = this.getWithDrawInfo(Long.valueOf(id));

		if (withdrawApplyEntity.getApplyStatus() != 1) {
			throw new Exception("0001" + withdrawApplyEntity.getCustName());
		}
		
		/*TODO BankCardinfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(withdrawApplyEntity.getBankId());
		BankDealamountLimitEntity bankDealamountLimitEntity = bankDealamountLimitService.queryBankLimitInfo(withdrawApplyEntity.getThirdPartyType().getKey(), 2, bankCardinfoEntity.getParentBankId());

		if (bankDealamountLimitEntity != null && withdrawApplyEntity.getDrawAmount().compareTo(bankDealamountLimitEntity.getLimitAmount()) > 0) {
			throw new Exception("0002" + withdrawApplyEntity.getCustName());
		}*/

		// 审核时间
		withdrawApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
		// 审核user
		withdrawApplyEntity.setReviewUserId(Integer.parseInt(sysUserId));

		

		String returnCode = "0000";

		// 富友支付
		if (withdrawApplyEntity.getThirdPartyType().intValue() ==2) {

			try {
//				WithdrawApplyCallback withdrawApplyCallback = new WithdrawApplyCallback();
				// 代扣
//				AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_AGENT_WITHDRAW,withdrawApplyEntity.getThirdPartyType(), withdrawApplyEntity, withdrawApplyCallback.getClass());
			
			}catch (ThirdpartyErrorAsyncException e){
				//需要手动核对
				returnCode = "0002";
			} catch (NeedSMSValidException | LazyDealException e) {
				//需要异步处理
				returnCode = "0001";
			} catch (CommandParmException e) {
				throw new Exception("0008" + e.getMessage());
			}
		}

		if ("0000".equals(returnCode)) {
			// 申请状态 2-提现成功
			withdrawApplyEntity.setApplyStatus(2);
			withdrawApplyEntity.setFactDrawAmount(withdrawApplyEntity.getDrawAmount()); // 实际提现金额
			
			if(null != withdrawApplyEntity.getDebtId()){
//			    AssignmentDebt assignment = assignmentDebtService.getAssignmentDebtById(withdrawApplyEntity.getDebtId());
//			    if(null != assignment && StringUtils.isNotBlank(assignment.getAcctId())){
//			        creditorInfoService.updateLoanSystemData(assignment.getAcctId());
//			    }
			}
			
		} else if ("0001".equals(returnCode)) {
			// 申请状态 4-转账中
			withdrawApplyEntity.setApplyStatus(4);
		} else if ("0002".equals(returnCode)) {
			// 申请状态 99-人工审核
			withdrawApplyEntity.setApplyStatus(99);
		}

		// 更新申请状态
		update(withdrawApplyEntity);

		return returnCode;
	}
	
	
	/**
	 * 保存
	 * 
	 */
	public String updateWithdrawRechSave(WithdrawApplyEntity withdrawApplyEntity, BigDecimal drawAmount,boolean callBackFlg) throws Exception {


		String returnCode = "0000";
		withdrawApplyEntity.setDrawAmount(drawAmount);
		// 富友支付
		if (withdrawApplyEntity.getThirdPartyType().intValue() == 2) {
//			WithdrawApplyCallback withdrawApplyCallback = new WithdrawApplyCallback();
			try {
				if (callBackFlg) {
					// 提现
//					AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_AGENT_WITHDRAW,withdrawApplyEntity.getThirdPartyType(), withdrawApplyEntity,withdrawApplyCallback.getClass());
				} else {
					// 提现
//					AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_AGENT_WITHDRAW,withdrawApplyEntity.getThirdPartyType(), withdrawApplyEntity);
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
	 * 定时任务晚间10点自动审核没有审批的提现申请
	 * 
	 */
	public Map<String, String> updateAutoWithdrawRech() {
		Map<String, String> map = new HashMap<String, String>();
		//查询未审批的数据
		List<WithdrawApplyEntity> withdrawList = this.withdrawApplyReadMapper.selectWithdrawApplyByStatus();
		
		//如果没有数据 不处理
		if (withdrawList == null || withdrawList.size() == 0) {
			map.put("totalCount", "0");
			map.put("successCount", "0");
			map.put("failCount", "0");
			map.put("totalAmount", "0");
			
			return map;
		}
		
		WithdrawApplyEntity withdrawApplyEntity = null;
		BankCardInfoEntity bankCardinfoEntity = null;
		BankCardInfoEntity bankDealamountLimitEntity = null;// TODO
		String returnCode = "";
		
		int totalCount=0;
		int successCount=0;
		int failCount =0;
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for (int i =0; i < withdrawList.size(); i++) {
			totalCount ++;
			withdrawApplyEntity= withdrawList.get(i);
			returnCode = "0000";
//			bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(withdrawApplyEntity.getBankId());
//			bankDealamountLimitEntity = bankDealamountLimitService.queryBankLimitInfo(withdrawApplyEntity.getThirdPartyType().getKey(), 2, bankCardinfoEntity.getParentBankId());
			//如果代付金额小于银行最高上限
			if (bankDealamountLimitEntity != null) {// TODO  && withdrawApplyEntity.getDrawAmount().compareTo(bankDealamountLimitEntity.getLimitAmount()) <= 0
				// 审核时间
				withdrawApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
				// 审核user 0-系统自动
				withdrawApplyEntity.setReviewUserId(0);
				
				
				// 富有支付
				if (withdrawApplyEntity.getThirdPartyType().intValue() ==2) {

					try {
//						WithdrawApplyCallback withdrawApplyCallback = new WithdrawApplyCallback();
						// 代扣
//						AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_AGENT_WITHDRAW,withdrawApplyEntity.getThirdPartyType(), withdrawApplyEntity, withdrawApplyCallback.getClass());
					}catch (ThirdpartyErrorAsyncException e){
						//需要手动核对
						returnCode = "0002";
					} catch (NeedSMSValidException | LazyDealException e) {
						//需要异步处理
						returnCode = "0001";
					} catch (CommandParmException e) {
						returnCode = "0008";
						failCount++;
						log.error("提现自动审核失败:时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());
						//更新为失败状态
						try {
							this.updateWithDrawCallback(withdrawApplyEntity.getId(), "1");
						} catch (Exception e1) {
							log.error("提现自动审核失败(数据库更新失败):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e1.getMessage());
						}
						withdrawApplyEntity.setApplyStatus(5);//失败
					} catch (Exception e) {
						log.error("提现自动审核失败(其他):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());

					}
				}
				
				
				if ("0000".equals(returnCode)) {
					
					//输出统计用
					totalAmount = totalAmount.add(withdrawApplyEntity.getDrawAmount());
					
					
					// 申请状态 2-提现成功
					withdrawApplyEntity.setApplyStatus(2);
					withdrawApplyEntity.setFactDrawAmount(withdrawApplyEntity.getDrawAmount()); // 实际提现金额
					
					if(null != withdrawApplyEntity.getDebtId()){
//					    AssignmentDebt assignment = assignmentDebtService.getAssignmentDebtById(withdrawApplyEntity.getDebtId());
//					    if(null != assignment && StringUtils.isNotBlank(assignment.getAcctId())){
//					        creditorInfoService.updateLoanSystemData(assignment.getAcctId());
//					    }
					}
				} else if ("0001".equals(returnCode)) {
					totalAmount = totalAmount.add(withdrawApplyEntity.getDrawAmount());
					// 申请状态 4-转账中
					withdrawApplyEntity.setApplyStatus(4);
				} else if ("0002".equals(returnCode)) {
					totalAmount = totalAmount.add(withdrawApplyEntity.getDrawAmount());
					// 申请状态 99-人工审核
					withdrawApplyEntity.setApplyStatus(99);
				}

				// 更新申请状态
				if (!"0000".equals(returnCode) && !"0008".equals(returnCode)) {
					try {
						update(withdrawApplyEntity);
					} catch (Exception e) {
						log.error("提现自动审核失败(数据库更新失败):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());
					}
				}
				
				//金额超过银行代付单笔上限  && withdrawApplyEntity.getDrawAmount().compareTo(bankDealamountLimitEntity.getLimitAmount()) > 0
			} else if (bankDealamountLimitEntity != null)  {
				
				BigDecimal bg[] = withdrawApplyEntity.getDrawAmount().divideAndRemainder(new BigDecimal(0));//TODO bankDealamountLimitEntity.getLimitAmount()
				
				int splitCount = bg[0].intValue();
				BigDecimal lastwithDrawamount = bg[1];
				
				//判断是否除尽
				if (lastwithDrawamount.compareTo(BigDecimal.ZERO) > 0) {
					splitCount = splitCount + 1;
				} else {
//					lastwithDrawamount = bankDealamountLimitEntity.getLimitAmount();
				}
				
				//拆分处理
				BigDecimal preDrawAmount = withdrawApplyEntity.getDrawAmount();
				BigDecimal factDrawAmount = BigDecimal.ZERO; //实际代付金额 
				String returnSubCode = "0000";
				
				for (int j=0 ; j < splitCount; j++) {
					
					if (j != (splitCount-1) ) {
						withdrawApplyEntity.setDrawAmount(new BigDecimal(0));//TODO bankDealamountLimitEntity.getLimitAmount()
						// 富有支付
						if (withdrawApplyEntity.getThirdPartyType().intValue() ==2) {
							try {
								// 代扣
//								AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_AGENT_WITHDRAW,withdrawApplyEntity.getThirdPartyType(), withdrawApplyEntity);
							}catch (ThirdpartyErrorAsyncException e){
								//需要手动核对
								returnSubCode = "0002";
							} catch (NeedSMSValidException | LazyDealException e) {
								//需要异步处理
								returnSubCode = "0001";
							} catch (CommandParmException e) {
								returnSubCode = "0008";
								failCount++;
								log.error("提现自动审核(拆分)失败:时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());
								break;
							} catch (Exception e) {
								log.error("提现自动审核(拆分)失败(其他):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());
								break;
							}
						}
						
						
					} else {
						withdrawApplyEntity.setDrawAmount(lastwithDrawamount);
						// 富有支付
						if (withdrawApplyEntity.getThirdPartyType().intValue() ==2) {

							try {
//								WithdrawApplyCallback withdrawApplyCallback = new WithdrawApplyCallback();
								// 代扣
//								AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_AGENT_WITHDRAW,withdrawApplyEntity.getThirdPartyType(), withdrawApplyEntity,withdrawApplyCallback.getClass());
							
							}catch (ThirdpartyErrorAsyncException e){
								//需要手动核对
								returnSubCode = "0002";
							} catch (NeedSMSValidException | LazyDealException e) {
								//需要异步处理
								returnSubCode = "0001";
							} catch (CommandParmException e) {
								returnSubCode = "0008";
								failCount++;
								log.error("提现自动审核(拆分)失败:时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());
								break;
							} catch (Exception e) {
								log.error("提现自动审核(拆分)失败(其他):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());
								break;
							}
						}
					}
					
					factDrawAmount = factDrawAmount.add(withdrawApplyEntity.getDrawAmount());
				}
				
				//成功
				if (returnSubCode.equals("0000") && preDrawAmount.equals(factDrawAmount)) {
					//输出统计用
					totalAmount = totalAmount.add(factDrawAmount);
					
					if(null != withdrawApplyEntity.getDebtId()){
//					    AssignmentDebt assignment = assignmentDebtService.getAssignmentDebtById(withdrawApplyEntity.getDebtId());
//					    if(null != assignment && StringUtils.isNotBlank(assignment.getAcctId())){
//					        creditorInfoService.updateLoanSystemData(assignment.getAcctId());
//					    }
					}
				}  else if (returnSubCode.equals("0001") && preDrawAmount.equals(factDrawAmount)) {
					//输出统计用
					totalAmount = totalAmount.add(factDrawAmount);
					
					// 申请状态 4-代扣中
					withdrawApplyEntity.setApplyStatus(4);
					withdrawApplyEntity.setDrawAmount(factDrawAmount);
					withdrawApplyEntity.setFactDrawAmount(factDrawAmount);
					// 审核时间
					withdrawApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					withdrawApplyEntity.setReviewUserId(0);
  					//更新合同状态
					try {
						update(withdrawApplyEntity);
					} catch (Exception e) {
						log.error("提现自动审核(拆分)失败(数据库更新失败):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());

					}

				} else if (returnSubCode.equals("0002") ) {
					//输出统计用
					totalAmount = totalAmount.add(factDrawAmount);
					
					// 申请状态 99-人工审核
					withdrawApplyEntity.setApplyStatus(99);
					// 审核时间
					withdrawApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					withdrawApplyEntity.setReviewUserId(0);
					//更新合同状态
					try {
						update(withdrawApplyEntity);
					} catch (Exception e) {
						log.error("提现自动审核(拆分)失败(数据库更新失败):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());

					}
					//如果提现失败,更新合同状态为提现失败
				} else if ("0008".equals(returnSubCode) && factDrawAmount.equals(BigDecimal.ZERO)) {
					//更新为失败状态
					try {
						this.updateWithDrawCallback(withdrawApplyEntity.getId(), "1");
					} catch (Exception e1) {
						log.error("提现自动审核(拆分)失败(数据库更新失败):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e1.getMessage());
					}
				//如果部分成功
				} else if ("0008".equals(returnSubCode) && factDrawAmount.compareTo(BigDecimal.ZERO) > 0) {
					//输出统计用
					totalAmount = totalAmount.add(factDrawAmount);
					
					// 申请状态 6-部分成功
					withdrawApplyEntity.setApplyStatus(6);
					withdrawApplyEntity.setFactDrawAmount(factDrawAmount);
					// 审核时间
					withdrawApplyEntity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					withdrawApplyEntity.setReviewUserId(0);
					//更新合同状态
					try {
						update(withdrawApplyEntity);
					} catch (Exception e) {
						log.error("提现自动审核(拆分)失败(数据库更新失败):时间" + new Date() + " 客户名称=" + withdrawApplyEntity.getCustName() + ", 金额 =" + withdrawApplyEntity.getDrawAmount() + "代付失败,原因" + e.getMessage());

					}
				}
				
			}
		}
		

		map.put("totalCount", String.valueOf(totalCount));
		map.put("successCount", String.valueOf(totalCount-failCount));
		map.put("failCount", String.valueOf(failCount));
		map.put("totalAmount", totalAmount.toString());

		return map;
		
	}
	
}
