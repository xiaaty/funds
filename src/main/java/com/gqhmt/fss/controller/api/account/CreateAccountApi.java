package com.gqhmt.fss.controller.api.account;

import com.gqhmt.annotations.API;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.fss.architect.order.entity.FssSeqOrderEntity;
import com.gqhmt.fss.event.account.CreateAccountEvent;
import com.gqhmt.fss.transferDataBean.account.CreateAccountByFuiou;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/11  于泳      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class CreateAccountApi {

    @Resource
    private ApplicationContext applicationContext;
    /**
     * 富友开户,通用接口
     * @param createAccountByFuiou
     * @return
     */
    @API
    @RequestMapping(value = "/createAccountForFuiou",method = RequestMethod.POST)
    public Object ceeateAccount(CreateAccountByFuiou createAccountByFuiou){

        try {
            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "0000";

    }

}
