package com.gqhmt.common.base;

import java.text.DecimalFormat;

/**
 * Created by zhou on 2016/10/20.
 * Description: 基础框架常量
 */
public class Constants {

    /**
     * 无参构造
     */
    private Constants(){
        throw new IllegalAccessError("Utility class");
    }

    //报文版本
    public static final String     VERSION                        = "0";
    //在配置文件中类标签名称
    public static final String KEY_SUFFIX_CLASSNAME = "ClassName";
    //实例后缀
    public static final String KEY_SUFFIX_INSTANCE = "Instance";
    //分格符
    public static final String KEY_SPLIT = ".";

    /** spring实例名称*/
    public static final String SPRING_INSTANCE = "BASESpring.Instance";

    /** cacheManager在spring配置中的bean名称 */
    public static final String CACHE_MANAGER_BEAN_ID="baseCacheManager";

    /** 模块编号 */
    public static final String ADAPTER_SYSID = "Adapter.SYSID";
    /** 模块名称 */
    public  static final String ADAPTER_NAME = "Adapter.NAME";
    /**实例编号*/
    public  static final String ADAPTER_INSTID = "Adapter.INSTID";

    public  static final String ADAPTER_INSTID_DESC = "AdapterInstance";

    /** 两位小数的decimal格式化对象 */
    public final static DecimalFormat DECIMALFORMAT                  = new DecimalFormat("0.00");




}
