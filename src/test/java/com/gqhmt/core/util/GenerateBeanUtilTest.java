package com.gqhmt.core.util;

import com.gqhmt.fss.architect.order.entity.FssSeqOrderEntity;
import com.gqhmt.fss.transferDataBean.account.CreateAccountByFuiou;
import org.junit.Test;

/**
 * Filename:    com.gqhmt.core.util.GenerateBeanUtilTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/16 15:55
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/16  于泳      1.0     1.0 Version
 */
public class GenerateBeanUtilTest {

    @Test
    public void generateBeanTest(){
        FssSeqOrderEntity fssSeqOrderEntity = null;
        CreateAccountByFuiou createAccountByFuiou = new CreateAccountByFuiou();
        createAccountByFuiou.setSeq_no("123456");
        try {
            fssSeqOrderEntity =   GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }

        assert fssSeqOrderEntity != null && fssSeqOrderEntity.getModifyTime() != null;
    }
}
