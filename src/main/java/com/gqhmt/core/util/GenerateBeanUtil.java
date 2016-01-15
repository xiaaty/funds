package com.gqhmt.core.util;


/**
 * Filename:    com.gqhmt.core.util.GenericBeanUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/15 18:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/15  于泳      1.0     1.0 Version
 */
public class GenerateBeanUtil {

    public static <T> T GenerateClassInstance(Class<T> tClass) throws Exception {
        try {
            T t = tClass.newInstance();

            return t;
        } catch (InstantiationException e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        } catch (IllegalAccessException e) {
            LogUtil.error(GenerateBeanUtil.class,e);
        }

        throw new Exception("Don't generate instance ");
    }
}
