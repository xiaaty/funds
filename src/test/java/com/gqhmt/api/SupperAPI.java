package com.gqhmt.api;

import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.extServInter.dto.SuperDto;

/**
 * Filename:    com.gqhmt.api.SupperAPI
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/26 14:41
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/26  于泳      1.0     1.0 Version
 */
public class SupperAPI {

    public <T extends  SuperDto> T getSuperDto(Class<? extends SuperDto> class1, String tradeType,String mchn) throws IllegalAccessException, InstantiationException {
        T t  = (T) class1.newInstance();
        t.setMchn(mchn);
        t.setTrade_type(tradeType);
        t.setSeq_no(CommonUtil.getSeqNo());
        t.setSignature(CommonUtil.getSeqNo());
        return t;
    }
}
