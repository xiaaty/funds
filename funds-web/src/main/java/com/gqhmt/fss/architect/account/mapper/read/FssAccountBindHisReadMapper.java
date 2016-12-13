package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountBindHisEntity;

import java.util.List;

/**
 * @author keyulai
 */
public interface FssAccountBindHisReadMapper extends ReadMapper<FssAccountBindHisEntity>{

	public List<FssAccountBindHisEntity> queryBindAccountLimit();

}
