package com.gqhmt.pay.fuiou.config;


import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.configer.ConfigAbstract;

/**
 * Filename:    com.fuiou.config
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/3/29 13:19
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/3/29  于泳      1.0     1.0 Version
 */
public class FuiouConfigImpl extends ConfigAbstract implements Config {
    private final String xmlPath = "com/fuiou/fuiouRemote.xml";
    public FuiouConfigImpl(){
        super.init();
    }

    @Override
    public String getXmlPath() {
        return xmlPath;
    }



}
