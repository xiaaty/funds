package com.gqhmt.fss.architect.sftp.mapper.read;

import java.util.List;
import java.util.Map;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.sftp.entity.FssProjectCallbackEntity;

public interface FssProjectInfoCallBackReadMapper extends ReadMapper<FssProjectCallbackEntity> {
	public List<FssProjectCallbackEntity> queryFssProjectCallBackList(Map map);	
}
