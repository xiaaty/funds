package com.gqhmt.DataMigration.dao;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Filename:    com.gqhmt.DataMigration.dao.InvementDao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/25 17:04
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/25  于泳      1.0     1.0 Version
 */
public class InvementDao extends SuperGqDao {

    private final static InvementDao invementDao  = new InvementDao();

    public static InvementDao getInvementDao(){
        return invementDao;
    }

    private InvementDao(){}

    public CachedRowSet findAll() throws Exception{
        String  sql = "SELECT * FROM `t_gq_investment_info` t1";

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        CachedRowSetImpl cs = new CachedRowSetImpl();
        cs.populate(rs);
        rs.close();
        ps.close();

        return  cs;
    }
}