package com.gqhmt.controller.api.account;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.account.CreateAccountDto;
import com.gqhmt.extServInter.dto.account.LogOutAccountDto;
import com.gqhmt.extServInter.dto.account.QueryAccountDto;
import com.gqhmt.extServInter.dto.account.VerifiedAccountDto;
import com.gqhmt.extServInter.service.account.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
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
    private IQueryAccount queryAccountImpl;


    @Resource
    private IVerifiedCreateAccount verifiedCreateAccountImpl;

    @Resource
    private ICreateBidAccount createBidAccountImpl;

    @Resource
    private ILogOutAccount logOutAccountImpl;
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
    public Object createAccount(CreateAccountDto createAccountByFuiou){
    	Response response= new Response();
        try {
             response = createAccountImpl.execute(createAccountByFuiou);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }

    /**
     * jhz
     * 查询账户开户状态
     * @param queryAccountDto
     * @return
     */
    @RequestMapping(value = "/queryAccountState",method = {RequestMethod.GET,RequestMethod.POST})
    public Object createAccount( QueryAccountDto queryAccountDto){
    	Response response= new Response();
        try {
             response = queryAccountImpl.execute(queryAccountDto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }



    /**
     * 提供给冠E通后台调用开户接口
     * @param createAccountByFuiou
     * @return
     */
    @RequestMapping(value = "/createBackedAccount",method = {RequestMethod.GET,RequestMethod.POST})
    public Object createGetAccount(@RequestBody CreateAccountDto createAccountByFuiou){
        Response response= new Response();
        try {
            response = createAccountImpl.execute(createAccountByFuiou);
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


    /**
     *调用实名认证开户接口
     * @param dto
     * @return
     */
    @RequestMapping(value = "/createVerifiedAccount",method = {RequestMethod.GET,RequestMethod.POST})
    public Object createVerifiedAccount(VerifiedAccountDto dto){
        Response response= new Response();
        try {
            response = verifiedCreateAccountImpl.execute(dto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }

    /**
     * 标的开户
     * @param dto
     * @return
     */
    @RequestMapping(value = "/createBidAccount",method = {RequestMethod.GET,RequestMethod.POST})
    public Object createBidAccount(VerifiedAccountDto dto){
        Response response= new Response();
        try {
            response = createBidAccountImpl.execute(dto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }

    /**
     *账户注销
     * @param dto
     * @return
     */
    @RequestMapping(value = "/logOutAccount",method = {RequestMethod.GET,RequestMethod.POST})
    public Object logOutAccount(LogOutAccountDto dto){
        Response response= new Response();
        try {
            response = logOutAccountImpl.execute(dto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }
}
