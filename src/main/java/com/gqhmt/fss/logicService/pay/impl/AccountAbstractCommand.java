package com.gqhmt.fss.logicService.pay.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gqhmt.business.architect.loan.service.BidRepaymentService;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.business.architect.loan.service.BonusService;
import com.gqhmt.business.architect.loan.service.InvestmentService;
import com.gqhmt.business.architect.loan.service.PointsAccountService;
import com.gqhmt.business.architect.loan.service.TenderService;
import com.gqhmt.fss.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.account.exception.CreateAccountFailException;
import com.gqhmt.fss.architect.customer.service.BankCardInfoService;
import com.gqhmt.fss.architect.customer.service.FssChangeCardService;
import com.gqhmt.fss.architect.customer.service.CustomerInfoService;
import com.gqhmt.fss.architect.account.service.FundAccountService;
import com.gqhmt.fss.architect.account.service.FundSequenceService;
import com.gqhmt.fss.architect.trade.service.FundTradeService;
import com.gqhmt.fss.architect.account.service.FundWithrawChargeService;
import com.gqhmt.fss.architect.trade.service.FundsRecordService;
import com.gqhmt.fss.architect.order.entity.FundOrderEntity;
import com.gqhmt.fss.architect.order.service.FundOrderService;
import com.gqhmt.fss.architect.trade.service.WithdrawApplyService;
import com.gqhmt.fss.architect.trade.service.WithholdApplyService;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.ServiceLoader;

/**
 * Filename: com.gq.p2p.interactions.account Copyright: Copyright (c)2014
 * Company: 冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7 Create at: 2015/2/9 15:38 Description:
 *         <p/>
 *         Modification History: Date Author Version Description
 *         -----------------------------------------------------------------
 *         2015/2/9 于泳 1.0 1.0 Version
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, noRollbackFor = { CommandParmException.class }, readOnly = false)
abstract class AccountAbstractCommand {

	protected FundSequenceService sequenceService = ServiceLoader.get(FundSequenceService.class);
	protected FundAccountService fundAccountService = ServiceLoader.get(FundAccountService.class);
	protected FundOrderService fundOrderService = ServiceLoader.get(FundOrderService.class);
	protected FssChangeCardService changeCardService = ServiceLoader.get(FssChangeCardService.class);
	protected CustomerInfoService customerInfoService = ServiceLoader.get(CustomerInfoService.class);
	protected FundTradeService fundTradeService = ServiceLoader.get(FundTradeService.class);
	protected WithdrawApplyService withdrawApplyService = ServiceLoader.get(WithdrawApplyService.class);
	protected BankCardInfoService bankCardInfoService = ServiceLoader.get(BankCardInfoService.class);
	protected BidService bidService = ServiceLoader.get(BidService.class);
	protected TenderService tenderService = ServiceLoader.get(TenderService.class);
//	protected NoticeService noticeService = ServiceLoader.get(NoticeService.class);
	protected InvestmentService investmentService = ServiceLoader.get(InvestmentService.class);
	protected BidRepaymentService bidRepayment = ServiceLoader.get(BidRepaymentService.class);
	protected WithholdApplyService withholdApplyService = ServiceLoader.get(WithholdApplyService.class);
	protected FundsRecordService fundsRecordService = ServiceLoader.get(FundsRecordService.class);
	protected BidRepaymentService bidRepaymentService = ServiceLoader.get(BidRepaymentService.class);
//	protected DebtQuartzJobDayTask newQuartzJobDayTask = ServiceLoader.get(DebtQuartzJobDayTask.class);

	protected BonusService bonusService = ServiceLoader.get(BonusService.class);

    protected FundWithrawChargeService fundWithrawChargeService = ServiceLoader.get(FundWithrawChargeService.class);

	protected PointsAccountService pointsAccountService = ServiceLoader.get(PointsAccountService.class);

//	protected PromoteService promoteService =  ServiceLoader.get(PromoteService.class);

	/*---------------------------------------------------------账户信息----------------------------------------------------------------*/
	/**
	 * 获取主账户
	 * 
	 * @param cusID
	 * @return
	 */
	protected final FundAccountEntity getPrimaryAccount(int cusID, boolean isNull) throws CommandParmException {
		FundAccountEntity primaryAccount = fundAccountService.getFundAccount(cusID, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		if (primaryAccount == null && isNull) {
			throw new CommandParmException("主账户不存在");
		}
		return primaryAccount;
	}

	/**
	 * 获取其他账户信息
	 * 
	 * @param cusID
	 * @param type
	 * @return
	 */
	protected final FundAccountEntity getFundAccount(int cusID, int type, boolean isNull) throws CommandParmException {
		FundAccountEntity entity = null;
		if (cusID < 100) {
			entity = fundAccountService.getFundAccount(cusID, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		} else {
			entity = fundAccountService.getFundAccount(cusID, type);
		}
		if (entity == null && isNull) {
			throw new CommandParmException("" + GlobalConstants.accountType.get(type) + "不存在");
		}
		return entity;
	}

	/**
	 * 获取其他账户信息
	 * 
	 * @param userName
	 * @param type
	 * @return
	 */
	protected final FundAccountEntity getFundAccount(String userName, int type) throws CommandParmException {
		FundAccountEntity entity = null;
		entity = fundAccountService.getFundAccount(userName, type);
		if (entity == null) {
			entity = fundAccountService.getFundAccount(userName, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		}
		return entity;
	}

	/**
	 * 创建主账户
	 * 
	 * @param customerInfoEntity
	 * @param userID
	 * @return
	 * @throws CreateAccountFailException 
	 */
	protected final FundAccountEntity createPrimaryAccount(CustomerInfoEntity customerInfoEntity, int userID) throws CreateAccountFailException {
		return fundAccountService.createCustomerAccount(customerInfoEntity, userID, GlobalConstants.ACCOUNT_TYPE_PRIMARY, 0l);
	}

	/**
	 * 创建所有子账户
	 * 
	 * @param customerInfoEntity
	 * @param userID
	 * @param primaryAccount
	 * @throws CreateAccountFailException 
	 */
	protected final void createAccount(CustomerInfoEntity customerInfoEntity, int userID, FundAccountEntity primaryAccount) throws CreateAccountFailException {
		Set<Integer> typeSet = GlobalConstants.accountType.keySet();
		for (int type : typeSet) {
			if (type == 0 || (customerInfoEntity.getId() < GlobalConstants.RESERVED_CUSTOMERID_LIMIT)) {
				continue;
			}
			FundAccountEntity entity = this.getFundAccount(customerInfoEntity.getId(), type, false);
			if (entity == null) {
				fundAccountService.createCustomerAccount(customerInfoEntity, userID, type, primaryAccount.getId());
			}
		}
	}

	protected final void updateAccount(FundAccountEntity primaryAccount) {
		this.fundAccountService.update(primaryAccount);
	}

	/************************************************* 账户操作完结 *****************************************************************************/

	/******************************************************** 客户信息 **********************************************************************************/
	protected final CustomerInfoEntity getCustomerInfo(int cusID) throws CommandParmException {
		CustomerInfoEntity customerInfoEntity = customerInfoService.queryCustomerById(cusID);
		if (customerInfoEntity == null) {
			throw new CommandParmException("客户不存在");
		}
		return customerInfoEntity;
	}

	/******************************************************** 客户信息操作结束 **********************************************************************************/

	/******************************************************** 资金流水信息 **********************************************************************************/

	protected final void hasEnoughBanlance(FundAccountEntity entity, BigDecimal amount) throws CommandParmException {
		BigDecimal bigDecimal = sequenceService.getSumAmount(entity.getId());
		if (bigDecimal.compareTo(amount) < 0) {
			throw new CommandParmException("账户余额不足");
		}
	}

	/******************************************************** 资金流水信息操作结束 **************************************************************************/

	/******************************************************** 订单信息 **********************************************************************************/
	protected final FundOrderEntity createOrder(FundAccountEntity primaryAccount, BigDecimal amount, int orderType, long sourceID, Integer sourceType, String thirdPartyType) throws CommandParmException {
		return this.createOrder(primaryAccount, null, amount, orderType, sourceID, sourceType, thirdPartyType);
	}

    protected final FundOrderEntity createOrder(FundAccountEntity primaryAccount, FundAccountEntity toAccountEntity,BigDecimal amount, int orderType, long sourceID, Integer sourceType, String thirdPartyType) throws CommandParmException {
        return this.createOrder(primaryAccount,toAccountEntity,amount,BigDecimal.ZERO,orderType,sourceID,sourceType,thirdPartyType);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, noRollbackFor = { CommandParmException.class }, readOnly = false)
	protected final FundOrderEntity createOrder(FundAccountEntity primaryAccount, FundAccountEntity toAccountEntity, BigDecimal amount,BigDecimal chargeAmount, int orderType, long sourceID, Integer sourceType, String thirdPartyType) throws CommandParmException {
		FundOrderEntity fundOrderEntity = new FundOrderEntity();
		fundOrderEntity.setAccountId(primaryAccount.getId());
		if (toAccountEntity != null) {
			fundOrderEntity.setToAccountId(toAccountEntity.getId());
		}
		fundOrderEntity.setOrderNo(fundOrderService.getOrderNo());
		fundOrderEntity.setCreateTime(new Date());
		fundOrderEntity.setOrderAmount(amount);
		fundOrderEntity.setOrderSource(sourceType);
		fundOrderEntity.setOrderFrormId(sourceID);
		// 订单类型(1-充值 2-提现 3-代偿 4-投标 5-转账 6-还款 7-流标)
		fundOrderEntity.setOrderType(orderType);
		fundOrderEntity.setThirdPartyType(thirdPartyType);
        fundOrderEntity.setChargeAmount(chargeAmount);
		fundOrderEntity.setOrderState(GlobalConstants.ORDER_STATUS_SUBMIT);
		try {
			fundOrderService.insert(fundOrderEntity);
		} catch (Exception e) {
			throw new CommandParmException("" + e.getMessage());
		}
		return fundOrderEntity;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, noRollbackFor = { CommandParmException.class }, readOnly = false)
	public final void updateOrder(FundOrderEntity fundOrderEntity, int status, String code, String msg) throws CommandParmException {
		fundOrderEntity.setOrderState(status);
		fundOrderEntity.setRetCode(code);
		fundOrderEntity.setRetMessage(msg);
		try {
			fundOrderService.update(fundOrderEntity);
		} catch (Exception e) {
			throw new CommandParmException(e.getMessage());
		}
	}

	/******************************************************** 订单信息操作结束 **************************************************************************/

	/******************************************************** 交易记录信息 **********************************************************************************/
	// "充值成功，充值金额 " + amount + "元"
	protected final void createFundTrade(FundAccountEntity entity, BigDecimal income, BigDecimal payment, int tradeType, String memo) throws RuntimeException {
		try {
			this.fundTradeService.addFundTrade(entity.getUserId(), entity.getId(), income, payment, tradeType, memo, entity.getCustId());
		} catch (Exception e) {
			throw new CommandParmException(e.getMessage());
		}
	}
	// "充值成功，充值金额 " + amount + "元"
	protected final void createFundTrade(FundAccountEntity entity, BigDecimal income, BigDecimal payment, int tradeType, String memo,long bonusAmont) throws RuntimeException {
		try {
			this.fundTradeService.addFundTrade(entity.getUserId(), entity.getId(), income, payment, tradeType, memo, entity.getCustId(),bonusAmont);
		} catch (Exception e) {
			throw new CommandParmException(e.getMessage());
		}
	}

	/******************************************************** 交易记录信息操作结束 **********************************************************************************/

	/******************************************************** 处理返回信息值 **********************************************************************************/

	/******************************************************** 提现申请信息 **********************************************************************************/

	/******************************************************** 提现申请操作结束 **********************************************************************************/

	/******************************************************** 消息通知信息 **********************************************************************************/

	/**
	 * 充值提现金额变动通知
	 * 
	 * @param noticeType
	 * @param entity
	 * @param amount
	 */
//	protected void sendNotice(NoticeService.NoticeType noticeType, FundAccountEntity entity, BigDecimal amount) {
//		this.noticeService.sendNotice(noticeType, entity.getUserId(), entity.getCustId(), amount.toPlainString());
//	}

	/**
	 * 其他消息通知
	 * 
	 * @param noticeType
	 * @param entity
	 * @param content
	 */
//	protected void sendNotice(NoticeService.NoticeType noticeType, FundAccountEntity entity, String content) {
//		this.noticeService.sendNotice(noticeType, entity.getUserId(), entity.getCustId(), content);
//	}

}
