package com.gqhmt.core.util;

import com.gqhmt.extServInter.dto.SuperDto;

import java.lang.reflect.Field;

/**
 * Filename:    com.gqhmt.core.util.APIValidUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/20 11:03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/20  于泳      1.0     1.0 Version
 */
public class APIValidUtil {



    public static  String valid(SuperDto dto){
        if(dto == null){
            return "90099999";
        }
        Class<SuperDto> dtoClass = (Class<SuperDto>) dto.getClass();
        Class<SuperDto> superDtoClass = (Class<SuperDto>) dtoClass.getSuperclass();
        Field[] fields  = dtoClass.getDeclaredFields();
        return "90099999";
    }
}
