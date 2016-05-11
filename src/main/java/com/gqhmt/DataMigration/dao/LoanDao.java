package com.gqhmt.DataMigration.dao;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Filename:    com.gqhmt.DataMigration.dao.LoanDao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/25 14:48
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/25  于泳      1.0     1.0 Version
 */
public class LoanDao extends SuperLoan {


    private final static LoanDao loanDao = new LoanDao();

    public static LoanDao getLoanDao(){
        return loanDao;
    }

    private LoanDao(){}

    public  CachedRowSetImpl findAllData() throws Exception{

        String  sql = "select distinct 合同编号 ,银行卡号 ,主借人姓名,客户电话号码,主借人身份证号,银行全称 from  transfer_basic";

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        CachedRowSetImpl cs = new CachedRowSetImpl();
        cs.populate(rs);
        rs.close();
        ps.close();
        conn.close();
        return  cs;
    }


}
