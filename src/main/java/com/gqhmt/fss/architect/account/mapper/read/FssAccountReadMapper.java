package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:28
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
public interface FssAccountReadMapper extends ReadMapper<FssAccountEntity> {
	
	
	/**
	 * 获取客户账户信息
	 * @param map
	 * @return
	 */
	public List<FssAccountEntity> findCustomerAccountByParams(Map map);

	
	
	public List<BussAndAccountBean> getBussinessAccountList(Map map);

	public FssAccountEntity findAccountByAccNo(@Param("accNo") String accNo);
	
//	public FssAccountEntity findAccountByCustId(@Param("custId") Long custId);

}
