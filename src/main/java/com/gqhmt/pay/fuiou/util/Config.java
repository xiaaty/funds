package com.gqhmt.pay.fuiou.util;

/**
 * Filename:    com.thirdparty.configer
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/3/29 13:18
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/3/29  于泳      1.0     1.0 Version
 */
public interface Config {

    public Object getValue(String key);

    public String getURL();

    public boolean isConnection();
}
