package com.gqhmt.fss.controller.api.account;

import com.gqhmt.fss.transferDataBean.account.CreateAccountByFuiou;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 富友开户,通用接口
     * @param createAccountByFuiou
     * @return
     */
    @RequestMapping(value = "/createAccountForFuiou",method = RequestMethod.POST)
    public Object ceeateAccount(CreateAccountByFuiou createAccountByFuiou){
        return null;
    }

}
