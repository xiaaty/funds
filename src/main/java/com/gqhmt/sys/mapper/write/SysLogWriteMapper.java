package com.gqhmt.sys.mapper.write;

import com.gqhmt.core.mybatis.WriteMapper;
import com.gqhmt.sys.beans.SysLog;

public interface SysLogWriteMapper {
	public int insertSysLog(SysLog sysLog);
}
