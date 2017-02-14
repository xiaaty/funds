package com.gqhmt.controller.fss.loan;

import com.beust.jcommander.internal.Maps;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.entity.FssMappingEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.account.service.FssMappingService;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.fss.architect.loan.bean.FssLoanBean;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.ExportAndImpService;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.cost.ICost;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * Filename: com.gqhmt.extServInter.dto.account.CreateAccountByFuiou Copyright:
 * Copyright (c)2016 Company: 冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7 Create at: 2016年3月11日 Description: 代付审核
 *         借款人放款
 *         借款人提现
 *         代偿人付款
 *         代扣
 *         转账
 *         收费
 *         退费
 *         退款
 *         出借赎回 Modification History: Date Author Version Description
 *         -----------------------------------------------------------------
 *         2016年3月11日 jhz 1.0 1.0 Version
 */
@Controller
public class FssLoanTradeController {
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	@Resource
	private FssLoanService fssLoanService;
	@Resource
	private IFundsTrade fundsTradeImpl;
	@Resource
	private ICost cost;
	@Resource
    private FssCustomerService fssCustomerService;
	@Resource
	private FssBackplateService fssBackplateService;
	@Resource
	private FssAccountService fssAccountService;
	@Resource
	private ExportAndImpService exportAndImpService;
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private FssMappingService fssMappingService;
	@Resource
	private FssRepaymentService fssRepaymentService;

	/**
	 * 
	 * author:jhz time:2016年3月11日 function：借款人放款
	 * @throws FssException 
	 */
	@RequestMapping(value = "/loan/trade/{type}", method = { RequestMethod.GET, RequestMethod.POST })
	@AutoPage
	public Object loanList(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map,
			@PathVariable String type) throws FssException {
		String status=request.getParameter("status");
		map.put("type", type);
		map.put("status", status);
		List<FssLoanEntity> list = fssLoanService.findBorrowerLoan(map);
	/*	List<FssLoanEntity> list2 =new ArrayList<>();
		for (FssLoanEntity fssLoanEntity : list) {
			String custNo = fssLoanEntity.getCustNo();
			if(custNo!=null&&!"".equals(custNo)){
			FssCustomerEntity customerNameByCustNo = fssCustomerService.getCustomerNameByCustNo(custNo);
			fssLoanEntity.setUserNo(customerNameByCustNo.getName());
			}
			list2.add(fssLoanEntity);
		}
		model.addAttribute("page", list2);
		*/
		model.addAttribute("page", list);
		model.put("map", map);
		model.put("status", status);
		if("11090003".equals(type)){//纯线下放款
			return "fss/trade/trade_audit/borrowerloan_offline";
		}else if("11090006".equals(type)){//冠e通抵押标权人提现
			return "fss/trade/trade_audit/motegreeWithDraw";
		}else if("11092001".equals(type)){//抵押标权人提现
			return "fss/trade/trade_audit/motegreeWithDraw";
		}else if("11090004".equals(type)){
			FssMappingEntity fssMap=fssMappingService.selectByTradeType(type);
			model.addAttribute("scale",fssMap.getCustId());
			return "fss/trade/trade_audit/batchExtraction";
		}
		return "fss/trade/trade_audit/borrowerloan";
	}

	/**
	 * 
	 * author:jhz time:2016年3月16日 function：点击代扣跳转到代扣页面
	 * @throws FssException 
	 */
	@RequestMapping("/loan/trade/{type}/toWithHold/{id}")
	public String withhold(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) throws FssException {
//		String token = TokenProccessor.getInstance().makeToken();//创建令牌
//		System.out.println("在FormServlet中生成的token："+token);
//		request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
		// 通过id查询交易对象
		String userName=null;
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		if("11090005".equals(type)){//冠e通抵押标放款
			CustomerInfoEntity customerInfoEntity=customerInfoService.getCustomerById(Long.valueOf(fssLoanEntityById.getMortgageeAccNo()));
			if(customerInfoEntity!=null){
				userName=customerInfoEntity.getCustomerName();
				model.addAttribute("userName",userName);
			}
		}else { //借款系统抵押标放款
			//通过custNo查询用户对象
			FssCustomerEntity customerNameByCustNo = fssCustomerService.getCustomerNameByCustNo(fssLoanEntityById.getCustNo());
			if(customerNameByCustNo!=null) {
				userName=customerNameByCustNo.getName();
				model.addAttribute("userName", userName);
			}
		}
		model.addAttribute("loan", fssLoanEntityById);
		return "fss/trade/trade_audit/loanWithHold";
	}

	/**
	 * 
	 * author:jhz time:2016年3月18日 function：添加到抵押权人代扣
	 * @throws InterruptedException 
	 * 
	 * @throws FssException
	 *             "10100001"代扣充值
	 */
	@RequestMapping("/loan/trade/{type}/withHold/{id}")
	@ResponseBody
	public Object withholdApply(HttpServletRequest request, ModelMap model, @PathVariable String type, @PathVariable Long id, BigDecimal payAmt) {
		Map<String, String> map = new HashMap<String, String>();
		FssLoanEntity fssLoanEntity = fssLoanService.getFssLoanEntityById(id);
		if(!"10050002".equals(fssLoanEntity.getStatus())) {
			// 把交易状态 修改为‘代扣中’
			fssLoanEntity.setStatus("10050002");
//		String server_token  = (String) request.getSession().getAttribute("token");
//		request.getSession().removeAttribute("token");
//		if(token.equals(server_token)){
			try {
				fssLoanEntity.setContractAmt(payAmt);
				fssTradeApplyService.insertLoanTradeApply(fssLoanEntity, type);
				//1 代扣，2 提现
				map.put("code", "0000");
				map.put("message", "成功");
				fssLoanService.update(fssLoanEntity);
			} catch (FssException e) {
				map.put("code", "0001");
				map.put("message", e.getMessage());
				LogUtil.info(this.getClass(), e.getMessage());
			}
		}else{
			map.put("code", "0001");
	        map.put("message", "请勿重复提交!");
		}
		return map;
	}

	/**
	 * 
	 * author:jhz time:2016年3月16日 function：转给借款人
	 */
	@RequestMapping("/loan/trade/{type}/transfer/{id}")
	public String transfer(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) {
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		try {
			if("11090005".equals(type)){//冠e通抵押标转账MortgageeAccNo存的cust_id
				fundsTradeImpl.transefer(Integer.parseInt(fssLoanEntityById.getMortgageeAccNo()),1,Integer.parseInt(fssLoanEntityById.getAccNo()),1,fssLoanEntityById.getContractAmt(),GlobalConstants.ORDER_MORTGAGEE_TRANS_ACC,fssLoanEntityById.getId(),GlobalConstants.NEW_BUSINESS_MT,type,fssLoanEntityById.getContractNo(),1005,3);
			}else {//借款系统抵押标转账MortgageeAccNo存的accno
				fundsTradeImpl.transefer(fssLoanEntityById.getMortgageeAccNo(),fssLoanEntityById.getAccNo(),fssLoanEntityById.getContractAmt(), GlobalConstants.ORDER_MORTGAGEE_TRANS_ACC, fssLoanEntityById.getId(),GlobalConstants.NEW_BUSINESS_MT,type,fssLoanEntityById.getContractNo(),1005,3);
			}
			fssLoanEntityById.setStatus("10050005");
			fssLoanService.update(fssLoanEntityById);
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
			model.addAttribute("erroMsg", e.getMessage());
		}

		// todo 结果返回前台页面,消息提示
		return "redirect:/loan/trade/"+type;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月6日
	 * function：抵押标流标转账
	 */
	@RequestMapping("/loan/trade/{type}/retransfer/{id}")
	public String retransfer(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) {
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		
		try {
			fundsTradeImpl.transefer(fssLoanEntityById.getAccNo(),fssLoanEntityById.getMortgageeAccNo(),
					fssLoanEntityById.getPayAmt(), GlobalConstants.ORDER_MORTGAGEE_TRANS_ACC, fssLoanEntityById.getId(),
					GlobalConstants.NEW_BUSINESS_MT,type,fssLoanEntityById.getContractNo(),1005,3);
			fssLoanEntityById.setStatus("10050100");
			fssLoanService.update(fssLoanEntityById);
			fssBackplateService.createFssBackplateEntity(fssLoanEntityById.getSeqNo(), fssLoanEntityById.getMchnChild(), fssLoanEntityById.getTradeType());
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e.getMessage());
			model.addAttribute("erroMsg", e.getMessage());
		}
		
		// todo 结果返回前台页面,消息提示
		return "redirect:/loan/trade/"+type;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月6日
	 * function：信用流标退款
	 * @throws FssException 
	 */
	@RequestMapping("/loan/trade/{type}/abort/{id}")
	public String abort(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) throws FssException {
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		if("11090011".equals(type)){
			FssAccountEntity fssAccountByAccNo = fssAccountService.getFssAccountByAccNo(fssLoanEntityById.getAccNo());
			fssLoanEntityById.setCustNo(fssAccountByAccNo.getCustId().toString());
		}
//					fssLoanService.abort(fssLoanEntityById);
		fssLoanEntityById.setStatus("10050103");
		fssLoanService.update(fssLoanEntityById);
		return "redirect:/loan/trade/"+type;
	}
	/**
	 * 
	 * author:jhz time:2016年3月16日 function：收费
	 * @throws FssException 
	 */
	@RequestMapping("/loan/trade/{type}/charge/{id}")
//	@ResponseBody
	public Object charge(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) throws FssException {
		int i=0;
		Map<String,String> map=new HashMap<>();
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		if (fssLoanEntityById == null) {
			// 处理前台页面消息提示内容
			map.put("msg", "0001");
		}

		List<FssFeeList> fssFeeLists = fssLoanService.getFeeList(id);
		if (fssFeeLists == null || fssFeeLists.size() == 0) {
			// 处理前台页面消息提示内容
			map.put("msg", "0002");
		} else {

				for (FssFeeList fssFeeList : fssFeeLists) {
					try {
					if(!"10050007".equals(fssFeeList.getTradeStatus())&&!"10050015".equals(fssFeeList.getTradeStatus())){
						if(fssFeeList.getFeeAmt().compareTo(BigDecimal.ZERO)>0&&!"10990004".equals(fssFeeList.getFeeType())){
							if("11090005".equals(type) || "11090004".equals(type)){//冠e通收费
								cost.cost(fssLoanEntityById.getLoanPlatform(),
										fssFeeList.getFeeType(),Long.parseLong(fssLoanEntityById.getAccNo()),1, fssFeeList.getFeeAmt(),
										fssFeeList.getId(), GlobalConstants.NEW_BUSINESS_COST,fssLoanEntityById.getContractNo());
							}else {
								cost.cost(fssLoanEntityById.getLoanPlatform(),
										fssFeeList.getFeeType(), fssLoanEntityById.getAccNo(), fssFeeList.getFeeAmt(),
										fssFeeList.getId(), GlobalConstants.NEW_BUSINESS_COST,fssLoanEntityById.getContractNo());
							}
							// 修改费用状态	收取成功
							fssFeeList.setRepCode("0000");
							fssFeeList.setModifyTime(new Date());
							fssFeeList.setTradeStatus("10050007");
						}else{
							fssFeeList.setRepCode("10050015");
							fssFeeList.setModifyTime(new Date());
							fssFeeList.setTradeStatus("10050015");
						}
					}
					} catch (FssException e) {
						fssFeeList.setRepCode(e.getMessage());
						map.put("msg", "0003");

					}
					fssLoanService.updateFeeList(fssFeeList);
					if("10050007".equals(fssFeeList.getTradeStatus())||"10050015".equals(fssFeeList.getTradeStatus())){
						i++;
					}
				}
			if(i==fssFeeLists.size()){
				// 如果全部成功,修改记录收费状态并进入回盘记录表中,失败返回页面,继续处理
				if(!"11090004".equals(type)){
					fssLoanEntityById.setStatus("10050007");
					fssBackplateService.createFssBackplateEntity(fssLoanEntityById.getSeqNo(), fssLoanEntityById.getMchnChild(), fssLoanEntityById.getTradeType());
				}else {
					fssLoanEntityById.setStatus("10050022 ");
				}
				map.put("msg", "0000");
			}else{
				//收取失败继续收取费用
				map.put("msg", "0003");
			}
			fssLoanService.update(fssLoanEntityById);
		}

		return "redirect:/loan/trade/"+type;
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年4月6日
	 * function：退费
	 */
	@RequestMapping("/loan/trade/{type}/recharge/{id}")
//	@ResponseBody
	public Object recharge(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) throws FssException {
		int i=0;
		Map<String,String> map=new HashMap<>();
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		
		if (fssLoanEntityById == null) {
			// 处理前台页面消息提示内容
			map.put("msg", "0001");
		}
		
		List<FssFeeList> fssFeeLists = fssLoanService.getFeeList(id);
		if (fssFeeLists == null || fssFeeLists.size() == 0) {
			// 处理前台页面消息提示内容
			map.put("msg", "0002");
		} else {
			
			for (FssFeeList fssFeeList : fssFeeLists) {
				try {
					if(!"10050099".equals(fssFeeList.getTradeStatus())){
						FundOrderEntity fundOrderEntity = cost.costReturn(fssLoanEntityById.getLoanPlatform(),
								fssFeeList.getFeeType(),fssLoanEntityById.getAccNo(), fssFeeList.getFeeAmt(),
								fssFeeList.getId(), GlobalConstants.NEW_BUSINESS_COST,fssLoanEntityById.getContractNo());
						// 修改费用状态	退费成功
						fssFeeList.setTradeStatus("10050099");
						fssLoanService.updateFeeList(fssFeeList);
					}
				} catch (FssException e) {
					e.printStackTrace();
					map.put("msg", "0003");
					
				}
			}
			// 如果全部成功,修改记录收费状态并进入回盘记录表中,失败返回页面,继续处理
			for (FssFeeList fssFeeList : fssFeeLists) {
				if("10050099".equals(fssFeeList.getTradeStatus())){
					i++;
				}
			}
			if(i==fssFeeLists.size()){
				fssLoanEntityById.setStatus("10050099");
				fssLoanService.update(fssLoanEntityById);
				map.put("msg", "0000");
				//todo 成功之后取消收费按钮
			}else{
				map.put("msg", "0003");
			}
		}
		
		return "redirect:/loan/trade/"+type;
	}

	/**
	 * 
	 * author:jhz time:2016年3月11日 function：查看收费列表
	 */				
	@RequestMapping("/loan/trade/{type}/{loanId}/feeList")
	public String accountRecharge(HttpServletRequest request, ModelMap model, @PathVariable Long loanId,@PathVariable String type) {
		List<FssFeeList> findFeeList = fssLoanService.getFeeList(loanId);
		model.addAttribute("feeList", findFeeList);
		return "fss/trade/trade_audit/feeList";
	}

	
	/**
	 * 导出还款代扣（纯线下）
	 * @param request
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping("/loan/trade/{type}/export")
	public void loanListExport(HttpServletRequest request,HttpServletResponse response,@PathVariable String type, ModelMap model) throws FssException{
//		List<FssLoanBean> list = fssLoanService.findLoanOffilne(type);
		List<FssLoanBean> list = fssLoanService.findLoanOffilne();
		try {
			HSSFWorkbook wb = exportAndImpService.exportLoan(list);
			response.setContentType("application/vnd.ms-excel");    
			response.setHeader("Content-disposition", "attachment;filename=loandata.xls");    
			OutputStream ouputStream = response.getOutputStream();  
			wb.write(ouputStream);    
			ouputStream.flush();    
			ouputStream.close();
			JOptionPane.showMessageDialog(null, "导出成功!");
		} catch (IOException e) {
			throw new FssException("Io异常");
		}    
	}



	@RequestMapping("/loan/trade/backplat")
	@AutoPage
	public String queryBackPlat(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) {
		List<FssBackplateEntity> backplateList = fssBackplateService.getBackPlate(map);
		model.addAttribute("page", backplateList);
		model.addAttribute("map",map);
		return "fss/trade/backplateList";
	}
	

	/**
	 *
	 * author:jhz time:2016年07月04日 function：点击提现跳转到提现页面
	 * @throws FssException
	 */
	@RequestMapping("/loan/trade/{type}/toWithDraw/{id}")
	public String withDraw(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) throws FssException {
		// 通过id查询交易对象
		String userName=null;
		CustomerInfoEntity customerInfoEntity=null;
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		if("11090006".equals(type)){//冠e通抵押标提现
			customerInfoEntity=customerInfoService.getCustomerById(Long.valueOf(fssLoanEntityById.getMortgageeAccNo()));
			if(customerInfoEntity!=null){
				model.addAttribute("customerInfoEntity",customerInfoEntity);
			}
		}else { //借款系统抵押标提现
			FssCustomerEntity fssCustomerEntity= fssCustomerService.getCustomerNameByCustNo(fssLoanEntityById.getCustNo());
			if(fssCustomerEntity!=null) {
				customerInfoEntity=customerInfoService.getCustomerById(fssCustomerEntity.getCustId());
				if(customerInfoEntity!=null) {
					model.addAttribute("customerInfoEntity", customerInfoEntity);
				}
			}
		}
		FundAccountEntity fundAccountEntity=fundAccountService.getFundsAccount(customerInfoEntity.getId(),GlobalConstants.ACCOUNT_TYPE_LOAN);
		model.addAttribute("amount",fundAccountEntity.getAmount());
		model.addAttribute("loan", fssLoanEntityById);
		model.addAttribute("flag", 9);
		return "fss/trade/offlineRecharge_add";
	}

	/**
	 *
	 * author:jhz time:2016年07月04日 function：添加到抵押权人提现
	 * @throws InterruptedException
	 *
	 * @throws FssException
	 */
	@RequestMapping("/loan/trade/{type}/withDrawApply/{id}")
	@ResponseBody
	public Object withDrawApply(HttpServletRequest request, ModelMap model, @PathVariable String type, @PathVariable Long id, BigDecimal amt,BigDecimal amount) {
		Map<String, String> map = new HashMap<String, String>();
		FssLoanEntity fssLoanEntity = fssLoanService.getFssLoanEntityById(id);
		if(amt!=null){
			if (amount.compareTo(amt)<0){
				map.put("code", "0002");
				map.put("message", "账户余额不足!");
			}
		}else{
			amt=fssLoanEntity.getContractAmt();
		}
		if(!"1".equals(fssLoanEntity.getWithDrawStatus())) {
			// 修改体现状态
			fssLoanEntity.setWithDrawStatus("1");
			fssLoanEntity.setModifyTime(new Date());
			BigDecimal contractAmt=fssLoanEntity.getContractAmt();
			try {
				fssLoanEntity.setContractAmt(amt);
				fssTradeApplyService.insertLoanTradeApply(fssLoanEntity, type);
				fssLoanEntity.setContractAmt(contractAmt);
				//2 提现
				map.put("code", "0000");
				map.put("message", "成功");
				fssLoanService.update(fssLoanEntity);
			} catch (FssException e) {
				map.put("code", "0001");
				map.put("message", e.getMessage());
				LogUtil.info(this.getClass(), e.getMessage());
			}
		}else{
			map.put("code", "0001");
			map.put("message", "请勿重复提交!");
		}
		return map;
	}

	/**
	 * 回盘失败重新回盘
	 * @param request
	 * @param model
     * @return
     */
	@RequestMapping("/loan/trade/backToDisk")
	@ResponseBody
	public Object backToDisk(HttpServletRequest request, ModelMap model) throws FssException{
		Map<String, String> map = new HashMap<String, String>();
		try{
			Long id=Long.valueOf(request.getParameter("id"));
			FssBackplateEntity entity=fssBackplateService.getBackPlateById(id);
			fssBackplateService.updatebackplate(entity);
			map.put("code", "0000");
			map.put("message", "成功");
		}catch (FssException e){
			map.put("code", "0001");
			map.put("message", e.getMessage());
			LogUtil.error(this.getClass(), e.getMessage());
		}
		return map;
	}

	/**
	 * jhz
	 * 添加到借款人提现（分批提现）
	 * @param request
	 * @param id
	 * @param type
	 * @param amount
	 * @return
     * @throws FssException
     */
	@RequestMapping("/loan/trade/{type}/bathWithDraw/{id}")
	@ResponseBody
	public Object bathWithDraw(HttpServletRequest request, @PathVariable Long id, @PathVariable String type,String amount ) throws FssException {
		Map<String, String> map = new HashMap<String, String>();
		// 通过id查询交易对象
		try {
			FssLoanEntity fssLoanEntity = fssLoanService.getFssLoanEntityById(id);
			if ("10050009".equals(fssLoanEntity.getStatus())) {
				BigDecimal firstAmt = new BigDecimal(amount);
				if(firstAmt.compareTo(fssLoanEntity.getPayAmt())>0){
					map.put("code", "0001");
					map.put("msg", "请检查提现金额");
					return map;
				}
				fssLoanEntity.setFirstAmt(firstAmt);
				fssLoanEntity.setStatus("10050023");
				fssLoanService.update(fssLoanEntity);
				fssTradeApplyService.insertLoanTradeApply(fssLoanEntity, type);
				map.put("code", "0000");
				map.put("msg", "成功");
			}else if("10050022".equals(fssLoanEntity.getStatus())){
				BigDecimal secondtAmt = new BigDecimal(amount);
				if(secondtAmt.compareTo(BigDecimal.ZERO)<=0 || secondtAmt.compareTo(fssLoanEntity.getPayAmt().subtract(fssLoanEntity.getFirstAmt()))>0){
					map.put("code", "0001");
					map.put("msg", "请检查提现金额");
					return map;
				}
				fssLoanEntity.setSecondAmt(secondtAmt);
				fssLoanEntity.setStatus("10050020");
				fssLoanService.update(fssLoanEntity);
				fssTradeApplyService.insertLoanTradeApply(fssLoanEntity, type);
				map.put("code", "0000");
				map.put("msg", "成功");
			}else{
				map.put("code", "0001");
				map.put("msg", "不符合提现状态");
			}
		}catch (Exception e){
			map.put("code", "0001");
			map.put("msg", e.getMessage());
			LogUtil.error(this.getClass(), e.getMessage());
		}

		return map;
	}

	/**
	 * jhz
	 * 提现或代扣跳过
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 * @throws FssException
     */
	@RequestMapping("/loan/trade/{type}/jumpWithDraw/{id}")
	public String jumpWithDraw(HttpServletRequest request, @PathVariable Long id, @PathVariable String type ) throws FssException {
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntity = fssLoanService.getFssLoanEntityById(id);
		//首次提现跳过
		if("10050009".equals(fssLoanEntity.getStatus())){
			fssLoanEntity.setStatus("10050017");
			fssLoanService.update(fssLoanEntity);
			//首次提现跳过之后进行回盘
			fssBackplateService.createFssBackplateEntity(fssLoanEntity.getSeqNo(), fssLoanEntity.getMchnChild(), fssLoanEntity.getTradeType());
		//收费代扣跳过
		}else if("10050017".equals(fssLoanEntity.getStatus())){
			fssLoanEntity.setStatus("10050019");
			fssLoanService.update(fssLoanEntity);
		//跳过收费
		}else if("10050019".equals(fssLoanEntity.getStatus())){
			fssLoanEntity.setStatus("10050022");
			fssLoanService.update(fssLoanEntity);
		//跳过二次提现
		}else if("10050022".equals(fssLoanEntity.getStatus())){
			fssLoanEntity.setStatus("10050021");
			fssLoanService.update(fssLoanEntity);
		}
		return "redirect:/loan/trade/"+type;
	}

	/**
	 * jhz
	 * 费用代扣
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 * @throws FssException
     */
	@RequestMapping("/loan/trade/{type}/chargeWithHold/{id}")
//	@ResponseBody
	public Object chargeWithHold(HttpServletRequest request, @PathVariable Long id, @PathVariable String type) throws FssException {
		BigDecimal amount=BigDecimal.ZERO;
		// 通过id查询交易对象
		FssLoanEntity fssLoanEntity = fssLoanService.getFssLoanEntityById(id);

		List<FssFeeList> fssFeeLists = fssLoanService.getFeeList(id);
		if (fssFeeLists == null || fssFeeLists.size() == 0) {
			// 处理前台页面消息提示内容
			return null;
		} else {

			for (FssFeeList fssFeeList : fssFeeLists) {
				if("".equals(fssFeeList.getTradeStatus())||fssFeeList.getTradeStatus()==null){
					if(fssFeeList.getFeeAmt().compareTo(BigDecimal.ZERO)>0&&!"10990004".equals(fssFeeList.getFeeType())){
						amount=amount.add(fssFeeList.getFeeAmt());
						// 修改费用状态	收取成功
						fssFeeList.setModifyTime(new Date());
						fssFeeList.setTradeStatus("10050018");
					}else{
						fssFeeList.setRepCode("10050015");
						fssFeeList.setModifyTime(new Date());
						fssFeeList.setTradeStatus("10050015");
					}
				}
				fssLoanService.updateFeeList(fssFeeList);
			}
			//代扣费用金额大于0时进行代扣收费
			if(amount.compareTo(BigDecimal.ZERO)>0) {
				fssTradeApplyService.whithholdingApply(null, null, fssLoanEntity.getTradeType(), amount, fssLoanEntity.getMchnChild(), fssLoanEntity.getSeqNo(), Long.valueOf(fssLoanEntity.getAccNo()), GlobalConstants.ACCOUNT_TYPE_LOAN
						, fssLoanEntity.getContractNo(), fssLoanEntity.getContractId(), fssLoanEntity.getId(), true);
				//信用标借款人费用代扣中
				fssLoanEntity.setStatus("10050018");
				fssLoanService.update(fssLoanEntity);
			}else {
				//信用标借款人费用代扣成功
				fssLoanEntity.setStatus("10050019");
				fssLoanService.update(fssLoanEntity);
			}
		}

		return "redirect:/loan/trade/"+type;
	}
	/**
	 * jhz
	 * 修改首次提现比例
	 * @param request
	 * @param type
	 * @return
	 * @throws FssException
	 */
	@RequestMapping("/loan/trade/{type}/updateScale")
	@ResponseBody
	public Object updateScale(HttpServletRequest request, @PathVariable String type,String scale) throws FssException {
		Map<String, String> map = Maps.newHashMap();
		// 通过交易类型查询对象
		FssMappingEntity fssMap = fssMappingService.selectByTradeType(type);
		try{
			Integer scales=Integer.valueOf(scale);
			if(0<scales && scales<100) {
				fssMap.setCustId(scales);
				fssMappingService.update(fssMap);
				map.put("code", "0000");
				map.put("msg", "修改成功");
			}else{
				map.put("code", "0001");
				map.put("msg", "请输入正确的提现比例");
			}
		}catch (Exception e){
			map.put("code", "0001");
			map.put("msg", e.getMessage());
		}
		return map;
	}


	/**
	 * 中间人转账借款人
	 * @param id
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping("/loan/trade/midCust/{type}/retransfer/{id}")
	public String midCustretransfer(HttpServletRequest request, @PathVariable Long id, @PathVariable String type, ModelMap model) {
		// 通过id查询交易对象
		FssRepaymentEntity repayment = fssRepaymentService.queryRepaymentById(id);

		try {
			fundsTradeImpl.transefer(repayment.getAccNo(),repayment.getMidCustId(),
					repayment.getAmt(), GlobalConstants.ORDER_MORTGAGEE_TRANS_ACC, repayment.getId(),
					GlobalConstants.NEW_BUSINESS_MT,type,repayment.getContractNo(),1005,3);
			repayment.setState("10050105");
			fssRepaymentService.updateRepaymentEntity(repayment);

			FssBackplateEntity backplateEntity = fssBackplateService.selectByMchnAndseqNo(repayment.getMchnChild(), repayment.getSeqNo());
			backplateEntity.setRepayCount(0);

			fssBackplateService.updatebackplate(backplateEntity);

		} catch (FssException e) {
			LogUtil.error(this.getClass(), e.getMessage());
			model.addAttribute("erroMsg", e.getMessage());
		}

		return "redirect:/loan/trade/"+type;
	}

}