package com.gqhmt.extServInter.fetchService;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.connection.UrlConnectUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.extServInter.fetchService.FetchDataUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/16 11:36
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/16  于泳      1.0     1.0 Version
 */
@Component
public class FetchDataService {



    public  <T> List<T> featchData(Class<T> tClass, String urlType, Map<String, String> map) throws FssException {
        return UrlConnectUtil.sendDataReturnObjectList(tClass,urlType,map);
    }

    public  <T> T featchDataSingle(Class<T> tClass, String utlType, Map<String, String> map) throws FssException {
        return UrlConnectUtil.sendDataReturnSingleObjcet(tClass,utlType,map);

    }

    public  <T> T featchDataSingle(Class<T> tClass, String urlType,String key, String param) throws FssException {
        return UrlConnectUtil.sendDataReturnSingleObjcet(tClass,urlType,key,param);
    }

    public  <T> T featchDataSingleNotJson(Class<T> tClass, String urlType,String key, String param) throws FssException {

        return UrlConnectUtil.sendDataReturnSingleObjcetNotJson(tClass,urlType,key,param);
    }



    public  Map<String,String> featchDataMap(String utlType, Map<String, String> map){
        return null;

    }



















}
