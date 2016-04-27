package com.gqhmt.DataMigration.dao;

import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.util.LogUtil;

/**
 * Filename:    com.gqhmt.DataMigration.dao.SuperDao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/4/25 14:41
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/4/25  于泳      1.0     1.0 Version
 */
public class SuperDao {

    static{
        String classDriver = ResourceUtil.getValue("jdbc.jdbc","jdbc.driverClassName");
        try {
            Class.forName(classDriver);
        } catch (ClassNotFoundException e) {
            LogUtil.error(SuperDao.class,e);
        }
    }

}
