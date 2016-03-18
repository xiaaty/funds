package com.gqhmt.controller.api.loan;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.EnterAccountDto;
import com.gqhmt.extServInter.dto.loan.FailedBidDto;
import com.gqhmt.extServInter.dto.loan.LendingDto;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawDto;
import com.gqhmt.extServInter.service.loan.IEnterAccount;
import com.gqhmt.extServInter.service.loan.ILending;
import com.gqhmt.extServInter.service.loan.IMortgageeWithDraw;
import com.gqhmt.extServInter.service.loan.impl.FailedBidImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月8日  jhz      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssLoan {

    @Resource
    private FailedBidImpl failedBidImpl;
    
    @Resource
    private ILending lendingImpl;
    
    @Resource
    private IMortgageeWithDraw mortgageeWithDrawImpl;
    
    @Resource
    private IEnterAccount enterAccountImpl;
    
   /**
    * 
    * author:jhz
    * time:2016年2月22日
    * function：流标
    */
    @RequestMapping(value = "/loan/failedBid",method = RequestMethod.POST)
    public Object ceeateAccount(FailedBidDto failedBidDto){
    	Response response= null;
        try {
        	
             response = failedBidImpl.excute(failedBidDto);
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
    @RequestMapping(value = "/loan/lending",method = RequestMethod.POST)
    public Object changeBankCard(@RequestBody LendingDto lendingDto){
    	Response response= null;
    	try {
    		response = lendingImpl.excute(lendingDto);
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
    @RequestMapping(value = "/loan/mortgageeWithDraw",method = RequestMethod.POST)
    public Object MortgageeWithDraw(MortgageeWithDrawDto mortgageeWithDrawDto){
    	Response response= null;
    	try {
    		response = mortgageeWithDrawImpl.excute(mortgageeWithDrawDto);
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
    @RequestMapping(value = "/loan/enterAccount",method = RequestMethod.POST)
    public Object EnterAccount(@RequestBody EnterAccountDto enterAccountDto){
    	Response response= null;
    	try {
    		response = enterAccountImpl.excute(enterAccountDto);
    	} catch (Exception e) {
    		response = this.excute(e);
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
