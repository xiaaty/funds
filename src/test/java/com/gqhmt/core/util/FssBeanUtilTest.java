package com.gqhmt.core.util;

import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Filename:    com.gqhmt.core.util.FssBeanUtilTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/31 17:16
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/31  于泳      1.0     1.0 Version
 */
public class FssBeanUtilTest {

    @Test
    public void test(){
        List<Field> list = FssBeanUtil.findField(RepaymentDto.class);

        List<PropertyDescriptor> list1 = FssBeanUtil.FindPropertyDescriptor(RepaymentDto.class);

        for(PropertyDescriptor propertyDescriptor:list1){
            System.out.println(propertyDescriptor);
            //System.out.println(propertyDescriptor.getReadMethod().getName()+propertyDescriptor.getWriteMethod().getName());
        }

        assert list.size()>0 && list1.size()>0;
    }
}
