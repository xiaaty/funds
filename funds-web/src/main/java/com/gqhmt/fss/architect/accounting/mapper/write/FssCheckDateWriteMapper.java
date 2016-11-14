package com.gqhmt.fss.architect.accounting.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
import org.springframework.stereotype.Repository;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/6/27.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/6/27.  jhz         1.0     1.0 Version
 */
public interface FssCheckDateWriteMapper extends ReadAndWriteMapper<FssCheckDate> {

    public int updateInputState(String inputDate);

}
