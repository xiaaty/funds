package com.gqhmt.fss.architect.sftp.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.sftp.entity.FssProjectInfoEntity;

public interface FssProjectInfoReadMapper extends ReadMapper<FssProjectInfoEntity> {
	public List<FssProjectInfoEntity> queryFssProjectInfoList(Map map);	
}
