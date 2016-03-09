package com.gqhmt.funds.architect.customer.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;

/**
 * Filename:    com.gq.p2p.customer.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 16:35
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
public interface BankCardinfoWriteMapper extends ReadAndWriteMapper<BankCardInfoEntity> {
	public void updateBankCardInfo(BankCardInfoEntity bankcard);
	public void deleteBankCardInfo(Integer id);
}
