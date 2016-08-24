package com.gqhmt.fss.architect.account.mapper.read;

import java.util.List;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountBalanceDiffFullDataEntity;

/**
 * @author zhaoenyue
 */
public interface FssAccountBalanceDiffFullDataReadMapper extends ReadMapper<FssAccountBalanceDiffFullDataEntity>{
	
	/**
	 * 查询本批次待余额校验的custId
	 * @author zhaoenyue
	 * @return
	 */
	List<String> queryCurrentBusinessDealAccount();
	
}
