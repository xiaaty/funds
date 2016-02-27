package com.gqhmt.controller.api.trade;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.trade.RechargeSuccessDto;
import com.gqhmt.extServInter.dto.trade.WithdrawSuccessDto;
import com.gqhmt.extServInter.service.trade.IRechargeSuccess;
import com.gqhmt.extServInter.service.trade.IWithdrawSuccess;

/**
* <h1>入账控制类</h1>
* <p>
* </p>
* 
* @author jhz
* @version 1.0
* @createTime:2016-02-18 
*/
@Controller
@RequestMapping(value = "/api")
public class FssAccountSuccessApi {
	@Resource
	private IRechargeSuccess rechargeSuccessImpl;
	
	@Resource
	private IWithdrawSuccess withdrawSuccessSuccess;
	/**
	 * 
	 * author:jhz
	 * time:2016年2月27日
	 * function：PC端网银充值成功入账
	 */
    @RequestMapping(value = "/freeze",method = RequestMethod.POST)
    public Object rechargeSuccess(RechargeSuccessDto rechargeSuccessDto){
    	Response response=new Response();
    	try {
    		response = rechargeSuccessImpl.excute(rechargeSuccessDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    /**
	 * 
	 * author:jhz
	 * time:2016年2月27日
	 * function：PC端提现成功入账
	 */
    @RequestMapping(value = "/freeze",method = RequestMethod.POST)
    public Object freeze(WithdrawSuccessDto withdrawSuccessDto){
    	Response response=new Response();
    	try {
    		response = withdrawSuccessSuccess.excute(withdrawSuccessDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
	
}
