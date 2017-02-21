package com.gqhmt.common.context;

import com.gqhmt.common.exception.XmlParseException;
import com.gqhmt.common.hook.ConfigShutdownHook;
import com.gqhmt.common.log.Logger;

/**
 * Filename:    com.gqhmt.common.context.XmlApplicationContext
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
public class XmlApplicationContext extends ApplicationContext {


    /*** 配置文件解析对象***/
    private XMLConfigureParse xmlConfigureParse;

    /** 系统关闭时的hook*/
    private ConfigShutdownHook hook = new ConfigShutdownHook();



    public void init(String path){
        Logger.info(this.getClass()," System init starting ......");

        Logger.info(this.getClass()," loadConfigurations ["+path+"]");


        try {

            /** 第一步：加载所有配置到内存从配置文件 */
            xmlConfigureParse = new XMLConfigureParse(path,hook);

            Logger.info(this.getClass(),"loadConfigurations succeed!");


        } catch (XmlParseException e) {
            Logger.error(getClass(),e);

//            stop();
        }



    }

}
