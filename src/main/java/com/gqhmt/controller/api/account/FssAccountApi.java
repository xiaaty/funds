package com.gqhmt.controller.api.account;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.account.BankCardDto;
import com.gqhmt.extServInter.dto.account.BankCardResponse;
import com.gqhmt.extServInter.dto.account.ChangeBankCardDto;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.extServInter.dto.asset.TradeFlowResponse;
import com.gqhmt.extServInter.dto.fund.BankDto;
import com.gqhmt.extServInter.dto.fund.BankResponse;
import com.gqhmt.extServInter.dto.fund.TradflowDto;
import com.gqhmt.extServInter.service.account.IBankCardList;
import com.gqhmt.extServInter.service.account.IBankList;
import com.gqhmt.extServInter.service.account.IChangeBankCardAccount;
import com.gqhmt.extServInter.service.account.ICreateAccount;
import com.gqhmt.extServInter.service.asset.IAccountTradFlow;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private IBankList bankListImpl;
    
    @Resource
    private IBankCardList bankCardListImpl;
    
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
    @RequestMapping(value = "/createAccount",method = RequestMethod.POST)
    public Object ceeateAccount(CreateAccountDto createAccountByFuiou){
    	Response response= null;
        try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
             response = createAccountImpl.excute(createAccountByFuiou);
        } catch (Exception e) {
            response = this.excute(e);
        }
        return response;
    }
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
    		response = changeBankCardAccountImpl.excute(changeBankCardDto);
    	} catch (Exception e) {
            response = this.excute(e);
    	}
    	return response;
    }

    /**
     * author:柯禹来
     * time:2016年3月1日
     * function：银行信息
     */
    @RequestMapping(value = "/getBankInfo",method = RequestMethod.POST)
    public Object getBankInfo(BankDto bank) throws APIExcuteErrorException{
    	BankResponse response= new BankResponse();
    	try {
    		response= (BankResponse)bankListImpl.excute(bank);
    		List<BankEntity> banklist= response.getBanklist();
    		/*for(BankEntity bankEntity:banklist){
    			bankEntity.getBankCode();
    			bankEntity.getBankName();
    		}*/
    	} catch (FssException e) {
             response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    /**
     * author:柯禹来
     * time:2016年3月1日
     * function：银行卡信息
     */
    @RequestMapping(value = "/getBankCardInfo",method = RequestMethod.POST)
    public Object getBankCardInfo(BankCardDto bankcard){
    	BankCardResponse response= new BankCardResponse();
    	try {
    		response = (BankCardResponse)bankCardListImpl.excute(bankcard);
    		List<BankCardInfoEntity> bankcardlist=response.getBankcardlist();
    	/*	for (BankCardInfoEntity bankCardEntity:bankcardlist) {
    			String bankNo = bankCardEntity.getBankNo();
    			String bankLongName = bankCardEntity.getBankLongName();
    			String bankSortName = bankCardEntity.getBankSortName();
			}*/
    		
    	} catch (Exception e) {
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    
    /**
     * author:柯禹来
     * time:2016年3月1日
     * function：资金流水
     */
    @RequestMapping(value = "/getTradFlow",method = RequestMethod.POST)
    public Object getTradFlow(TradflowDto tradflowDto){
    	TradeFlowResponse response= new TradeFlowResponse();
    	try {
    		response = (TradeFlowResponse)accountTradflowImpl.excute(tradflowDto);
    		List<FundFlowBean> fundflowlist=response.getList();
    		response.getList();
    	} catch (Exception e) {
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
