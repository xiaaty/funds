package com.gqhmt.DataMigration.dao;

import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Filename:    com.gqhmt.DataMigration.dao.FundAccountDao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/25 15:08
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/25  于泳      1.0     1.0 Version
 */
public class FundAccountDao extends SuperGqDao {

    private final static FundAccountDao fundAccountDao = new FundAccountDao();

    public static FundAccountDao getFundAccountDao(){
        return fundAccountDao;
    }

    private FundAccountDao(){}


    public CachedRowSet findOnlineAccount() throws Exception{

        String  sql = "SELECT * FROM `t_gq_fund_account` t1 WHERE t1.busi_type=3";

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        CachedRowSetImpl cs = new CachedRowSetImpl();
        cs.populate(rs);
        rs.close();
        ps.close();

        return  cs;

    }

    public FundAccountEntity findFundAccountPrimay(Long id) throws Exception{
        String  sql = "SELECT * FROM `t_gq_fund_account` t1 WHERE t1.id = "+id;

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        FundAccountEntity fundAccountEntity = null;
        if(rs.next()){
            fundAccountEntity  = new FundAccountEntity();
            fundAccountEntity.setId(rs.getLong("id"));

        }
        return  fundAccountEntity;
    }

    public CustomerInfoEntity findCustom(Long custId) throws Exception{
        String  sql = "SELECT * FROM `t_gq_customer_info` t1 WHERE t1.id = "+custId;

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        CustomerInfoEntity customerInfoEntity = null;
        if(rs.next()){
            customerInfoEntity  = new CustomerInfoEntity();
            customerInfoEntity.setId(rs.getLong("id"));

        }
        return  customerInfoEntity;
    }

    public BankCardInfoEntity findBankCardInfo(Long id) throws Exception{
        String  sql = "SELECT * FROM `t_gq_customer_info` t1 WHERE t1.id = "+id;

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        BankCardInfoEntity bankCardInfoEntity = null;
        if(rs.next()){
            bankCardInfoEntity  = new BankCardInfoEntity();
            bankCardInfoEntity.setId(rs.getInt("id"));
        }
        return  bankCardInfoEntity;
    }
}
