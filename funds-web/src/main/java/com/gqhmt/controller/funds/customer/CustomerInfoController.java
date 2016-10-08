package com.gqhmt.controller.funds.customer;

import com.google.common.collect.Maps;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.funds.architect.customer.bean.CustomerInfoDetialBean;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
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
	 * 查询富有展示客户信息
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
	 */
	@RequestMapping(value = "/customerInfo/checkCustomerInfo/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object checkCustomerInfo(HttpServletRequest request, ModelMap model, @PathVariable String  id) throws FssException{
		Map<String,Object> returnMap= Maps.newHashMap();
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
		returnMap.put("code","0000");
		returnMap.put("msg","成功");
		returnMap.put("bean",bean);
		returnMap.put("custmerMap",custmerMap);
		return returnMap;
	}
	/**
	 * jhz
	 * 查询富有核对银行卡信息
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
	 */
	@RequestMapping(value = "/checkCustomerInfo/checkbankNo/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object queryForFuiou(HttpServletRequest request, ModelMap model, @PathVariable String  id,String bankCode,String bankName,String bankNo) throws FssException{
		Map<String,String> returnMap= Maps.newHashMap();
		CustomerInfoDetialBean bean=customerInfoService.queryCustomerinfoById(Integer.valueOf(id));
		if (bean==null){
			returnMap.put("code","0001");
			returnMap.put("msg","该客户不存在");
			return returnMap;
		}
		BankCardInfoEntity entity=bankCardInfoService.getBankCardById(bean.getBankId());
		if(entity==null){
			returnMap.put("code","0001");
			returnMap.put("msg","未查到该客户银行卡信息");
			return  returnMap;
		}
		entity.setBankLongName(bankName);
		entity.setBankSortName(Application.getInstance().getDictName("9703"+bankCode));
		entity.setModifyTime(new Date());
		entity.setBankNo(bankNo);
		entity.setParentBankId(bankCode);
		bankCardInfoService.update(entity);
		LogUtil.info(this.getClass(),"cust_id为"+id+"的客户的银行卡信息已更新为："+bankNo);
		returnMap.put("code","0000");
		returnMap.put("msg","银行卡信息不一致，已更新");
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
//		boolean result= true;
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
