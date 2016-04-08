package com.gqhmt.controller.api.account;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.account.ChangeBankCardDto;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.extServInter.service.account.IChangeBankCardAccount;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.pay.service.account.IFundsAccount;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.controller.api.account.CreateAccountApi
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/11 15:20
 * Description:
 * <p>创建账户API</p>
 * <p>创建账户</p>
 * <p>变更银行卡</p>
 * <p>变更银行卡结果查询</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/11  于泳      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssAccountApi {

    @Resource
    private ApplicationContext applicationContext;
    
    @Resource
    private ICreateAccount createAccountImpl;
    
    @Resource
    private IChangeBankCardAccount changeBankCardAccountImpl;
    
    @Resource
    private IFundsAccount fundsAccountImpl;
    
    
    
    /**
     * 富友开户,通用接口
     * @param createAccountByFuiou
     * @return
     */
//    @API
//    @RequestMapping(value = "/createAccountForFuiou",method = RequestMethod.POST)
//    public Object ceeateAccount(CreateAccountByFuiou createAccountByFuiou){
//
//        try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return "0000";
//
//    }
   /**
    * 
    * author:jhz
    * time:2016年2月22日
    * function：创建账户
    */
    @RequestMapping(value = "/createAccount",method = {RequestMethod.GET,RequestMethod.POST})
    public Object ceeateAccount(CreateAccountDto createAccountByFuiou){
    	Response response= new Response();
        try {
             response = createAccountImpl.execute(createAccountByFuiou);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }
    
/*    @RequestMapping(value = "/createAccount",method = RequestMethod.POST)
    public Object ceeateAccount(CreateAccountDto createAccountByFuiou){
    	CreateAccountResponse response= null;
        try {
             response = fundsAccountImpl.createFundAccount(createAccountByFuiou);
        } catch (Exception e) {
            response = this.execute2(e);
        }
        return response;
    }*/
    
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：变更银行卡
     */
    @RequestMapping(value = "/changeBankCard",method = RequestMethod.POST)
    public Object changeBankCard(ChangeBankCardDto changeBankCardDto){
    	Response response= null;
    	try {
    		response = changeBankCardAccountImpl.execute(changeBankCardDto);
    	} catch (Exception e) {
            response = this.execute(e);
    	}
    	return response;
    }

    private Response execute(Exception e){
        LogUtil.error(this.getClass(), e);
        Response response = new Response();
        response.setResp_code(e.getMessage());
        return response;
    }
    
    
  /*  private CreateAccountResponse execute2(Exception e){
    	LogUtil.error(this.getClass(), e);
    	CreateAccountResponse response = new CreateAccountResponse();
    	response.setResp_code(e.getMessage());
    	return response;
    }*/

}
