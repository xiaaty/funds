package com.gqhmt.funds.architect.account.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;

import java.util.List;

/**
 * Filename:    com.gq.p2p.account.Bean
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 14:06
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
public interface FundSequenceWriteMapper extends ReadAndWriteMapper<FundSequenceEntity> {

    public void insertSequenceList(List<FundSequenceEntity> list);
}
