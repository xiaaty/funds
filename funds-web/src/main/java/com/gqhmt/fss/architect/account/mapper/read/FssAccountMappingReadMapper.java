package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountMappingEntity;
import java.util.List;
import java.util.Map;

/**
 * @author keyulai
 */
public interface FssAccountMappingReadMapper extends ReadMapper<FssAccountMappingEntity>{

	public List<FssAccountMappingEntity> queryAccountMapping(Map map);

}
