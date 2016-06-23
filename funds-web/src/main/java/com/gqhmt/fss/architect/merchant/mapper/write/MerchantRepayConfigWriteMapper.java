package com.gqhmt.fss.architect.merchant.mapper.write;

import com.gqhmt.fss.architect.merchant.entity.MerchantRepayConfigEntity;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/20.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/20.  xdw         1.0     1.0 Version
 */
public interface MerchantRepayConfigWriteMapper {
    public void updateMerchantRepayConfigEntity(MerchantRepayConfigEntity merchantRepayConfigEntity);

    public void addMerchantRepayConfigEntity(MerchantRepayConfigEntity merchantRepayConfigEntity);
}
