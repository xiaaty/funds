package com.gqhmt.controller.funds.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.business.architect.invest.service.InvestmentService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.trade.WithdrawDto;
import com.gqhmt.extServInter.service.trade.IWithdraw;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.trade.bean.WithdrawApplyBean;
import com.gqhmt.funds.architect.trade.entity.WithdrawApplyEntity;
import com.gqhmt.funds.architect.trade.entity.WithholdApplyEntity;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.funds.architect.trade.service.WithdrawApplyService;
import com.gqhmt.pay.exception.ThirdpartyErrorAsyncException;
import com.gqhmt.sys.beans.SysUsers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* <h1>提现管理控制类</h1>
* <p>
* 提现申请列表查询 提现申请审核
* </p>
* 
* @author jhz
* @version 1.0
* @createTime:2016-02-18 
*/
@Controller
public class FundsWithdrawApplyController {
	@Resource
	private WithdrawApplyService withdrawApplyService;
	
	@Resource
	private WithdrawApplyService withDrawService;
	@Resource
	private FundSequenceService fundSequenceService;
	@Resource
	private FundTradeService fundTradeService;
	@Resource
	private InvestmentService investmentService;
	@Resource
	private IWithdraw withdrawImpl;
	
	
//	@Resource
//	private AssignmentDebtService assignmentDebtService;
//	@Resource
//	private CreditorInfoService creditorInfoService;
	@Resource
	private BankCardInfoService bankCardinfoService;
//	@Resource
//	private BankDealamountLimitService bankDealamountLimitService;

//	@Autowired
//	private BidService bidService;
//
//	private static final Log log = LogFactory
//			.getLog(WithdrawApplyController.class);
   
	/**
	 * 
	 * author:jhz
	 * time:2016年2月18日
	 * function：根据条件查询并返回所有提现申请列表信息
	 * @throws Exception 
	 */
	@RequestMapping("/withdrawApply/queryWithdrawList")
	@AutoPage
	public String queryWithholdList(HttpServletRequest request,
			ModelMap model, WithdrawApplyBean withdrawApplyBean) throws Exception {
			List<WithholdApplyEntity> withDrawList= withdrawApplyService.queryWithdrawList(withdrawApplyBean);
			model.addAttribute("withdrawBean", withdrawApplyBean);
			model.addAttribute("page", withDrawList);
		return "funds/account/withDraw/withdraw_list";
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月26日
	 * function：提现申请审核
	 */
	@RequestMapping("/account/withdraw/reviewWithdrawbak")
	@ResponseBody
	public Object updateWithDraw(HttpServletRequest request,
			HttpServletResponse response, WithdrawApplyBean withDrawBean)
			throws IOException {
		WithdrawApplyEntity entity;
		WithdrawDto withdrawDto= new WithdrawDto();
		withdrawDto.setCust_no(withDrawBean.getCustId().toString());
		withdrawDto.setAmount(withDrawBean.getDrawAmount());
		withdrawDto.setProcedure_fee(withDrawBean.getProcedureFee());
		SysUsers user = (SysUsers) request.getSession().getAttribute(
				GlobalConstants.SESSION_EMP);
		Map<String, Object> map= new HashMap<>();
		try {
			entity = withDrawService.getWithDrawInfo(withDrawBean.getId());
			entity.setReviewTime(new Date());
			entity.setSettleType(withDrawBean.getSettleType());
			/** 审核通过-处理中 插入一条流水表记录（与第三方交互） */

			if (withDrawBean.getApplyStatus() == 4) {

				for (int i = 0; i < withDrawBean.getDrawAmountSplit().size(); i++) {
					entity.setDrawAmount(withDrawBean.getDrawAmountSplit().get(
							i));
					withdrawDto.setCust_no(entity.getCustId().toString());
					withdrawDto.setAmount(entity.getDrawAmount());
					withdrawDto.setProcedure_fee(entity.getProcedureFee());
					withdrawImpl.excute(withdrawDto);

				}

				// entity.setApplyStatus(2);
				// withDrawService.updateWithDraw(entity);

				if (null != entity.getDebtId()) {
//					AssignmentDebt assignment = assignmentDebtService
//							.getAssignmentDebtById(entity.getDebtId());
//					if (null != assignment
//							&& StringUtils.isNotBlank(assignment.getAcctId())) {
//						creditorInfoService.updateLoanSystemData(assignment
//								.getAcctId());
						// CreditorInfo creditInfo =
						// creditorInfoService.queryByIdCreditorAssignInfo(assignment.getAcctId());
						// if (creditInfo !=null) {
						// creditInfo.setAcctStatus("3");
						// creditorInfoService.updateCreditorAssignInfo(creditInfo);
						// }

						// waiting do
					}
				}else {
					withdrawImpl.excute(withdrawDto);
				}

			map.put("tips", "S");
			} 
		catch (ThirdpartyErrorAsyncException e) {
			// 需要手动核对
			try {
				withDrawService.updateWithDrawEntity(withDrawBean.getId(),
						String.valueOf(user.getId()), 99);
				map.put("tips", "S");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				map.put("tips", "F");
				map.put("message", e1.getMessage());
			}

		} catch (FssException e) {

			try {
				withDrawService.updateWithDrawEntity(withDrawBean.getId(),
						String.valueOf(user.getId()), 4);
				map.put("tips", "S");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				map.put("tips", "F");
				map.put("message", e1.getMessage());
			}
		} catch (Exception e) {
//			log.error("审核失败" + e.getMessage());
			map.put("tips", "F");
			map.put("message", e.getMessage());
			e.printStackTrace();
		}
		return map;
//		JsonTransferUtil.printStr(response, json.toString());
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年2月26日
	 * function：跳转至新增页面
	 */
	@RequestMapping("/account/withdraw/redirectReview/{withDrawId}")
	public String redirectReview(ModelMap map,
			HttpServletResponse resp, @PathVariable Long withDrawId) {
		WithdrawApplyEntity retWithdraw = null;
//		BankDealamountLimitEntity bankDealamountLimitEntity = null;
		try {
			retWithdraw = withDrawService.getWithDrawInfo(withDrawId);
//			BankCardInfoEntity bankCardinfoEntity = bankCardinfoService
//					.queryBankCardinfoById(retWithdraw.getBankId());
//			bankDealamountLimitEntity = bankDealamountLimitService
//					.queryBankLimitInfo(retWithdraw.getThirdPartyType()
//							.getKey(), 2, bankCardinfoEntity.getParentBankId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//设置默认的结算类型
		if(retWithdraw.getSettleType()==null){
			retWithdraw.setSettleType(1);
		}
		map.addAttribute("withDraw", retWithdraw);
//		if (bankDealamountLimitEntity != null) {
//			req.setAttribute("limitAmount",
//					bankDealamountLimitEntity.getLimitAmount());
//		} else {
//			req.setAttribute("limitAmount", new BigDecimal("1000000"));
//		}

		return "funds/account/withDraw/withdraw_review";
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年2月26日
	 * function：  批量提现
	 */
	@RequestMapping(value = "/account/withdraw/withdrawDepute")
	@ResponseBody
	public Object withdrawDepute(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "no", required = false, defaultValue = "") String no) {

		LogUtil.debug(this.getClass(), no);

		SysUsers user = (SysUsers) request.getSession().getAttribute(
				GlobalConstants.SESSION_EMP);
		String code = "0000";
		String agreeNo = "";
		String message = "提现成功。";

		String[] ids = no.split(",");
		String returnCode = "";
		for (int i = 0; i < ids.length; i++) {
			try {
				returnCode = withDrawService.updateWithdrawDepute(ids[i],
						String.valueOf(user.getId()));

			} catch (Exception e) {
				LogUtil.error(this.getClass(), e.getMessage(), e);
				// 捕获充值失败异常
				if (e.getMessage() != null) {
					if (e.getMessage().contains("0001")) {
						code = "0001";
						agreeNo = e.getMessage().replace("0001", "");
						message = "已经提现，不能重复操作";
					} else if (e.getMessage().contains("0002")) {
						code = "0002";
						agreeNo = e.getMessage().replace("0002", "");
						message = "提现金额超过单笔最大上限，请审核进行提现";
					} else if (e.getMessage().contains("0008")) {
						code = "0008";
						message = e.getMessage().replace("0008", "");
						LogUtil.error(this.getClass(),
								"提现审核的提现审批动作报错日志0008====" + e.getMessage(), e);
						// 如果代扣失败,更新合同状态为代扣失败
						try {
							withDrawService.updateWithDrawCallback(
									Long.valueOf(ids[i]), "1");

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							code = "0007";
							message = "数据库操作失败！";
							LogUtil.error(
									this.getClass(),
									"提现审核的提现审批动作报错日志0007(2)===="
											+ e1.getMessage(), e1);
						}
					} else {
						code = "0007";
						message = "数据库操作失败！";
						LogUtil.error(this.getClass(),
								"提现审核的提现审批动作报错日志0007(1)====" + e.getMessage(),
								e);
					}
				} else {
					code = "0010";
					message = "系统异常！请联系系统管理员";
					LogUtil.error(this.getClass(), "提现审核的提现审批动作报错日志0010===="
							+ e.getMessage(), e);
				}
				break;
			}

		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		map.put("code", code);
		map.put("agreeNo", agreeNo);
		return map;
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年2月26日
	 * function： 审核保存
	 */
	@RequestMapping(value = "/account/withdraw/reviewWithdraw")
	@ResponseBody
	public Object updateWithDrawInfo(HttpServletRequest request,
			HttpServletResponse response, WithdrawApplyBean withDrawBean)
			throws IOException {
		WithdrawDto withdrawDto= new WithdrawDto();
		withdrawDto.setCust_no(withDrawBean.getCustId().toString());
		withdrawDto.setAmount(withDrawBean.getDrawAmount());
		withdrawDto.setProcedure_fee(withDrawBean.getProcedureFee());
		String code = "0000";
		String agreeNo = "";
		String message = "提现成功。";
		String returnCode = "";
		BigDecimal factDrawAmount = BigDecimal.ZERO;
		BigDecimal drawAmount = BigDecimal.ZERO;
		BigDecimal appalyDrawAmount = BigDecimal.ZERO;//申请金额，local变量使用
		WithdrawApplyEntity entity = null;
		SysUsers user = (SysUsers) request.getSession().getAttribute(
				GlobalConstants.SESSION_EMP);
		try {
			entity = withDrawService.getWithDrawInfo(withDrawBean.getId());
			appalyDrawAmount = entity.getDrawAmount();
			entity.setReviewTime(new Date());
			// 根据页面的修改结果，对实体对象进行设置结算类型
			entity.setSettleType(withDrawBean.getSettleType());
			// 更新合同状态
			withDrawService.update(entity);
			/** 审核通过-处理中 插入一条流水表记录（与第三方交互） */
			//审核意见==4 表示通过
			if (withDrawBean.getApplyStatus() == 4) {

				if (entity.getApplyStatus() != 1) {
					code = "0001";
					agreeNo = entity.getCustName();
					message = "已经提现，不能重复操作";
				} else {
					for (int i = 0; i < withDrawBean.getDrawAmountSplit().size(); i++) {
						drawAmount = withDrawBean.getDrawAmountSplit().get(i);
						if (i != (withDrawBean.getDrawAmountSplit().size() - 1)) {
							returnCode = withDrawService.updateWithdrawRechSave(entity, drawAmount, false);
						} else {
							returnCode = withDrawService.updateWithdrawRechSave(entity, drawAmount, true);
						}
						factDrawAmount = factDrawAmount.add(withDrawBean.getDrawAmountSplit().get(i));
					}
				}

				// 成功
				if (returnCode.equals("0000") && entity.getDrawAmount().equals(factDrawAmount)) {
					entity = withDrawService.getWithDrawInfo(withDrawBean.getId());
					// 根据页面的修改结果，对实体对象进行设置结算类型
					entity.setSettleType(withDrawBean.getSettleType());
					withDrawService.update(entity);
					
//					if (null != entity.getDebtId()) {
//						AssignmentDebt assignment = assignmentDebtService.getAssignmentDebtById(entity.getDebtId());
//						if (null != assignment&& StringUtils.isNotBlank(assignment.getAcctId())) {
//							creditorInfoService.updateLoanSystemData(assignment.getAcctId());
//						}
//					}
				} else if (returnCode.equals("0001")
						&& entity.getDrawAmount().equals(factDrawAmount)) {
					// 申请状态 4-代扣中
					entity.setApplyStatus(4);
					entity.setDrawAmount(factDrawAmount);
					entity.setFactDrawAmount(factDrawAmount);
					// 审核时间
					entity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					entity.setReviewUserId(Integer.parseInt(String.valueOf(user.getId())));
					// 更新合同状态
					withDrawService.update(entity);

				} else if (returnCode.equals("0002")) {
					// 申请状态 99-人工审核
					entity.setApplyStatus(99);
					// 审核时间
					entity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					entity.setReviewUserId(Integer.parseInt(String.valueOf(user
							.getId())));
					// 更新合同状态
					withDrawService.update(entity);
				}

			} else {
				withdrawImpl.excute(withdrawDto);
				message = "提现拒绝。";
			}
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e.getMessage(), e);
			// 捕获充值失败异常
			if (e.getMessage() != null) {
				if (e.getMessage().contains("0001")) {
					code = "0001";
					agreeNo = e.getMessage().replace("0001", "");
					message = "已经提现，不能重复操作";
				} else if (e.getMessage().contains("0008")) {
					code = "0008";
					message = e.getMessage().replace("0008", "");
				} else {
					code = "0007";
					message = "数据库操作失败！";
					LogUtil.error(this.getClass(), "提现审核的提现审批动作报错日志0007===="
							+ e.getMessage(), e);
				}
			} else {
				code = "0010";
				message = "系统异常！请联系系统管理员";
			}
		}
		// 如果提现失败,更新合同状态为提现失败
		if ("0008".equals(code) && factDrawAmount.equals(BigDecimal.ZERO)) {
			try {
				withDrawService.updateWithDrawCallback(withDrawBean.getId(), "1");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 如果部分成功
		} else if ("0008".equals(code)
				&& factDrawAmount.compareTo(BigDecimal.ZERO) > 0) {
			try {

				// 申请状态 6-部分成功
				entity.setApplyStatus(6);
				entity.setDrawAmount(appalyDrawAmount);
				entity.setFactDrawAmount(factDrawAmount);
				// 审核时间
				entity.setReviewTime(new Date(System.currentTimeMillis()));
				// 审核user
				entity.setReviewUserId(Integer.parseInt(String.valueOf(user.getId())));
				withDrawService.update(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年2月26日
	 * function：跳转至审核详细画面
	 */
	@RequestMapping("/account/withdraw/redirectReviewGoon/{id}")
	public String redirectReviewGoon(ModelMap model,
			HttpServletRequest request,
			@PathVariable Long  id) {
		WithdrawApplyEntity retWithdraw = null;
//		BankDealamountLimitEntity bankDealamountLimitEntity = null;
		try {
			retWithdraw = withDrawService.getWithDrawInfo(Long.valueOf(id));
			BankCardInfoEntity bankCardinfoEntity = bankCardinfoService
					.queryBankCardinfoById(retWithdraw.getBankId());
//			bankDealamountLimitEntity = bankDealamountLimitService
//					.queryBankLimitInfo(retWithdraw.getThirdPartyType()
//							.getKey(), 2, bankCardinfoEntity.getParentBankId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("withDraw", retWithdraw);
//		if (bankDealamountLimitEntity != null) {
//			req.setAttribute("limitAmount",
//					bankDealamountLimitEntity.getLimitAmount());
//		} else {
//			req.setAttribute("limitAmount", new BigDecimal("1000000"));
//		}

		return "funds/account/withDraw/withdraw_reviewGoon";
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年2月26日
	 * function：审核继续保存
	 */
	@RequestMapping(value = "/account/withdraw/reviewWithdrawGoon")
	@ResponseBody
	public Object updateWithDrawInfoGoon(HttpServletRequest request,
			HttpServletResponse response, WithdrawApplyBean withDrawBean)
			throws IOException {
		WithdrawDto withdrawDto= new WithdrawDto();
		withdrawDto.setCust_no(withDrawBean.getCustId().toString());
		withdrawDto.setAmount(withDrawBean.getDrawAmount());
		withdrawDto.setProcedure_fee(withDrawBean.getProcedureFee());
		String code = "0000";
		String agreeNo = "";
		String message = "提现成功。";
		String returnCode = "";
		BigDecimal factDrawAmount = BigDecimal.ZERO;
		BigDecimal drawAmount = BigDecimal.ZERO;
		BigDecimal appalyDrawAmount = BigDecimal.ZERO;//申请金额，local变量使用
		WithdrawApplyEntity entity = null;
		SysUsers user = (SysUsers) request.getSession().getAttribute(
				GlobalConstants.SESSION_EMP);
		try {
			entity = withDrawService.getWithDrawInfo(withDrawBean.getId());
			entity.setReviewTime(new Date());
			// 根据页面的修改结果，对实体对象进行设置结算类型
			entity.setSettleType(withDrawBean.getSettleType());
			// 更新合同状态
			withDrawService.update(entity);
			appalyDrawAmount = entity.getDrawAmount();
			factDrawAmount = entity.getFactDrawAmount();
			/** 审核通过-处理中 插入一条流水表记录（与第三方交互） */
			if (withDrawBean.getApplyStatus() == 4) {

				if (entity.getApplyStatus() != 6) {
					code = "0001";
					agreeNo = entity.getCustName();
					message = "已经提现，不能重复操作";
				} else {
					for (int i = 0; i < withDrawBean.getDrawAmountSplit().size(); i++) {
						drawAmount = withDrawBean.getDrawAmountSplit().get(i);
						if (i != (withDrawBean.getDrawAmountSplit().size() - 1)) {
							returnCode = withDrawService.updateWithdrawRechSave(entity, drawAmount, false);
						} else {
							returnCode = withDrawService.updateWithdrawRechSave(entity, drawAmount, true);
						}
						factDrawAmount = factDrawAmount.add(withDrawBean.getDrawAmountSplit().get(i));
					}
				}

				// 成功
				if (returnCode.equals("0000") && entity.getDrawAmount().equals(factDrawAmount)) {
					entity = withDrawService.getWithDrawInfo(withDrawBean.getId());
					// 根据页面的修改结果，对实体对象进行设置结算类型
					entity.setSettleType(withDrawBean.getSettleType());
					withDrawService.update(entity);
//					if (null != entity.getDebtId()) {
//						AssignmentDebt assignment = assignmentDebtService.getAssignmentDebtById(entity.getDebtId());
//						if (null != assignment && StringUtils.isNotBlank(assignment.getAcctId())) {
//							creditorInfoService.updateLoanSystemData(assignment	.getAcctId());
//						}
//					}
				} else if (returnCode.equals("0001")
						&& entity.getDrawAmount().equals(factDrawAmount)) {
					// 申请状态 4-代扣中
					entity.setApplyStatus(4);
					entity.setDrawAmount(factDrawAmount);
					entity.setFactDrawAmount(factDrawAmount);
					// 审核时间
					entity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					entity.setReviewUserId(Integer.parseInt(String.valueOf(user
							.getId())));
					// 更新合同状态
					withDrawService.update(entity);

				} else if (returnCode.equals("0002")) {
					// 申请状态 99-人工审核
					entity.setApplyStatus(99);
					// 审核时间
					entity.setReviewTime(new Date(System.currentTimeMillis()));
					// 审核user
					entity.setReviewUserId(Integer.parseInt(String.valueOf(user.getId())));
					// 更新合同状态
					withDrawService.update(entity);
				}

			} else {
				withdrawImpl.excute(withdrawDto);
				message = "提现拒绝。";
			}
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e.getMessage(), e);
			// 捕获充值失败异常
			if (e.getMessage() != null) {
				if (e.getMessage().contains("0001")) {
					code = "0001";
					agreeNo = e.getMessage().replace("0001", "");
					message = "已经提现，不能重复操作";
				} else if (e.getMessage().contains("0008")) {
					code = "0008";
					message = e.getMessage().replace("0008", "");
				} else {
					code = "0007";
					message = "数据库操作失败！";
					LogUtil.error(this.getClass(), "提现审核的提现审批动作报错日志0007===="
							+ e.getMessage(), e);
				}
			} else {
				code = "0010";
				message = "系统异常！请联系系统管理员";
			}
		}
		// 如果提现失败,更新合同状态为提现失败
		if ("0008".equals(code) && factDrawAmount.equals(BigDecimal.ZERO)) {
			try {
				withDrawService.updateWithDrawCallback(withDrawBean.getId(), "1");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 如果部分成功
		} else if ("0008".equals(code)
				&& factDrawAmount.compareTo(BigDecimal.ZERO) > 0) {
			try {

				// 申请状态 6-部分成功
				entity.setApplyStatus(6);
				entity.setDrawAmount(appalyDrawAmount);
				entity.setFactDrawAmount(factDrawAmount);
				// 审核时间
				entity.setReviewTime(new Date(System.currentTimeMillis()));
				// 审核user
				entity.setReviewUserId(Integer.parseInt(String.valueOf(user
						.getId())));
				withDrawService.update(entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("message", message);
		return map;
	}
    
}
