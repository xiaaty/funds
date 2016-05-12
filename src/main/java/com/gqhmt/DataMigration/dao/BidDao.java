package com.gqhmt.DataMigration.dao;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.util.LogUtil;
import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Filename:    com.gqhmt.DataMigration.dao.BidDao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/25 16:18
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/25  于泳      1.0     1.0 Version
 */
public class BidDao extends SuperGqDao {

    private final static BidDao bidDao  = new BidDao();

    public static BidDao getBidDao(){
        return bidDao;
    }

    private BidDao(){}

    public boolean isContractNo(String contractNo) throws Exception{

        String  sql = "SELECT * FROM `t_gq_bid` t1 WHERE t1.contract_no = "+contractNo;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{

            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }
        }catch (Exception e){

        }finally {
            close(rs);
            close(ps);
            close(conn);
        }


        return  false;

    }

    public CachedRowSet findAllBid() throws Exception{
        String  sql = "SELECT * FROM `t_gq_bid` t1";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CachedRowSetImpl cs = null;
        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            cs = new CachedRowSetImpl();
            cs.populate(rs);

        }catch (Exception e){

        }finally {
            close(rs);
            close(ps);
            close(conn);
        }



        return  cs;
    }

    public List<Bid> findAllBid(Long custId) throws Exception{
        String  sql = "SELECT * FROM `t_gq_bid` t1 where t1.customer_id = "+custId;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Bid> list = new ArrayList<>();
        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Bid bid = new Bid();
                bid.setId(rs.getInt("id"));
                bid.setCustomerId(rs.getInt("customer_id"));
                bid.setContractNo(rs.getString("contract_no"));
                list.add(bid);

            }

        }catch (Exception e){
            LogUtil.error(getClass(),e);
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }

        return  list;
    }

    public Set<String> findAllBidByMap() throws Exception{
        String  sql = "SELECT contract_no FROM `t_gq_bid` t1";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<String> set = new HashSet<>();
        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                set.add(rs.getString(1));
            }

        }catch (Exception e){

        }finally {
            close(rs);
            close(ps);
            close(conn);
        }

        return  set;
    }
}
