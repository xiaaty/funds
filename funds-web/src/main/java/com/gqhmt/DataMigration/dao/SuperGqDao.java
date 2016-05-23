package com.gqhmt.DataMigration.dao;

import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.util.LogUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Filename:    com.gqhmt.DataMigration.dao.SuperGqFao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/25 14:44
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/25  于泳      1.0     1.0 Version
 */
public class SuperGqDao extends SuperDao{

    private static final  String url;
    private static final  String user;
    private static final  String pwd;

    static {
        url = ResourceUtil.getValue("jdbc.jdbc","dataMig.gq.jdbc.url");
        user = ResourceUtil.getValue("jdbc.jdbc","dataMig.gq.jdbc.username");
        pwd = ResourceUtil.getValue("jdbc.jdbc","dataMig.gq.jdbc.password");
    }


    public final Connection getConnection(){
        try {
            Connection conn = DriverManager.getConnection(url,user,pwd);
            return  conn;
        } catch (SQLException e) {
            LogUtil.error(getClass(),e);
        }
        return null;
    }
}
