package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author keyulai
 */
public interface FssAccountBindReadMapper extends ReadMapper<FssAccountBindEntity>{

	public List<FssAccountBindEntity> queryAccountBindList(Map map);

	public FssAccountBindEntity getBindAccByParam(@Param("busiId")Long busiId,@Param("busiType")Integer busiType);

	public FssAccountBindEntity getBindAccountBySeqNo(@Param("seqNo") String seqNo);

	public List<FssAccountBindEntity> queryBindAccountLimit();

}
