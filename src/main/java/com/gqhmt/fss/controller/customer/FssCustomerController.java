package com.gqhmt.fss.controller.customer;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.mybatis.GqPageInfo;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashMap;
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
	 */
	@RequestMapping(value = "/fss/account/hxyhlist",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object findCustomerList(HttpServletRequest request,ModelMap model,FssCustomerEntity customer){
		String startime=request.getParameter("startime");
		String endtime=request.getParameter("endtime");
		
		Map map=new HashMap();
    	if(StringUtils.isNotEmptyString(customer.getMobile())){
    		map.put("mobile",customer.getMobile());
    	}
    	if(StringUtils.isNotEmptyString(customer.getName())){
    		map.put("name",customer.getName());
    	}
    	if(StringUtils.isNotEmptyString(customer.getCert_no())){
    		map.put("cert_no", customer.getCert_no());
    	}
    	if(StringUtils.isNotEmptyString(startime)){
			map.put("startime", startime+" 00:00:00");
    	}
    	if(StringUtils.isNotEmptyString(endtime)){
			map.put("endtime", endtime+" 23:59:59");
    	}
		List<FssCustomerEntity> customers = customerService.findCustomerByParams(map);
    	model.addAttribute("page",customers);
    	model.addAttribute("customer",customer);
    	model.addAttribute("startime",startime);
    	model.addAttribute("endtime",endtime);
    	return "fss/account/customerList";
    }
	
	
	/**
	 * 查询客户账户信息
	 * @param request
	 * @param model
	 * @param fssAccount
	 * @return
	 */
	@RequestMapping(value = "/fss/account/customerAccountDetail/{cust_no}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object getCustomerAccountList(HttpServletRequest request,ModelMap model,@PathVariable String cust_no,FssAccountEntity fssAccount){	
		String accNo=fssAccount.getAccNo();
		String startime=request.getParameter("startime");
		String endtime=request.getParameter("endtime");
		
		Map map=new HashMap();
		if(StringUtils.isNotEmptyString(cust_no)){
			map.put("custNo", cust_no);
		}
		
		if(StringUtils.isNotEmptyString(accNo)){
			map.put("accNo",accNo);
		}
		if(StringUtils.isNotEmptyString(startime)){
    		map.put("startDate", startime+" 00:00:00");
    	}
    	if(StringUtils.isNotEmptyString(endtime)){
    		map.put("endDate", endtime+" 23:59:59");
    	}
		
    	List<FssAccountEntity> fssAccounts = fssAccountService.findCustomerAccountByParams(map);
//    	GqPageInfo pageInfo = new GqPageInfo(fssAccounts);
    	model.addAttribute("page",fssAccounts);
    	model.addAttribute("account",fssAccount);
    	model.addAttribute("startime",startime);
    	model.addAttribute("endtime",endtime);
    	return "fss/account/customerAccountDetail";
    }
	

}
