package com.gqhmt.controller.api.loan;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.CardChangeDto;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.extServInter.dto.loan.MarginDto;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import com.gqhmt.extServInter.service.loan.IChangeCard;
import com.gqhmt.extServInter.service.loan.ICreateLoan;
import com.gqhmt.extServInter.service.loan.ILoadWithDraw;
import com.gqhmt.extServInter.service.loan.IMarginSendBack;
import com.gqhmt.extServInter.service.loan.IRepayment;
import com.gqhmt.pay.service.account.IFundsAccount;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
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
	private ApplicationContext applicationContext;
    @Resource
    private ICreateLoan createLoanImpl;
    @Resource
    private IChangeCard changeCardImpl;
    @Resource
    private IFundsAccount fundsAccountImpl;
    @Resource
    private ILoadWithDraw loadWithDrawImpl;
    @Resource
    private IMarginSendBack marginSendBackImpl;
    @Resource
    private IRepayment repaymentImpl;
    
    /**
     * author:柯禹来
     * time:2016年3月7日
     * function：开户
     */
    @RequestMapping(value = "/createLoanAccount",method = RequestMethod.POST)
    public Object createLoanAccount(@RequestBody CreateLoanAccountDto loanAccountDto){
    	Response response=new Response();
    	try {
    		response = createLoanImpl.execute(loanAccountDto);
    	} catch (Exception e) {
    		response=this.execute(e);
    	}
    	return response;
    }
    
    /**
     * 银行卡变更
     * @param
     * @return
     */
    @RequestMapping(value = "/bankCardChange",method = RequestMethod.POST)
    public Object bankCardChange(@RequestBody CardChangeDto changeCardDto){
    	Response response=new Response();
    	try {
    		response = changeCardImpl.execute(changeCardDto);
    	} catch (Exception e) {
//    		LogUtil.error(this.getClass(), e);
//    		response.setResp_code(e.getMessage());
    		response=this.execute(e);
    	}
    	return response;
    }
    
    /**
     * 借款人提现
     * @param
     * @return
     */
    @RequestMapping(value = "/createWithDrawApply",method = RequestMethod.POST)
    public Object createWithDrawApply(@RequestBody LoanWithDrawApplyDto loanWithDrawApplyDto){
    	Response response=new Response();
    	try {
    		response = loadWithDrawImpl.execute(loanWithDrawApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * 保证金退还
     * @param seqNo
     * @param mchn
     * @return
     */
    @RequestMapping(value = "/marginSendBack",method = RequestMethod.POST)
    public Object marginSendBack(@RequestBody MarginDto dto){
    	Response response=new Response();
    	try {
    		response = marginSendBackImpl.execute(dto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * 还款划扣
     * @return
     */
    @RequestMapping(value = "/createRefundDraw",method = RequestMethod.POST)
    public Object createRefundDraw(@RequestBody RepaymentDto dto){
    	Response response=new Response();
    	try {
    		response = repaymentImpl.execute(dto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }

	private Response execute(Exception e){
		LogUtil.error(this.getClass(), e);
		Response response = new Response();
		response.setResp_code(e.getMessage());
		return response;
	}
}
