package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import java.util.List;
import java.util.Map;

/**
 * @author keyulai
 */
public interface FssAccountBindReadMapper extends ReadMapper<FssAccountBindEntity>{

	public List<FssAccountBindEntity> queryAccountBindList(Map map);

}
