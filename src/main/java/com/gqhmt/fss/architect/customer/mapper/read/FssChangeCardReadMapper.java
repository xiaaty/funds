package com.gqhmt.fss.architect.customer.mapper.read;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import java.util.List;

/**
 * Filename:    com.gq.funds.dao.ChangeCardDao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/30 16:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/30  于泳      1.0     1.0 Version
 */
public interface FssChangeCardReadMapper extends ReadMapper<FssChangeCardEntity> {

    public List<FssChangeCardEntity> queryChangeCardList(FssChangeCardEntity fssBankcard);

}
