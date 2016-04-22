package com.gqhmt.DataMigration.account;

import com.github.pagehelper.PageHelper;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
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




    /**
     * 线上用户及 其他客户账户迁移
     */

    public void onlineAccountDataMig() {
        this.onlineAccountDataMig(0);
    }

    /**
     * 线上用户及 其他客户账户迁移
     * @param pageNum
     */
    public void onlineAccountDataMig(Integer pageNum){
        Integer pageSize = 1000;
        PageHelper.startPage(pageNum, pageSize);
        List<FundAccountEntity> list = fundAccountService.getFundsAccountByBusiType("3");
        for(FundAccountEntity f : list){
            CustomerInfoEntity customerInfoEntity = customerInfoService.getCustomerById(f.getCustId());

//            fssAccountService.createAccount("",gqgetFrontMchn,f.getUserName())
        }


    }

    /**
     * 借款账户迁移
     */
    public void loanOnlineAccountDataMig(){
        this.loanOnlineAccountDataMig(0);
    }

    /**
     * 借款账户迁移
     * @param pageNum
     */
    public void loanOnlineAccountDataMig(Integer pageNum){

    }


    /**
     * 出借账户迁移
     */
    public void lendOnLineAccountDataMig(){
        this.lendOnLineAccountDataMig(0);
    }
    /**
     * 出借账户迁移
     * @param pageNum
     */
    public void lendOnLineAccountDataMig(Integer pageNum){

    }

    /**
     * 纯线下账户导入
     */
    public void LoanOffLineAccountDataMig(){
        String classDriver = ResourceUtil.getValue("jdbc.jdbc","jdbc.driverClassName");
        String  url = ResourceUtil.getValue("jdbc.jdbc","dataMig.jdbc.url");
        String user = ResourceUtil.getValue("jdbc.jdbc","dataMig.jdbc.username");
        String pwd = ResourceUtil.getValue("jdbc.jdbc","dataMig.jdbc.password");
        try {
            Class.forName(classDriver);
            Connection conn = DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps = conn.prepareCall("select distinct \n" +
                    "          合同编号 " +
                    "          ,银行卡号 " +
                    "          ,主借人姓名" +
                    "          ,客户电话号码" +
                    "          ,主借人身份证号 " +
                    "          ,银行全称" +
                    "    from  transfer_basic");

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String  contractNo  = rs.getString(1);
                System.out.println(contractNo);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
