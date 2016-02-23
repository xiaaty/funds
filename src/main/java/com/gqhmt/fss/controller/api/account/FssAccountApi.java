package com.gqhmt.fss.controller.api.account;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gqhmt.annotations.API;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.account.AccountAccessDto;
import com.gqhmt.extServInter.dto.account.AssetDto;
import com.gqhmt.extServInter.dto.account.ChangeBankCardDto;
import com.gqhmt.extServInter.dto.account.CreateAccountByFuiouDto;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.extServInter.dto.fund.TradingRecordDto;
import com.gqhmt.extServInter.service.account.IAccountAccess;
import com.gqhmt.extServInter.service.account.IAccountBanlance;
import com.gqhmt.extServInter.service.account.IAccountTradFlow;
import com.gqhmt.extServInter.service.account.IChangeBankCardAccount;
import com.gqhmt.extServInter.service.account.ICost;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.extServInter.service.account.ITransaction;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.fss.controller.api.account.CreateAccountApi
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
    private IAccountAccess accountAccessImpl;
    
    @Resource
    private IAccountBanlance accountBanlanceImpl;
    
    @Resource
    private ITransaction transactionImpl;
    
    @Resource
    private ICost costImpl;
    
    @Resource
    private IAccountTradFlow accountTradflowImpl;
    
    
    
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
    @API
    @RequestMapping(value = "/createAccount",method = RequestMethod.POST)
    public Object ceeateAccount(CreateAccountByFuiouDto createAccountByFuiou){
    	Response response=new Response();
        try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
             response = createAccountImpl.excute(createAccountByFuiou);
        } catch (Exception e) {
        	LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
        }
        return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：变更银行卡
     */
    @API
    @RequestMapping(value = "/changeBankCard",method = RequestMethod.POST)
    public Object changeBankCard(ChangeBankCardDto changeBankCardDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = changeBankCardAccountImpl.excute(changeBankCardDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * author:柯禹来
     * time:2016年2月22日
     * function：账户余额查询
     */
    @API
    @RequestMapping(value = "/accountBanlanceInfo",method = RequestMethod.POST)
    public Object accountBanlance(AccountAccessDto accountbalance){
    	Response response=new Response();
    	try {
    		response = accountBanlanceImpl.excute(accountbalance);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    
    /**
     * author:柯禹来
     * time:2016年2月22日
     * function：账户资产查询
     */
    @API
    @RequestMapping(value = "/accountAssetInfo",method = RequestMethod.POST)
    public Object accountAsset(AssetDto asset){
    	Response response=new Response();
    	try {
    		response = accountAccessImpl.excute(asset);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * author:柯禹来
     * time:2016年2月22日
     * function：交易记录查询
     */
    @API
    @RequestMapping(value = "/tradeInfo",method = RequestMethod.POST)
    public Object accountTradeInfo(TradingRecordDto trade){
    	Response response=new Response();
    	try {
    		response = transactionImpl.excute(trade);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * author:柯禹来
     * time:2016年2月22日
     * function：账户资金流水查询
     */
    @API
    @RequestMapping(value = "/fundFlowInfo",method = RequestMethod.POST)
    public Object fundFlow(TradflowDto funddto){
    	Response response=new Response();
    	try {
    		response = accountTradflowImpl.excute(funddto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * author:柯禹来
     * time:2016年2月22日
     * function：费用接口
     */
    @API
    @RequestMapping(value = "/costInfo",method = RequestMethod.POST)
    public Object costInfo(TradflowDto funddto){
    	Response response=new Response();
    	try {
    		response = costImpl.excute(funddto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    
    
    

}
