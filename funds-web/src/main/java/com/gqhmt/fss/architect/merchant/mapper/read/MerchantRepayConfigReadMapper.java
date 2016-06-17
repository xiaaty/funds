package com.gqhmt.fss.architect.merchant.mapper.read;

import com.gqhmt.fss.architect.merchant.entity.MerchantRepayConfigEntity;

import java.util.List;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/16.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/16.  xdw         1.0     1.0 Version
 */
public interface MerchantRepayConfigReadMapper {

    List<MerchantRepayConfigEntity> getMerchantRepayConfigEntityList();

    List<MerchantRepayConfigEntity> getMerchantRepayConfigEntityListByMchnNo(String mchnNo);
}
