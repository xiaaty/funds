package com.gqhmt.controller.api.loan;

import com.gqhmt.core.FssException;
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
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
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
    
    @Resource
    private FssAccountService fssAccountService;
    
    
   /**
    * 
    * author:jhz
    * time:2016年2月22日
    * function：流标
    */
    @RequestMapping(value = "/loan/failedBid",method = RequestMethod.POST)
    public Object ceeateAccount(@RequestBody FailedBidDto failedBidDto){
    	
    	Response response= null;
        try {
        	
             response = failedBidImpl.execute(failedBidDto);
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
     * @throws FssException 
     */
    @RequestMapping(value = "/loan/lending",method = RequestMethod.POST)
    public Object lending(@RequestBody LendingDto lendingDto) throws FssException{
    	//抵押权人对象
//    	if("11090001".equals(lendingDto.getTrade_type())){
//    	FssAccountEntity mortgageeAccount = fssAccountService.getFssAccountByAccNo(lendingDto.getMortgagee_acc_no());
//    	if(!"11020009".equals(mortgageeAccount.getTradeType()))  throw new FssException("该用户非抵押权人账户");
//    	}
//    	//借款人对象
//    	FssAccountEntity lendingAccount = fssAccountService.getFssAccountByAccNo(lendingDto.getAcc_no());
//    	if(!"11020012".equals(lendingAccount.getTradeType()))  throw new FssException("该用户非借款人账户");
    	
    	Response response= null;
    	try {
    		response = lendingImpl.execute(lendingDto);
    	} catch (Exception e) {
            response = this.excute(e);
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年3月8日
     * function：抵押权人提现
     * @throws FssException 
     */
    @RequestMapping(value = "/loan/mortgageeWithDraw",method = RequestMethod.POST)
    public Object MortgageeWithDraw(@RequestBody MortgageeWithDrawDto mortgageeWithDrawDto) throws FssException{
    	//抵押权人对象
    	/*FssAccountEntity mortgageeAccount = fssAccountService.getFssAccountByAccNo(mortgageeWithDrawDto.getMortgagee_acc_no());
    	if(!"11020009".equals(mortgageeAccount.getTradeType()))  throw new FssException("该用户非抵押权人账户");
    	*/
    	Response response= null;
    	try {
    		response = mortgageeWithDrawImpl.execute(mortgageeWithDrawDto);
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
    		response = enterAccountImpl.execute(enterAccountDto);
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
