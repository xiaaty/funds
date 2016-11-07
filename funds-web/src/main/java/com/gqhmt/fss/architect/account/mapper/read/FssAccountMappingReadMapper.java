package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountBondEntity;
import java.util.List;
import java.util.Map;

/**
 * @author keyulai
 */
public interface FssAccountMappingReadMapper extends ReadMapper<FssAccountBondEntity>{

	public List<FssAccountBondEntity> queryAccountMapping(Map map);

}
