package com.gqhmt.controller.api.loan;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.callback.loan.EnterAccountCallback;
import com.gqhmt.extServInter.callback.loan.FailedBidCallback;
import com.gqhmt.extServInter.callback.loan.MortgageeWithDrawCallback;
import com.gqhmt.extServInter.callback.loan.LendingCallback;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.EnterAccountResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月8日
 * Description:	回调控制类
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月8日  jhz      1.0     1.0 Version
 */
@Controller
@RequestMapping(value = "/api")
public class FssCallback {

    @Resource
    private EnterAccountCallback enterAccountCallback;
    
    @Resource
    private FailedBidCallback failedBidCallback;
    
    @Resource
    private LendingCallback lendingCallback;
    
    @Resource
    private MortgageeWithDrawCallback mortgageeWithDrawCallback;
    
   /**
    * 
    * author:jhz
    * time:2016年2月22日
    * function：流标回调
    */
    @RequestMapping(value = "/loan/failedBidCallback",method = RequestMethod.POST)
    public Object ceeateAccount(String mchnNo,String seqNo){
    	Response response= null;
        try {
        	
             response = failedBidCallback.getResponse(mchnNo, seqNo);
        } catch (Exception e) {
            response = this.excute(e);
        }
        return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年3月8日
     * function：借款人放款
     */
    @RequestMapping(value = "/loan/lendingCallback",method = RequestMethod.POST)
    public Object changeBankCard(String mchnNo,String seqNo){
    	Response response= null;
    	try {
    		response = lendingCallback.getResponse(mchnNo, seqNo);
    	} catch (Exception e) {
            response = this.excute(e);
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年3月8日
     * function：抵押权人放款
     */
    @RequestMapping(value = "/loan/mortgageeWithDrawCallback",method = RequestMethod.POST)
    public Object MortgageeWithDraw(String mchnNo,String seqNo){
    	Response response= null;
    	try {
    		response = mortgageeWithDrawCallback.getResponse(mchnNo, seqNo);
    	} catch (Exception e) {
    		response = this.excute(e);
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年3月8日
     * function：入账接口
     */
	@RequestMapping(value = "/loan/enterAccountCallback",method = RequestMethod.POST)
    public Object EnterAccount(List<Map<String,String>> maps){
    	List<EnterAccountResponse> response=null;
    	try {
    		 response = enterAccountCallback.getResponse(maps);
    	} catch (Exception e) {
    		response = (List<EnterAccountResponse>) this.excute(e);
    	}
    	return response;
    }

    private Response excute(Exception e){
        LogUtil.error(this.getClass(), e);
        Response response = new Response();
        response.setResp_code(e.getMessage());
        return response;
    }

}
