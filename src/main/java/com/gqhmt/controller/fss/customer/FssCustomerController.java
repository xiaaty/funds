package com.gqhmt.controller.fss.customer;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 客户信息管理
 * @author 57627
 *
 */
@Controller
public class FssCustomerController {
	@Resource
    private FssCustomerService customerService;
	
	@Resource
	private FssAccountService fssAccountService;
	
	
	
    public FssCustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(FssCustomerService customerService) {
		this.customerService = customerService;
	}
	/**
	 * 查询客户核心信息列表
	 * @param request
	 * @param model
	 * @param customer
	 * @return
	 * @throws FssException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fss/account/hxyhlist",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object findCustomerList(HttpServletRequest request,ModelMap model,FssCustomerEntity customer,@RequestParam Map<String, String> map) throws FssException{
		List<FssCustomerEntity> customers = customerService.findCustomerByParams(map);
    	model.addAttribute("page",customers);
    	model.addAttribute("customer",customer);
    	model.addAttribute("map",map);
    	return "fss/account/customerList";
    }
	
	
	/**
	 * 查询客户账户信息
	 * @param request
	 * @param model
	 * @param fssAccount
	 * @return
	 * @throws FssException 
	 */
	@RequestMapping(value = "/fss/account/customerAccountDetail/{cust_no}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object getCustomerAccountList(HttpServletRequest request,ModelMap model,@PathVariable String cust_no,FssAccountEntity fssAccount) throws FssException{	
    	List<FssAccountEntity> fssAccounts = fssAccountService.findCustomerAccountByParams(fssAccount);
    	model.addAttribute("page",fssAccounts);
    	model.addAttribute("account",fssAccount);
    	return "fss/account/customerAccountDetail";
    }
	

}
