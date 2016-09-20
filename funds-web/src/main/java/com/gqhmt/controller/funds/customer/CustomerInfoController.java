package com.gqhmt.controller.funds.customer;

import com.google.common.collect.Maps;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.funds.architect.customer.bean.CustomerInfoDetialBean;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
public class CustomerInfoController {
	@Resource
    private CustomerInfoService customerInfoService;
	@Resource
	private BankCardInfoService bankCardInfoService;
	/**
	 * 查询客户核心信息列表
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException 
	 */
	@RequestMapping(value = "/funds/customer/customerList",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object queryCustomerList(HttpServletRequest request,ModelMap model,@RequestParam Map<String, String> map) throws FssException{
		List<CustomerInfoDetialBean> customers = customerInfoService.queryCustomerinfoList(map);
    	model.addAttribute("page",customers);
    	model.put("map",map);
    	return "funds/customer/customer_list";
    }
	/**
	 * jhz
	 * 查询富有核对银行卡信息
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
	 */
	@RequestMapping(value = "/checkCustomerInfo/queryForFuiou/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object queryForFuiou(HttpServletRequest request, ModelMap model, @PathVariable String  id) throws FssException{
		Map<String,String> returnMap= Maps.newHashMap();
		CustomerInfoDetialBean bean=customerInfoService.queryCustomerinfoById(Integer.valueOf(id));
		if (bean==null){
			returnMap.put("code","0001");
			returnMap.put("msg","该客户不存在");
			return returnMap;
		}
		Map<String,String > custmerMap = customerInfoService.userInfoQuery(id);
		if(custmerMap == null){
			returnMap.put("code","0001");
			returnMap.put("msg","未查询到相关富友信息");
			return returnMap;
		}
		LogUtil.info(this.getClass(),"查询富有，获取cust_id为"+id+"的客户信息");
		if(StringUtils.endsWith(custmerMap.get("capAcntNo"),bean.getBankNo())){
			returnMap.put("code","0000");
			returnMap.put("msg","银行卡信息已同步");
		}else{
			BankCardInfoEntity entity=bankCardInfoService.getBankCardById(bean.getBankId());
			if(entity==null){
				returnMap.put("code","0001");
				returnMap.put("msg","未查到该客户银行卡信息");
				return  returnMap;
			}
			entity.setBankLongName(custmerMap.get("bank_nm"));
			entity.setModifyTime(new Date());
			entity.setBankNo(custmerMap.get("capAcntNo"));
			entity.setParentBankId(custmerMap.get("parent_bank_id"));
			bankCardInfoService.update(entity);
			LogUtil.info(this.getClass(),"cust_id为"+id+"的客户的银行卡信息已更新为："+custmerMap.get("capAcntNo"));
			returnMap.put("code","0000");
			returnMap.put("msg","银行卡信息不一致，已更新");
		}
		return returnMap;
	}
	/**
	 * jhz
	 * 查询富有核对用户状态信息
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
	 */
	@RequestMapping(value = "/checkCustomerInfo/checkState/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object checkState(HttpServletRequest request, ModelMap model, @PathVariable String  id) throws FssException{
		Map<String,String> returnMap= Maps.newHashMap();
		CustomerInfoDetialBean bean=customerInfoService.queryCustomerinfoById(Integer.valueOf(id));
		CustomerInfoEntity entity=customerInfoService.getCustomerById(Long.valueOf(id));
		if (bean==null){
			returnMap.put("code","0001");
			returnMap.put("msg","该客户不存在");
			return returnMap;
		}
		Map<String,String > custmerMap = customerInfoService.userInfoQuery(id);
		if(custmerMap == null){
			LogUtil.info(this.getClass(),"未查询到相关富友信息");
			returnMap.put("code","0001");
			returnMap.put("msg","未查询到相关富友信息");
			return returnMap;
		}
		LogUtil.info(this.getClass(),"查询富有，获取cust_id为"+id+"的客户信息");
		LogUtil.info(this.getClass(),"富友用户状态为："+custmerMap.get("user_st"));
		if(StringUtils.equals(custmerMap.get("user_st"),"1")){
			if(bean.getIsvalid()==0){
				returnMap.put("code","0000");
				returnMap.put("msg","用户状态已同步");
			}else{
				customerInfoService.updateCustomerInfo(entity,0);
				returnMap.put("code","0000");
				returnMap.put("msg","用户状态已更新，现与富友同步");
			}
		}else if(StringUtils.equals(custmerMap.get("user_st"),"2")){
			if(bean.getIsvalid()==1){
				returnMap.put("code","0000");
				returnMap.put("msg","用户状态已同步");
			}else{
				customerInfoService.updateCustomerInfo(entity,1);
				returnMap.put("code","0000");
				returnMap.put("msg","用户状态已更新，现与富友同步");
			}
		}else if("3".equals(custmerMap.get("user_st"))){
			returnMap.put("code","0002");
			returnMap.put("msg","富友处于申请注销状态");
		}
		return returnMap;
	}
	/**
	 * jhz
	 * 销户确认
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
	 */
	@RequestMapping(value = "/checkCustomerInfo/dropAccount/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object dropAccount(HttpServletRequest request, ModelMap model, @PathVariable String  id) throws FssException{
		Map<String,String> returnMap= Maps.newHashMap();
		boolean result= customerInfoService.dropAccount(id);
		if(result){
			returnMap.put("code","0000");
			returnMap.put("msg","销户成功");
		}else {
			returnMap.put("code","0001");
			returnMap.put("msg","销户失败");
		}
			return returnMap;
	}


}
