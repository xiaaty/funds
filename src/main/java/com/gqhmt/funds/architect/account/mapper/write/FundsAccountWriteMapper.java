package com.gqhmt.funds.architect.account.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.p2p.account.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 16:03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
public interface FundsAccountWriteMapper extends ReadAndWriteMapper<FundAccountEntity> {

	public void updateCustName(Integer cusID, String custName) ;

	public void insertAccountList(@Param("accounts") List<FundAccountEntity> entities);

	public void updateAndSaveAccount(Map map);
	
	public void updateCustNameByCustId(Map map) ;
}
