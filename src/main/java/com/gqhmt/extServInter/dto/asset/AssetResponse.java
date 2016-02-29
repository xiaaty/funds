package com.gqhmt.extServInter.dto.asset;

import com.gqhmt.extServInter.dto.Response;

import java.util.List;

/**
 * Filename:    com.gqhmt.extServInter.dto.asset.AssetResponse
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/2/28 23:49
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/2/28  于泳      1.0     1.0 Version
 */
public class AssetResponse extends Response {
    private List plain;

    public List getPlain() {
        return plain;
    }

    public void setPlain(List plain) {
        this.plain = plain;
    }
}
