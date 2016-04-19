package com.gqhmt.DataMigration.account;

import com.github.pagehelper.PageHelper;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.DataMigration.account.OnlineAccountDataMigration
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/7 18:13
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/7  于泳      1.0     1.0 Version
 */
@Service
public class OnlineAccountDataMigration {

    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FssAccountService fssAccountService;

    @Resource
    private CustomerInfoService customerInfoService;


    private final static String gqgetFrontMchn = "";

    private final static String gqgetBackendMchn= "";

    private final static String loan = "";



    public void onlineAccountDataMig(Integer pageNum){
        Integer pageSize = 1000;
        PageHelper.startPage(pageNum, pageSize);
        List<FundAccountEntity> list = fundAccountService.getFundsAccountByBusiType("3");
        for(FundAccountEntity f : list){
            CustomerInfoEntity customerInfoEntity = customerInfoService.getCustomerById(f.getCustId());

//            fssAccountService.createAccount("",gqgetFrontMchn,f.getUserName())
        }


    }
}
