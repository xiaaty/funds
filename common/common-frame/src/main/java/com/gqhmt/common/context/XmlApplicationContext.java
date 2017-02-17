package com.gqhmt.common.context;

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



    public void init(String path){
        Logger.info(this.getClass()," System init starting ......");

        Logger.info(this.getClass()," loadConfigurations ["+path+"]");

//        Exception



    }

}
