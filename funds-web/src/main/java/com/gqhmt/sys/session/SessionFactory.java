package com.gqhmt.sys.session;

import com.gqhmt.fss.architect.merchant.entity.MerchantEntity;

/**
 * Filename:    com.gqhmt.sys.session.SessionFactory
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/19 0:33
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/19  于泳      1.0     1.0 Version
 */
public class SessionFactory {
    public static final ThreadLocal<MerchantEntity> sessionLocal = new ThreadLocal<MerchantEntity>();

    static {
        MerchantEntity merchantEntity = new MerchantEntity();
        sessionLocal.set(merchantEntity);
    }

    public static String getCode(){
        MerchantEntity merchantEntity = sessionLocal.get();
        return null;
    }


}
