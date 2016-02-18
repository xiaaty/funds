package com.gqhmt.fss.controller.customer;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.customer.bean.CustomerAndUser;
import com.gqhmt.fss.architect.customer.service.FssCustBankCardService;
import com.gqhmt.funds.architect.customer.bean.BankCardBean;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;

/**
 * Filename: com.gqhmt.sys.controller.MenuController Copyright: Copyright
 * (c)2015 Company: 冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7 Create at: 2015/12/19 7:46 Description:
 *         <p>
 *         Modification History: Date Author Version Description
 *         -----------------------------------------------------------------
 *         2015/12/19 于泳 1.0 1.0 Version
 */

@Controller
public class FssCustBankCardController {

	@Resource
	private FssCustBankCardService bankCardService;
	@Resource
	private BankCardInfoService bankCardInfoService;

	/**
	 *银行卡信息列表
	 */
	@RequestMapping(value = "/fss/customer/bankCards", method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public Object bankList(HttpServletRequest request, ModelMap model,@ModelAttribute(value = "customer") CustomerAndUser customer) {
		// 得到银行卡、客户信息列表
		List<CustomerAndUser> bankCards = bankCardService.findbankCardAll(customer);
		//得到银行列表
		List<BankCardBean> banks=bankCardInfoService.queryBankList();
		model.addAttribute("page", bankCards);
		model.addAttribute("banks", banks);
		model.addAttribute("customer", customer);
		return "fss/customer/bankCardList";
	}
	
	/**
	 * author:kyl 
	 * time:2016年2月16日 
	 * author:jhz 
	 * time:2016年1月25日 
	 * function：银行卡信息列表
	 */
	@RequestMapping(value = "/fss/customer/bankCards", method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public Object bankCardList(HttpServletRequest request, ModelMap model,
			@ModelAttribute(value = "customer") CustomerAndUser customerAndUser) {
		// 得到银行卡、客户信息列表
		List<CustomerAndUser> bankCards = bankCardService.findbankCardAll(customerAndUser);
		//得到银行列表
		List<BankCardBean> banks=bankCardInfoService.queryBankList();
		
		model.addAttribute("page", bankCards);
		model.addAttribute("banks", banks);
		// model.addAttribute("customer", customerAndUser);
		return "fss/customer/bankCardList";
	}

	/**
	 * author:jhz 
	 * time:2016年1月25日 
	 * function：跳转到银行变更申请
	 * @throws FssException 
	 */
	@RequestMapping(value = "/fss/customer/toUpdate", method = RequestMethod.GET)
	@AutoPage
	public Object bankCardChange(HttpServletRequest request, ModelMap model, Long id) throws FssException {
		// 得到银行卡、客户信息列表
		CustomerAndUser customer = bankCardService.findCustomerAndUser(id);
		
		model.addAttribute("customer", customer);
		
		return "fss/customer/bankCardList";
	}
}
