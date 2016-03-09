package com.gqhmt.extServInter.dto;

/**
 * Filename:    com.gqhmt.extServInter.dto.QueryListResponse
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/3/8 23:37
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/3/8  于泳      1.0     1.0 Version
 */
public class QueryListResponse extends Response {

    private Object plain;

    public Object getPlain() {
        return plain;
    }

    public void setPlain(Object plain) {
        this.plain = plain;
    }
}
