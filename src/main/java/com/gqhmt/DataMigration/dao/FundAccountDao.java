package com.gqhmt.DataMigration.dao;

import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.util.LogUtil;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        String  sql = "SELECT * FROM `t_gq_fund_account` t1 WHERE t1.busi_type=0 AND t1.`has_Third_Account` = 2 order by id ";

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

    public FundAccountEntity findFundAccountPrimay(Long id) throws Exception{
        String  sql = "SELECT * FROM `t_gq_fund_account` t1 WHERE t1.id = "+id;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FundAccountEntity fundAccountEntity = null;
        try{

            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){
                fundAccountEntity  = new FundAccountEntity();
                fundAccountEntity.setId(rs.getLong("id"));
                fundAccountEntity.setUserName(rs.getString("user_name"));
                fundAccountEntity.setCustName(rs.getString("cust_name"));
            }

        }catch (Exception e){
            LogUtil.error(getClass(),e);
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }

        return  fundAccountEntity;
    }

    public CustomerInfoEntity findCustom(Long custId) throws Exception{
        String  sql = "SELECT * FROM `t_gq_customer_info` t1 WHERE t1.id = "+custId;


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CustomerInfoEntity customerInfoEntity = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                customerInfoEntity = new CustomerInfoEntity();
                customerInfoEntity.setId(rs.getLong("id"));
                customerInfoEntity.setBankId(rs.getInt("bank_id"));
                customerInfoEntity.setCertNo(rs.getString("CERT_NO"));
                customerInfoEntity.setCustomerName(rs.getString("CUSTOMER_NAME"));
                customerInfoEntity.setMobilePhone(rs.getString("MOBILE_PHONE"));
                customerInfoEntity.setCustomerType(rs.getInt("CUSTOMER_TYPE"));  // 客户类型 1：借款用户 2:借款共借人 3：线下出借用户 4：线上出借注册用户 6:抵押人 7：抵押权人 8：线下赎回接标紧急用户（内部用）9：A0公司内用用户

                customerInfoEntity.setSendMsgRechargeWithdrawFouyou(rs.getInt("send_msg_recharge_withdraw_fouyou"));  //send_msg_recharge_withdraw_fouyou

                customerInfoEntity.setSendMsgTransferOutFouyou(rs.getInt("send_msg_transfer_out_fouyou"));   //出账是否发短信0-发送；1-不发送

                customerInfoEntity.setSendMsgTransferInFouyou(rs.getInt("send_msg_transfer_in_fouyou"));    //send_msg_transfer_in_fouyou

                customerInfoEntity.setSendMsgTransferAllFouyou(rs.getInt("send_msg_transfer_all_fouyou"));  //汇总是否发短信0-发送；1-不发送


            }
        } catch (SQLException e) {
            LogUtil.error(getClass(),e);
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }


        return  customerInfoEntity;
    }

    public BankCardInfoEntity findBankCardInfo(Integer id,Long cust_id) throws Exception{

        String  sql = "SELECT * FROM `t_gq_bank_info` t1 WHERE t1.id = "+id;
        if(id == null || id == 0){
            sql = "SELECT * FROM `t_gq_bank_info` t1 WHERE t1.cust_id = "+cust_id;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BankCardInfoEntity bankCardInfoEntity = null;
        try{


            conn = getConnection();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){
                bankCardInfoEntity  = new BankCardInfoEntity();
                bankCardInfoEntity.setId(rs.getInt("id"));
                bankCardInfoEntity.setBankNo(rs.getString("bank_no"));
                bankCardInfoEntity.setParentBankId(rs.getString("parent_bank_id"));
                bankCardInfoEntity.setCityId(rs.getString("city_id"));

            }

        }catch (Exception e){
            LogUtil.error(getClass(),e);
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }

        return  bankCardInfoEntity;
    }
}
