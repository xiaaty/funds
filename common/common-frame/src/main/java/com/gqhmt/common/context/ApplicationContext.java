package com.gqhmt.common.context;

import com.gqhmt.common.log.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.common.context.AppliacationContext
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/13 16:07
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/13  于泳      1.0     1.0 Version
 */
public abstract class ApplicationContext implements IApplicationContext {
    protected final static Map<String,String> configureMap = new ConcurrentHashMap<>();


    protected final void loadConfigure(final String fileName){
        Logger.info("系统初始化",this.getClass(),"解析配置文件");
    }



}
