package com.gqhmt.controller.api.loan;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.service.loan.ICreateLoan;
import com.gqhmt.extServInter.service.tender.IBidTender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * stController
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月22日
 * Description:
 * <p>投标</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月22日  柯禹来      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class LoanAccountApi {

    @Resource
    private ICreateLoan createLoanImpl;
    
    /**
     * author:柯禹来
     * time:2016年3月7日
     * function：开户
     */
    @RequestMapping(value = "/createLoanAccount",method = RequestMethod.POST)
    public Object createLoanAccount(CreateLoanAccountDto loanAccountDto){
    	Response response=new Response();
    	try {
    		response = createLoanImpl.excute(loanAccountDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
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
