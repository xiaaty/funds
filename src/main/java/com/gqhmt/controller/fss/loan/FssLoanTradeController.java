package com.gqhmt.controller.fss.loan;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.fss.architect.loan.bean.FssLoanBean;
import com.gqhmt.fss.architect.loan.entity.FssFeeList;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.ExportAndImpService;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
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
	private FssTradeRecordService fssTradeRecordService;
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
	private FuiouFtpOrderService fuiouFtpOrderService;
	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private FundOrderService fundOrderService;

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
		}
//		else if("11092001".equals(type)){//抵押标满标
//			return "fss/trade/trade_audit/motegreeWithDraw";
//		}
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
		FssLoanEntity fssLoanEntityById = fssLoanService.getFssLoanEntityById(id);
		//通过custNo查询用户对象
		FssCustomerEntity customerNameByCustNo = fssCustomerService.getCustomerNameByCustNo(fssLoanEntityById.getCustNo());
		// 把交易状态 修改为‘代扣中’
		model.addAttribute("loan", fssLoanEntityById);
		model.addAttribute("user", customerNameByCustNo);
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
		fssLoanEntity.setStatus("10050002");
//		String server_token  = (String) request.getSession().getAttribute("token");
//		request.getSession().removeAttribute("token");
//		if(token.equals(server_token)){
		try {
			fssLoanEntity.setContractAmt(payAmt);;
			fssTradeApplyService.insertLoanTradeApply(fssLoanEntity, "10100001",type);
			//1 代扣，2 提现
			map.put("code", "0000");
	        map.put("message", "成功");
	        fssLoanService.update(fssLoanEntity);
		} catch (FssException e) {
			map.put("code", "0001");
	        map.put("message", e.getMessage());
			LogUtil.info(this.getClass(), e.getMessage());
		}
//		}else{
//			map.put("code", "0001");
//	        map.put("message", "请勿重复提交!");
//		}
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
			fundsTradeImpl.transefer(fssLoanEntityById.getMortgageeAccNo(), fssLoanEntityById.getAccNo(),
					fssLoanEntityById.getContractAmt(), GlobalConstants.ORDER_MORTGAGEE_TRANS_ACC, fssLoanEntityById.getId(),
					GlobalConstants.NEW_BUSINESS_MT);
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
					GlobalConstants.NEW_BUSINESS_MT);
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

			try {
			for (FssFeeList fssFeeList : fssFeeLists) {
					if(!"10050007".equals(fssFeeList.getTradeStatus())){
						if(fssFeeList.getFeeAmt().compareTo(BigDecimal.ZERO)>0){
						FundOrderEntity fundOrderEntity = cost.cost(fssLoanEntityById.getLoanPlatform(),
								fssFeeList.getFeeType(),fssLoanEntityById.getAccNo(), fssFeeList.getFeeAmt(),
								fssFeeList.getId(), GlobalConstants.NEW_BUSINESS_COST);
						}
						// 修改费用状态	收取成功
						fssFeeList.setTradeStatus("10050007");
						fssLoanService.updateFeeList(fssFeeList);
					}
			}
			} catch (FssException e) {
				e.printStackTrace();
				map.put("msg", "0003");
				
			}
			fssLoanEntityById.setStatus("10050007");
			fssLoanService.update(fssLoanEntityById);
			// 如果全部成功,修改记录收费状态并进入回盘记录表中,失败返回页面,继续处理
			for (FssFeeList fssFeeList : fssFeeLists) {
				if("10050007".equals(fssFeeList.getTradeStatus())){
					i++;
				}
			}
			if(i==fssFeeLists.size()){
				fssBackplateService.createFssBackplateEntity(fssLoanEntityById.getSeqNo(), fssLoanEntityById.getMchnChild(), fssLoanEntityById.getTradeType());
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
								fssFeeList.getId(), GlobalConstants.NEW_BUSINESS_COST);
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
	
	
	
	
	
	

}