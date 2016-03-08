package com.gqhmt.controller.api.loan;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.CardChangeDto;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.extServInter.service.loan.IChangeCard;
import com.gqhmt.extServInter.service.loan.ICreateLoan;
import com.gqhmt.extServInter.service.loan.ILoadWithDraw;
import com.gqhmt.pay.service.account.IFundsAccount;
import com.gqhmt.pay.service.loan.IWithDrawApply;

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
    @Resource
    private IChangeCard changeCardImpl;
    @Resource
    private IFundsAccount fundsAccountImpl;
    @Resource
    private ILoadWithDraw loadWithDrawImpl;
    @Resource
    private IWithDrawApply withDrawApplyImpl;
    
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

    /**
     * 银行卡变更
     * @param
     * @return
     */
    @RequestMapping(value = "/queryAccountByAccNo",method = RequestMethod.POST)
    public Object queryAccountByAccNo(CardChangeDto changeCardDto){
    	Response response=new Response();
    	try {
    		response = changeCardImpl.excute(changeCardDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * 银行卡变更完成通知借款系统
     * @param
     * @return
     */
    @RequestMapping(value = "/bankCardChangeCallBack",method = RequestMethod.POST)
    public Object bankCardChangeCallBack(String seqNo,String mchn){
    	Response response=new Response();
    	try {
    		response = fundsAccountImpl.bankCardChangeCallBack(seqNo,mchn);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }

    /**
     * 借款人提现
     * @param
     * @return
     */
    @RequestMapping(value = "/createWithDrawApply",method = RequestMethod.POST)
    public Object createWithDrawApply(LoanWithDrawApplyDto loanWithDrawApplyDto){
    	Response response=new Response();
    	try {
    		response = loadWithDrawImpl.excute(loanWithDrawApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * 借款人提现通知
     * @param
     * @return
     */
    @RequestMapping(value = "/withDrasApplyCallBack",method = RequestMethod.POST)
    public Object withDrasApplyCallBack(String seqNo,String mchn){
    	Response response=new Response();
    	try {
    		response = withDrawApplyImpl.withDrasApplyCallBack(seqNo,mchn);
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
